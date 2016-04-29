package com.beehyv.findmissingchild.activities;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by kapil on 29/4/16.
 *
 * Helper class to supply location coordinates of the user
 *
 * Helpful links: http://developer.android.com/intl/zh-cn/guide/topics/location/strategies.html
 *
 * We can utilize GPS and Android's Network Location Provider to acquire the user location.
 * Although GPS is most accurate, it only works outdoors, it quickly consumes battery power,
 * and doesn't return the location as quickly as users want. Android's Network Location Provider
 * determines user location using cell tower and Wi-Fi signals, providing location information
 * in a way that works indoors and outdoors, responds faster, and uses less battery power. To
 * obtain the user location in our application, we can use both GPS and the Network Location
 * Provider, or just one.
 */
public abstract class LocationHelperActivity extends AppCompatActivity {
}
