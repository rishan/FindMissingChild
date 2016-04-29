package com.beehyv.findmissingchild.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.beehyv.findmissingchild.R;
import com.beehyv.findmissingchild.adapters.DrawerItemClickListener;
import com.beehyv.findmissingchild.adapters.DrawerItemCustomAdapter;
import com.beehyv.findmissingchild.adapters.ImageAdapter;
import com.beehyv.findmissingchild.pojos.ObjectDrawerItem;
import com.beehyv.findmissingchild.utilities.Utils;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements ImageAdapter.interactionInterface {
    private static final int CAMERA_REQUEST = 100;
    private RecyclerView recyclerView;
    private EditText name;
    private EditText missingFrom;
    private ArrayList<Bitmap> imageList=new ArrayList<>();
    private ImageAdapter imageAdapter;
    private String[] listOptions;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private boolean gender=true;
    /*
    *gender=true ---> Male
    * gender=false ---> Female
    */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // first call should be to open camera
        if(imageList.isEmpty())
            loadCamera();
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.splash_blurred);
        setTitle("Missing Child");
        //exit soft keyboard on clicking outside
        Utils.setupUI(findViewById(R.id.frame_layout),this);
        /*TextView title=(TextView)findViewById(R.id.title);
        title.setText("Find Missing Child");*/
        mTitle = mDrawerTitle = getTitle();

        //Image population
        recyclerView = (RecyclerView) findViewById(R.id.images);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        imageAdapter = new ImageAdapter(imageList, this);
        recyclerView.setAdapter(imageAdapter);
        Toolbar toolbar=(Toolbar)findViewById(R.id.app_toolbar);

        //Navigation Drawer
        listOptions=getResources().getStringArray(R.array.list_options);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerList=(ListView)findViewById(R.id.left_drawer);
        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[3];

        drawerItem[0] = new ObjectDrawerItem(R.drawable.add_missing_child, listOptions[0]);
        drawerItem[1] = new ObjectDrawerItem(R.drawable.added_child_list, listOptions[1]);
        drawerItem[2] = new ObjectDrawerItem(R.drawable.help, listOptions[2]);
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.drawer_list_item, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener(getApplicationContext()));
        mTitle = mDrawerTitle = getTitle();

        //Form
        name=(EditText)findViewById(R.id.name);
        missingFrom=(EditText)findViewById(R.id.missing_from);

        //Gender Selection
        //gender true=male
        boolean gender=true;
        LinearLayout genderMale=(LinearLayout)findViewById(R.id.gender_male);
        final ImageView maleImage =(ImageView)findViewById(R.id.male_icon);
        LinearLayout genderFemale=(LinearLayout)findViewById(R.id.gender_female);
        final ImageView femaleImage =(ImageView)findViewById(R.id.female_icon);
        genderMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGender()==false) {
                    setGender(true);
                    femaleImage.setImageResource(R.drawable.female);
                    maleImage.setImageResource(R.drawable.male_select);
                }
            }
        });
        genderFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGender()==true) {
                    setGender(false);
                    femaleImage.setImageResource(R.drawable.female_select);
                    maleImage.setImageResource(R.drawable.male);
                }
            }
        });

        //Age Range Selection
        Spinner age= (Spinner)findViewById(R.id.age);
        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this,
                R.array.age_options, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.dropdown_layout);
        age.setAdapter(ageAdapter);


        //on Submitting Form
        Button submit= (Button)findViewById(R.id.form_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Generate Form Object
                Intent intent = new Intent(getApplicationContext(), UserDetails.class);
                //intent.putExtra("childDetails",childDetails);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.getLayoutManager().scrollToPosition(imageList.size());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageList.add(photo);
            imageAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void loadCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}