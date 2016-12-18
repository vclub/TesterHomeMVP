package com.testerhome.nativeandroid.application;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by Bin Li on 2016/12/9.
 */


public class THApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }
}
