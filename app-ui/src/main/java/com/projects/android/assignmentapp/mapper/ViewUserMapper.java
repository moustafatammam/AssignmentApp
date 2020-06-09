package com.projects.android.assignmentapp.mapper;


import com.projects.android.assignmentapp.model.ViewUser;
import com.projects.android.presentation.model.PresenterUser;

import javax.inject.Inject;

public class ViewUserMapper implements Mapper<ViewUser, PresenterUser> {

    @Inject
    public ViewUserMapper() {
    }


    @Override
    public ViewUser mapToViewUser(PresenterUser presenterUser) {
        return new ViewUser(presenterUser.getId(), presenterUser.getName(), presenterUser.getJobTitle(),
                presenterUser.getAge(), presenterUser.getGender());
    }

    @Override
    public PresenterUser mapFromViewUser(ViewUser viewUser) {
        return new PresenterUser(viewUser.getId(), viewUser.getName(), viewUser.getJobTitle(),
                viewUser.getAge(), viewUser.getGender());
    }
}
