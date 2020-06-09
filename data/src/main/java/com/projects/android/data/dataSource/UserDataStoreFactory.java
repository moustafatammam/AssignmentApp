package com.projects.android.data.dataSource;

import javax.inject.Inject;

public class UserDataStoreFactory {

    private UserCacheDataStore mUserCacheDataStore;

    @Inject
    public UserDataStoreFactory(UserCacheDataStore mUserCacheDataStore){
        this.mUserCacheDataStore = mUserCacheDataStore;
    }


    public UserCacheDataStore retrieveCacheDataStore(){
        return mUserCacheDataStore;
    }

}
