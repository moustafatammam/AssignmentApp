package com.projects.android.assignmentapp.userInterface.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.projects.android.assignmentapp.R;
import com.projects.android.assignmentapp.databinding.FragmentAddUserBinding;
import com.projects.android.assignmentapp.mapper.ViewUserMapper;
import com.projects.android.assignmentapp.model.ViewUser;
import com.projects.android.presentation.ViewModelFactory;
import com.projects.android.presentation.resource.Resource;
import com.projects.android.presentation.resource.State;
import com.projects.android.presentation.viewModel.AddUserViewModel;

import javax.inject.Inject;

public class AddUserFragment extends Fragment {

    private FragmentAddUserBinding fragmentAddUserBinding;
    private EditText userNameEditText;
    private EditText userAgeEditText;
    private EditText userJobTitleEditText;
    private RadioButton maleButton;
    private RadioButton femaleBurron;

    private Button addUserButton;
    private Button cancelButton;




    private AddUserViewModel addUserViewModel;
    private ViewModelFactory viewModelFactory;
    private ViewUserMapper viewUserMapper;

    @Inject
    public AddUserFragment(ViewModelFactory viewModelFactory, ViewUserMapper viewUserMapper) {
        this.viewModelFactory = viewModelFactory;
        this.viewUserMapper = viewUserMapper;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAddUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_user, container, false);
        return fragmentAddUserBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupTaskDetails();
        addUserViewModel = new ViewModelProvider(this, viewModelFactory).get(AddUserViewModel.class);

        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupTaskData();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(cancelButton).navigate(R.id.action_addUserFragment_to_userListFragment);
            }
        });
    }

    private void setupTaskData(){

        userNameEditText.setError(null);
        userAgeEditText.setError(null);
        userJobTitleEditText.setError(null);
        boolean cancel = false;
        View focusView = null;

        String userNameText = userNameEditText.getText().toString();

        if(userNameText.isEmpty()){
            userNameEditText.setError("name can not be empty");
            focusView = userNameEditText;
            cancel = true;
        }
        String userAgeText = userAgeEditText.getText().toString();
        if(userAgeText.isEmpty()){
            userAgeEditText.setError("age can not be empty");
            focusView = userAgeEditText;
            cancel = true;
        }
        String userJobTitleText = userJobTitleEditText.getText().toString();

        if(userJobTitleText.isEmpty()){
            userJobTitleEditText.setError("job title can not be empty");
            focusView = userJobTitleEditText;
            cancel = true;
        }
        int gender;
        if (maleButton.isChecked()){
            gender = 0;
        }else{
            gender = 1;
        }
        if (cancel){
            focusView.requestFocus();

        }else{
            ViewUser viewUser = new ViewUser(userNameText, userJobTitleText, Integer.parseInt(userAgeText), gender);
            addTask(viewUser);
            Navigation.findNavController(addUserButton).navigate(R.id.action_addUserFragment_to_userListFragment);
        }
    }

    public void addTask(ViewUser user){

        addUserViewModel.addTask(viewUserMapper.mapFromViewUser(user)).observe(this, new Observer<Resource<String>>() {
            @Override
            public void onChanged(Resource<String> stringResource) {
                if(stringResource.mStatus == State.SUCCESS){
                    // do something after adding is completed
                }else{
                    // do something if adding was not successful
                }
            }
        });
    }

    private void setupTaskDetails(){
        userNameEditText = fragmentAddUserBinding.editTextNameContent;
        userAgeEditText = fragmentAddUserBinding.editTextAgeContent;
        userJobTitleEditText = fragmentAddUserBinding.editTextJobTitleContent;

        maleButton = fragmentAddUserBinding.maleButton;
        femaleBurron = fragmentAddUserBinding.femaleButton;

        addUserButton = fragmentAddUserBinding.addUserButton;
        cancelButton = fragmentAddUserBinding.cancelButton;

    }
}
