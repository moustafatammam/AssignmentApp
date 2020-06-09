package com.projects.android.presentation.mapper;

import com.projects.android.domian.model.User;
import com.projects.android.presentation.model.PresenterUser;

import javax.inject.Inject;

public class PreUserMapper implements Mapper<User, PresenterUser> {

    @Inject
    public PreUserMapper() {}

    @Override
    public PresenterUser mapToPreModel(User user) {
        return new PresenterUser(user.getId(), user.getName(), user.getJobTitle(),
                user.getAge(), user.getGender());
    }

    @Override
    public User mapFromPreModel(PresenterUser presenterUser) {
        return new User(presenterUser.getId(), presenterUser.getName(), presenterUser.getJobTitle(),
                presenterUser.getAge(), presenterUser.getGender());
    }
}
