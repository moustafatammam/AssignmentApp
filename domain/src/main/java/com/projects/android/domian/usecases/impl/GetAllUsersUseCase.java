package com.projects.android.domian.usecases.impl;

import com.projects.android.domian.executor.PostExecutionThread;
import com.projects.android.domian.executor.ThreadExecutor;
import com.projects.android.domian.model.User;
import com.projects.android.domian.repository.UserRepository;
import com.projects.android.domian.usecases.base.AbstractUseCaseForObservable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAllUsersUseCase extends AbstractUseCaseForObservable<List<User>, Void> {

    private final UserRepository mUserRepository;

    @Inject
    public GetAllUsersUseCase(UserRepository mUserRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.mUserRepository = mUserRepository;
    }

    @Override
    public Observable<List<User>> buildObservableUseCase(Void aVoid) {
        return mUserRepository.getAllUsers();
    }

}
