package com.projects.android.assignmentapp.userInterface.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.projects.android.assignmentapp.R;
import com.projects.android.assignmentapp.databinding.ActivityMainBinding;
import com.projects.android.assignmentapp.userInterface.InjectingFragmentFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    ActivityMainBinding activityMainBinding;


    @Inject
    InjectingFragmentFactory injectingFragmentFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        getSupportFragmentManager().setFragmentFactory(injectingFragmentFactory);
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host);
        Toolbar toolbar = activityMainBinding.toolbar;
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}
