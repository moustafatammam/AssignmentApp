package com.projects.android.assignmentapp.di.module;

import android.app.Application;

import androidx.room.Room;

import com.projects.android.cache.UserCacheImpl;
import com.projects.android.cache.database.UserDatabase;
import com.projects.android.data.repository.UserCache;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CacheModule {

    @Provides
    public static UserDatabase provideTaskDatabase(Application application){
        return Room.databaseBuilder(application.getApplicationContext(),
                UserDatabase.class, "UserDatabase").build();
    }

    @Binds
    public abstract UserCache bindUserCache(UserCacheImpl userCacheImpl);
}
