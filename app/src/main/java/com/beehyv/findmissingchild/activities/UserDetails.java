package com.beehyv.findmissingchild.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.beehyv.findmissingchild.R;
import com.beehyv.findmissingchild.pojos.User;

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
                    long phoneNumber=Long.parseLong(userPhone.getText().toString());
                    String emailID=userEmail.getText().toString().trim();
                    User user =new User(name,phoneNumber,emailID);

                    //saving userData to SharedPreferences
                    String userDataStorage="{name:"+name+",phoneNumber:"+ Long.toString(phoneNumber)+",emailID:"+emailID+"}";
                    SharedPreferences sharedPref = getPreferences ( Context. MODE_PRIVATE );
                    SharedPreferences. Editor editor = sharedPref . edit ();
                    editor.putString("userDetails",userDataStorage);
                    editor . commit ();
                    //TODO send to backend

                    //Alert Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserDetails.this);
                    builder.setMessage("Profile Submitted Successfully");
                    builder.setPositiveButton("ADD NEW", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("GOTO LIST", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getApplicationContext(), AddedChildren.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

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
        final Pattern emailPattern = Pattern.compile("^[^@]+@[^@]+\\.[^@]+$", Pattern.CASE_INSENSITIVE);
        if (userEmail.getText() != null) {
            String emailValue = userEmail.getText().toString().trim();
            if (emailValue.equals("")) {
                userEmail.setError("EmailID should not be empty");
                userEmail.requestFocus();
            }
            else if (!emailPattern.matcher(emailValue).matches()) {
                userEmail.setError("Please enter a proper emailID");
                userEmail.requestFocus();
            }
            else {
                userEmail.setError(null);
                emailOK = true;
            }
        }
        if (userPhone.getText() != null) {
            String phoneValue = userPhone.getText().toString().trim();
            if (phoneValue.equals("")) {
                userPhone.setError("Phone number should not be empty");
                userPhone.requestFocus();
            }
            else if (!phonePattern.matcher(phoneValue).matches()) {
                userPhone.setError("Please enter a proper phonenumber");
                userPhone.requestFocus();
            }
            else {
                userPhone.setError(null);
                phoneOK = true;
            }
        }
        if (userName.getText() != null) {
            String nameValue = userName.getText().toString().trim();
            if (nameValue.equals("")) {
                userName.setError("Name should not be empty");
                userName.requestFocus();
            }
            else if (!namePattern.matcher(nameValue).matches()) {
                userName.setError("No special characters allowed in name");
                userName.requestFocus();
            }
            else {
                userName.setError(null);
                nameOK = true;
            }
        }

        if(nameOK && phoneOK && emailOK)
            return true;
        else
            return false;
    }

}

