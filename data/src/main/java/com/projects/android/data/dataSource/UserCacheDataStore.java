package com.projects.android.data.dataSource;

import com.projects.android.data.model.DataUser;
import com.projects.android.data.repository.UserCache;
import com.projects.android.data.repository.UserDataStore;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class UserCacheDataStore implements UserDataStore {

    private UserCache mUserCache;

    @Inject
    public UserCacheDataStore(UserCache mUserCache){

        this.mUserCache = mUserCache;
    }

    @Override
    public Completable insert(DataUser dataUser) {
        return mUserCache.saveTask(dataUser);
    }

    @Override
    public Completable delete(DataUser dataUser) {
        return mUserCache.deleteTask(dataUser);
    }

    @Override
    public Observable<List<DataUser>> getAllTasks() {
        return mUserCache.getAllTasks();
    }
}
