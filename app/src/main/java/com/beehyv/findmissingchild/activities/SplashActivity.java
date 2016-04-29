package com.beehyv.findmissingchild.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.beehyv.findmissingchild.adapters.ImageAdapter;
import com.beehyv.findmissingchild.utilities.Utils;

/**
 * Created by rishan on 25/4/16.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isUserFirstTime = Utils.readSharedSetting(getApplicationContext(), Utils.PREFERENCES_FILE);
        Intent introIntent = new Intent(getApplicationContext(), PagerActivity.class);
        introIntent.putExtra(Utils.PREFERENCES_FILE, isUserFirstTime);
        introIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isUserFirstTime) {
            startActivity(introIntent);
            finish();
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
