package com.projects.android.data.mapper;

import com.projects.android.data.model.DataUser;
import com.projects.android.domian.model.User;

import javax.inject.Inject;

public class DataUserMapper implements Mapper<DataUser, User> {

    @Inject
    DataUserMapper() {
    }


    @Override
    public User mapFromDataModel(DataUser dataUser) {
        return new User(dataUser.getId(), dataUser.getName(), dataUser.getJobTitle(),
                dataUser.getAge(), dataUser.getGender());
    }

    @Override
    public DataUser mapToDataModel(User user) {
        return new DataUser(user.getId(), user.getName(), user.getJobTitle(),
                user.getAge(), user.getGender());
    }
}


