package com.example.itamarborges.popularmoviesstage2;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by itamarborges on 17/02/18.
 */

public class MainApp extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
