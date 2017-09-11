package com.example.eslam.vodafoodies;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Eslam on 8/13/2017.
 */

public class MyApplication extends Application {
    private static final String MY_PREFS_NAME = "myPrefsName";
    private static final String USERU_ID = "userId";
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        context = getApplicationContext();
    }


    public static void saveUserId(String userId) {
        SharedPreferences.Editor edit = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        edit.putString(USERU_ID,userId).commit();
    }

    public static String  getUserId(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        return sharedPreferences.getString(USERU_ID, null);
    }

}
