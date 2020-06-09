package com.projects.android.presentation.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.projects.android.domian.model.User;
import com.projects.android.domian.usecases.impl.DeleteUserUseCase;
import com.projects.android.domian.usecases.impl.GetAllUsersUseCase;
import com.projects.android.presentation.mapper.PreUserMapper;
import com.projects.android.presentation.model.PresenterUser;
import com.projects.android.presentation.resource.Resource;
import com.projects.android.presentation.resource.State;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;

public class UserListViewModel extends ViewModel {

    private GetAllUsersUseCase mGetAllUsers;
    private DeleteUserUseCase mDeleteUser;
    private PreUserMapper mPreUserMapper;

    private MutableLiveData<Resource<List<PresenterUser>>> usersLiveData = new MutableLiveData<>();
    private MutableLiveData<Resource<String>> deleteUserCompletableLiveData = new MutableLiveData<>();




    @Inject
    public UserListViewModel(GetAllUsersUseCase mGetAllUsers,DeleteUserUseCase mDeleteUser, PreUserMapper mPreUserMapper) {
        this.mGetAllUsers = mGetAllUsers;
        this.mDeleteUser = mDeleteUser;
        this.mPreUserMapper = mPreUserMapper;
        fetchTasks();
    }



    @Override
    protected void onCleared() {
        mGetAllUsers.dispose();
        super.onCleared();
    }

    private void fetchTasks(){
        usersLiveData.postValue(new Resource<>(State.LOADING, null, null));
        mGetAllUsers.execute(new TasksObserver(), null);
    }

    public LiveData<Resource<String>> deleteTask(PresenterUser preUser) {
        deleteUserCompletableLiveData.postValue(new Resource<>(State.LOADING, null, null));
        mDeleteUser.execute(new DeleteTaskCompletable(), mPreUserMapper.mapFromPreModel(preUser));
        return deleteUserCompletableLiveData;
    }

    public MutableLiveData<Resource<List<PresenterUser>>> getUsersLiveData() {
        return usersLiveData;
    }

    class TasksObserver extends DisposableObserver<List<User>> {

        @Override
        public void onNext(List<User> users) {
            List<PresenterUser> preList = new ArrayList<>();
            for(User user : users){
                preList.add(mPreUserMapper.mapToPreModel(user));
            }
            usersLiveData.postValue(new Resource<>(State.SUCCESS, preList, null));
        }

        @Override
        public void onError(Throwable e) {
            usersLiveData.postValue(new Resource<>(State.ERROR, null, e.getMessage()));
        }

        @Override
        public void onComplete() {
        }
    }

    class DeleteTaskCompletable extends DisposableCompletableObserver {

        @Override
        public void onComplete() {
            String toastMsg = "User Deleted Successfully";
            deleteUserCompletableLiveData.postValue(new Resource<>(State.SUCCESS, toastMsg, null));
        }

        @Override
        public void onError(Throwable e) {
            deleteUserCompletableLiveData.postValue(new Resource<>(State.ERROR, null, e.getMessage()));
        }
    }
}
