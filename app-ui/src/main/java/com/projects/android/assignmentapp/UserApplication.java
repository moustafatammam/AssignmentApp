package com.projects.android.assignmentapp;

import android.app.Application;

import com.projects.android.assignmentapp.di.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class UserApplication extends Application implements HasAndroidInjector {


    @Inject
    DispatchingAndroidInjector<Object> activityDispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }




    @Override
    public AndroidInjector androidInjector() {
        return activityDispatchingAndroidInjector;
    }
}
