package com.projects.android.assignmentapp.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.projects.android.assignmentapp.di.ViewModelKey;
import com.projects.android.presentation.ViewModelFactory;
import com.projects.android.presentation.viewModel.AddUserViewModel;
import com.projects.android.presentation.viewModel.UserListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel.class)
    public abstract ViewModel bindTaskListViewModel(UserListViewModel taskListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddUserViewModel.class)
    abstract ViewModel bindAddTaskViewModel(AddUserViewModel addTaskViewModel);

    @Binds
    abstract ViewModelProvider.Factory  bindViewModelFactory(ViewModelFactory viewModelFactory);
}
