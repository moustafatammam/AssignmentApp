package com.projects.android.assignmentapp.di.module;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.projects.android.assignmentapp.di.FragmentKey;
import com.projects.android.assignmentapp.userInterface.InjectingFragmentFactory;
import com.projects.android.assignmentapp.userInterface.fragments.AddUserFragment;
import com.projects.android.assignmentapp.userInterface.fragments.UserListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class FragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(UserListFragment.class)
    abstract Fragment bindTaskListFragment(UserListFragment userListFragment);

    @Binds
    @IntoMap
    @FragmentKey(AddUserFragment.class)
    abstract Fragment bindAddTaskFragment(AddUserFragment addUserFragment);

    @Binds
    abstract FragmentFactory bindFragmentFactory(InjectingFragmentFactory injectingFragmentFactory);
}
