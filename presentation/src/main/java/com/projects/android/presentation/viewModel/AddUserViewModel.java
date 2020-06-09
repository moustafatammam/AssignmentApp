package com.projects.android.presentation.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.projects.android.domian.usecases.impl.AddUserUseCase;
import com.projects.android.presentation.mapper.PreUserMapper;
import com.projects.android.presentation.model.PresenterUser;
import com.projects.android.presentation.resource.Resource;
import com.projects.android.presentation.resource.State;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;

public class AddUserViewModel extends ViewModel {

    private AddUserUseCase mAddUserUseCase;
    private PreUserMapper mPreUserMapper;

    private MutableLiveData<Resource<String>> addUserCompletableLiveData = new MutableLiveData<>();

    @Inject
    public AddUserViewModel(AddUserUseCase mAddUserUseCase, PreUserMapper mPreUserMapper) {
        this.mAddUserUseCase = mAddUserUseCase;
        this.mPreUserMapper = mPreUserMapper;
    }

    @Override
    protected void onCleared() {
        mAddUserUseCase.dispose();
        super.onCleared();
    }


    public LiveData<Resource<String>> addTask(PresenterUser preUser) {
        addUserCompletableLiveData.postValue(new Resource<>(State.LOADING, null, null));
        mAddUserUseCase.execute(new AddTaskCompletable(), mPreUserMapper.mapFromPreModel(preUser));
        return addUserCompletableLiveData;
    }

    class AddTaskCompletable extends DisposableCompletableObserver {

        @Override
        public void onComplete() {
            String toastMsg = "User Added Successfully";
            addUserCompletableLiveData.postValue(new Resource<>(State.SUCCESS, toastMsg, null));
        }

        @Override
        public void onError(Throwable e) {
            addUserCompletableLiveData.postValue(new Resource<>(State.ERROR, null, e.getMessage()));
        }
    }
}
