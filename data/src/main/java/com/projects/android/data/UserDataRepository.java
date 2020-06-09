package com.projects.android.data;

import com.projects.android.data.dataSource.UserDataStoreFactory;
import com.projects.android.data.mapper.DataUserMapper;
import com.projects.android.data.model.DataUser;
import com.projects.android.domian.model.User;
import com.projects.android.domian.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class UserDataRepository  implements UserRepository {

    private UserDataStoreFactory mUserDataStoreFactory;
    private DataUserMapper mDataUserMapper;

    @Inject
    public UserDataRepository(UserDataStoreFactory mUserDataStoreFactory, DataUserMapper mDataUserMapper) {
        this.mUserDataStoreFactory = mUserDataStoreFactory;
        this.mDataUserMapper = mDataUserMapper;
    }


    @Override
    public Completable insert(User user) {
        return mUserDataStoreFactory.retrieveCacheDataStore().insert(mDataUserMapper.mapToDataModel(user));
    }

    @Override
    public Completable delete(User user) {
        return mUserDataStoreFactory.retrieveCacheDataStore().delete(mDataUserMapper.mapToDataModel(user));
    }


    @Override
    public Observable<List<User>> getAllUsers() {
        return mUserDataStoreFactory.retrieveCacheDataStore().getAllTasks()
                .map(taskEntities -> {
                    List<User> taskList = new ArrayList<>();
                    for(DataUser dataUser : taskEntities){
                        taskList.add(mDataUserMapper.mapFromDataModel(dataUser));
                    }
                    return taskList;
                });
    }
}
