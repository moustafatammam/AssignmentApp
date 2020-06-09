package com.projects.android.data.repository;

import com.projects.android.data.model.DataUser;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface UserDataStore {

    Completable insert(DataUser dataUser);

    Completable delete(DataUser dataUser);

    Observable<List<DataUser>> getAllTasks();
}
