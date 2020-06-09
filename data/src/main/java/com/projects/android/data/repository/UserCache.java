package com.projects.android.data.repository;

import com.projects.android.data.model.DataUser;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface UserCache {

    Completable saveTask(DataUser dataUser);

    Completable deleteTask(DataUser dataUser);

    Observable<List<DataUser>> getAllTasks();

}
