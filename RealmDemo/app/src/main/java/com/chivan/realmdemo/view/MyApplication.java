package com.chivan.realmdemo.view;

import android.app.Application;

import io.realm.Realm;

/**
 * @author Chivan
 * @description:
 * @date : 2019-08-19 21:03
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
