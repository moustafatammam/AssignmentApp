package com.projects.android.domian.usecases.impl;

import com.projects.android.domian.executor.PostExecutionThread;
import com.projects.android.domian.executor.ThreadExecutor;
import com.projects.android.domian.model.User;
import com.projects.android.domian.repository.UserRepository;
import com.projects.android.domian.usecases.base.AbstractUseCaseForCompletable;

import javax.inject.Inject;

import io.reactivex.Completable;

public class AddUserUseCase  extends AbstractUseCaseForCompletable<User> {

    private final UserRepository mUserRepository;

    @Inject
    public AddUserUseCase(UserRepository mUserRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.mUserRepository = mUserRepository;
    }


    @Override
    public Completable buildCompletableUseCase(User user) {
        return mUserRepository.insert(user);
    }
}
