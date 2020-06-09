package com.projects.android.assignmentapp.di;

import android.app.Application;

import com.projects.android.assignmentapp.UserApplication;
import com.projects.android.assignmentapp.di.module.AppUiModule;
import com.projects.android.assignmentapp.di.module.ApplicationModule;
import com.projects.android.assignmentapp.di.module.CacheModule;
import com.projects.android.assignmentapp.di.module.DataModule;
import com.projects.android.assignmentapp.di.module.FragmentModule;
import com.projects.android.assignmentapp.di.module.PresentationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ApplicationModule.class, CacheModule.class,
        DataModule.class, PresentationModule.class, AppUiModule.class,
        AndroidSupportInjectionModule.class, FragmentModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(UserApplication app);
}
