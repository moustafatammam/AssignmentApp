package com.projects.android.assignmentapp.userInterface.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.projects.android.assignmentapp.R;
import com.projects.android.assignmentapp.databinding.UserItemBinding;
import com.projects.android.assignmentapp.model.ViewUser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {

    private List<ViewUser> viewUserList = new ArrayList<>();


    @Inject
    public UserListAdapter() {
    }

    @NonNull
    @Override
    public UserListAdapter.UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UserItemBinding userItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_item, parent, false);
        return new UserListViewHolder(userItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.UserListViewHolder holder, int position) {
        holder.userItemBinding.setViewUser(viewUserList.get(position));
    }

    @Override
    public int getItemCount() {
        return viewUserList == null ? 0 : viewUserList.size();
    }

    public class UserListViewHolder extends RecyclerView.ViewHolder{

        UserItemBinding userItemBinding;

        public UserListViewHolder(@NonNull UserItemBinding userItemBinding) {
            super(userItemBinding.getRoot());
            this.userItemBinding = userItemBinding;
        }
    }

    public void submitList(List<ViewUser> viewUserList){
        this.viewUserList = viewUserList;
        notifyDataSetChanged();
    }

    public List<ViewUser> getViewUserList() {
        return viewUserList;
    }

    @BindingAdapter({"imageUrl"})
    public static void bindImage(ImageView imageView, int gender){
        switch (gender){
            case 0:
                imageView.setImageResource(R.drawable.human_male);
                break;
            case 1:
                imageView.setImageResource(R.drawable.human_female);
                break;
        }
    }

    @BindingAdapter({"age"})
    public static void bindAge(TextView textView, int age){
        String ageString  = Integer.toString(age);
        textView.setText(ageString);
    }
}
