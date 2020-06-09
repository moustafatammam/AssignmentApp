package com.projects.android.assignmentapp.di.module;

import com.projects.android.assignmentapp.UiThread;
import com.projects.android.assignmentapp.userInterface.activities.MainActivity;
import com.projects.android.domian.executor.PostExecutionThread;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppUiModule {

    @Binds
    abstract PostExecutionThread bindPostExecutionThread(UiThread uiThread);


    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

}
