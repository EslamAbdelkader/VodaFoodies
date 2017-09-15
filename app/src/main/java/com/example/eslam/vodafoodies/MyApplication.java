package com.example.eslam.vodafoodies;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.eslam.vodafoodies.model.User;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.gson.Gson;

/**
 * Created by Eslam on 8/13/2017.
 */

public class MyApplication extends Application {
    private static final String MY_PREFS_NAME = "myPrefsName";
    private static final String USER = "user";
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        context = getApplicationContext();
    }


    public static void saveUser(User user) {
        SharedPreferences.Editor edit = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        edit.putString(USER,new Gson().toJson(user)).commit();
    }

    public static User getUser(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        return new Gson().fromJson(sharedPreferences.getString(USER, null),User.class);
    }

}
