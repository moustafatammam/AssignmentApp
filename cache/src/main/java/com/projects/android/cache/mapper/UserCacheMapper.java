package com.projects.android.cache.mapper;

import com.projects.android.cache.model.CacheUser;
import com.projects.android.data.model.DataUser;

import javax.inject.Inject;

public class UserCacheMapper implements Mapper<CacheUser, DataUser>{

    @Inject
    public UserCacheMapper() {
    }

    @Override
    public DataUser mapFromCached(CacheUser cacheUser) {
        return new DataUser(cacheUser.getId(), cacheUser.getName(), cacheUser.getJobTitle(),
                cacheUser.getAge(), cacheUser.getGender());
    }

    @Override
    public CacheUser mapToCached(DataUser dataUser) {
        return new CacheUser(dataUser.getId(), dataUser.getName(), dataUser.getJobTitle(),
                dataUser.getAge(), dataUser.getGender());
    }
}
