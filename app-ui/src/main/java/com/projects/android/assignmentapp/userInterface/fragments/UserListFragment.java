package com.projects.android.assignmentapp.userInterface.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projects.android.assignmentapp.R;
import com.projects.android.assignmentapp.databinding.FragmentUserListBinding;
import com.projects.android.assignmentapp.mapper.ViewUserMapper;
import com.projects.android.assignmentapp.model.ViewUser;
import com.projects.android.assignmentapp.userInterface.adapter.UserListAdapter;
import com.projects.android.presentation.ViewModelFactory;
import com.projects.android.presentation.model.PresenterUser;
import com.projects.android.presentation.resource.Resource;
import com.projects.android.presentation.resource.State;
import com.projects.android.presentation.viewModel.UserListViewModel;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class UserListFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Inject
    UserListAdapter mUserListAdapter;

    private FragmentUserListBinding fragmentUserListBinding;
    private FloatingActionButton mFloatingActionButton;
    private View emptyView;
    private ProgressBar mProgressBar;

    private UserListViewModel userListViewModel;

    private ViewModelFactory viewModelFactory;
    private ViewUserMapper viewUserMapper;

    @Inject
    public UserListFragment(ViewModelFactory viewModelFactory, ViewUserMapper viewUserMapper) {
        this.viewModelFactory = viewModelFactory;
        this.viewUserMapper = viewUserMapper;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentUserListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false);
        return fragmentUserListBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupStateViews();
        userListViewModel = new ViewModelProvider(this, viewModelFactory).get(UserListViewModel.class);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(mFloatingActionButton).navigate(R.id.action_userListFragment_to_addUserFragment);
            }
        });

        userListViewModel.getUsersLiveData().observe(this, new Observer<Resource<List<PresenterUser>>>() {
            @Override
            public void onChanged(Resource<List<PresenterUser>> listResource) {
                if (listResource != null){
                    handleDataState(listResource.mStatus, listResource.mData, listResource.mMessage);
                }
            }
        });
        setupRecyclerView();
    }

    private void handleDataState(State resourceState, List<PresenterUser> data, String message){

        switch (resourceState){
            case LOADING:
                setupScreenForLoadingState();
                break;
            case SUCCESS:
                setupScreenForSuccess(data);
                break;
            default:
                setupScreenForError(message);
        }
    }


    private void setupScreenForSuccess(List<PresenterUser> data){
        if(data != null && !data.isEmpty()){
            List<ViewUser> userViewList = data.stream().map(new Function<PresenterUser, ViewUser>() {
                @Override
                public ViewUser apply(PresenterUser presenterUser) {
                    return viewUserMapper.mapToViewUser(presenterUser);
                }
            }).collect(Collectors.toList());
            mUserListAdapter.submitList(userViewList);
            mRecyclerView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
        }else{
            mRecyclerView.setVisibility(View.INVISIBLE);
            emptyView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);

        }

    }
    private void setupScreenForError(String message) {
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
    }

    private void setupScreenForLoadingState() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);

    }

    private void setupRecyclerView(){
        mRecyclerView = fragmentUserListBinding.userListRecyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mUserListAdapter);

    }

    private void setupStateViews(){
        mProgressBar = fragmentUserListBinding.progressBar;
        emptyView = fragmentUserListBinding.emptyView;
        mFloatingActionButton = fragmentUserListBinding.addUserButton;
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            List<ViewUser> adapterList = mUserListAdapter.getViewUserList();
            ViewUser userToBeDeleted = adapterList.get(viewHolder.getAdapterPosition());
            adapterList.remove(viewHolder.getAdapterPosition());
            deleteUser(userToBeDeleted);
            mUserListAdapter.notifyDataSetChanged();
        }
    };
    private void deleteUser(ViewUser user){
        userListViewModel.deleteTask(viewUserMapper.mapFromViewUser(user)).observe(this, new Observer<Resource<String>>() {
            @Override
            public void onChanged(Resource<String> stringResource) {
                if(stringResource.mStatus == State.SUCCESS){
                    if (mUserListAdapter.getViewUserList().isEmpty()){
                        mRecyclerView.setVisibility(View.INVISIBLE);
                        emptyView.setVisibility(View.VISIBLE);
                    }
                    // do something after deleting a task
                }else{
                    // do something if deleting was not successful
                }
            }
        });
    }
}
