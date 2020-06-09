package com.projects.android.cache;

import com.projects.android.cache.database.UserDatabase;
import com.projects.android.cache.mapper.UserCacheMapper;
import com.projects.android.cache.model.CacheUser;
import com.projects.android.data.model.DataUser;
import com.projects.android.data.repository.UserCache;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class UserCacheImpl implements UserCache {

    private UserDatabase mUserDatabase;
    private UserCacheMapper mUserCacheMapper;

    @Inject
    public UserCacheImpl(UserDatabase mUserDatabase, UserCacheMapper mUserCacheMapper) {
        this.mUserDatabase = mUserDatabase;
        this.mUserCacheMapper = mUserCacheMapper;
    }

    @Override
    public Completable saveTask(DataUser dataUser) {
        return Completable.defer(() -> {
            mUserDatabase.userDao().saveTask(mUserCacheMapper.mapToCached(dataUser));
            return Completable.complete();
        });
    }

    @Override
    public Completable deleteTask(DataUser dataUser) {
        return Completable.defer(() -> {
            mUserDatabase.userDao().deleteTask(mUserCacheMapper.mapToCached(dataUser));
            return Completable.complete();
        });
    }

    @Override
    public Observable<List<DataUser>> getAllTasks() {
        return Observable.defer(() -> Observable.just(mUserDatabase.userDao().getAllTasks())
                .map(cacheUsers -> {
                    List<DataUser> dataUserList = new ArrayList<>();
                    for(CacheUser cacheUser : cacheUsers){
                        dataUserList.add(mUserCacheMapper.mapFromCached(cacheUser));
                    }
                    return dataUserList;
                }));
    }

}
