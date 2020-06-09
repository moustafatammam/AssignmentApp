package com.projects.android.domian.repository;

import com.projects.android.domian.model.User;

import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Observable;

public interface  UserRepository {

    Completable insert(User user);

    Completable delete(User user);

    Observable<List<User>> getAllUsers();
}
