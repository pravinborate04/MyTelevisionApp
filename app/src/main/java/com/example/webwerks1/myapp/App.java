package com.example.webwerks1.myapp;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;

/**
 * Created by webwerks1 on 17/5/16.
 */
public class App extends Application
{
    public static App app;
    public static final String username="pravinborate";
    public static final String password="pravib123";
    public static final String session_id="8b1ffac3af5e553751d73367804583128a5a246f";


    public static App getInstance(){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        Stetho.initializeWithDefaults(this);
    }
}
