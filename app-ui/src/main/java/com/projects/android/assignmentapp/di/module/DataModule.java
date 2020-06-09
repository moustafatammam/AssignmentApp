package com.projects.android.assignmentapp.di.module;

import com.projects.android.data.UserDataRepository;
import com.projects.android.data.executor.JobExecutor;
import com.projects.android.domian.executor.ThreadExecutor;
import com.projects.android.domian.repository.UserRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataModule {

    @Binds
    abstract UserRepository bindTaskRepository(UserDataRepository userDataRepository);

    @Binds
    abstract ThreadExecutor bindThreadExecutor(JobExecutor jobExecutor);
}
