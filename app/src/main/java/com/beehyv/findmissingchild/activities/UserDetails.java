package com.beehyv.findmissingchild.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.beehyv.findmissingchild.R;
import com.beehyv.findmissingchild.pojos.UserData;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class UserDetails extends AppCompatActivity {
    private EditText userName,userPhone,userEmail;
    private Button userSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        getWindow().setBackgroundDrawableResource(R.drawable.splash_blurred);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("User Details");

        userName=(EditText)findViewById(R.id.user_name);
        userPhone=(EditText)findViewById(R.id.user_phone);
        userEmail=(EditText)findViewById(R.id.user_email);
        userSubmit=(Button)findViewById(R.id.user_submit);

        userSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()){
                    String name=userName.getText().toString().trim();
                    int phoneNumber=Integer.parseInt(userPhone.getText().toString());
                    String emailID=userEmail.getText().toString().trim();
                    UserData userData=new UserData(name,phoneNumber,emailID);

                    //saving userData to SharedPreferences
                    String userDataStorage="{name:"+name+",phoneNumber:"+ Integer.toString(phoneNumber)+",emailID:"+emailID+"}";
                    SharedPreferences sharedPref = getPreferences ( Context. MODE_PRIVATE );
                    SharedPreferences. Editor editor = sharedPref . edit ();
                    editor.putString("userDetails",userDataStorage);
                    editor . commit ();
                    //TODO send to backend
                }

            }
        });
        /*View.OnFocusChangeListener ofcl=new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validateForm();
                }
            }
        };

        userName.setOnFocusChangeListener(ofcl);
        userEmail.setOnFocusChangeListener(ofcl);
        userPhone.setOnFocusChangeListener(ofcl);
*/
    }
    private boolean validateForm() {
        boolean nameOK = false;
        boolean phoneOK = false;
        boolean emailOK = false;
        final Pattern namePattern = Pattern.compile("^[a-zA-Z0-9]*$");
        //TODO check phonePattern regex
        final Pattern phonePattern = Pattern.compile("[0-9]{10}");
        final Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*\n" +
                "      @[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);
        if (userName.getText() != null) {
            String nameValue = userName.getText().toString().trim();
            if (nameValue.equals(""))
                userName.setError("Name should not be empty");
            else if (!namePattern.matcher(nameValue).matches())
                userName.setError("No special characters allowed in name");
            else {
                userName.setError(null);
                nameOK = true;
            }
        }
        if (userPhone.getText() != null) {
            String phoneValue = userPhone.getText().toString().trim();
            if (phoneValue.equals(""))
                userPhone.setError("Phone number should not be empty");
            else if (!phonePattern.matcher(phoneValue).matches())
                userPhone.setError("Please enter a proper phonenumber");
            else {
                userPhone.setError(null);
                phoneOK = true;
            }
        }
        if (userEmail.getText() != null) {
            String emailValue = userEmail.getText().toString().trim();
            if (emailValue.equals(""))
                userEmail.setError("EmailID should not be empty");
            else if (!emailPattern.matcher(emailValue).matches())
                userEmail.setError("PLease enter a proper emailID");
            else {
                userEmail.setError(null);
                emailOK = true;
            }
        }
        if(nameOK && phoneOK && emailOK)
            return true;
        else
            return false;
    }
}

