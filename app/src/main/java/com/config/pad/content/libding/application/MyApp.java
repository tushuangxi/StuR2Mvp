package com.config.pad.content.libding.application;

import android.app.Application;
import android.content.Context;

/**
 * Function:
 */
public class MyApp extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }
}
