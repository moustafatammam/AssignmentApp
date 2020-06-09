package com.projects.android.cache.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.projects.android.cache.dao.UserDao;
import com.projects.android.cache.model.CacheUser;

import javax.inject.Inject;

@Database(entities = {CacheUser.class},
        version = 1,
        exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static final String LOG_TAG = UserDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "UserDatabase";

    private static UserDatabase sDatabaseInstance;

    @Inject
    UserDatabase(){}


    public static UserDatabase getInstance(Context context){
        if(sDatabaseInstance == null){
            synchronized (LOCK){
                sDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                        UserDatabase.class, UserDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "getting the database instance");
        return sDatabaseInstance;
    }

    public abstract UserDao userDao();
}

