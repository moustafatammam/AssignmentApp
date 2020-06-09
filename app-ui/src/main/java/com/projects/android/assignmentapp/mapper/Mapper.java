package com.projects.android.assignmentapp.mapper;

public interface Mapper<T, V> {

    T mapToViewUser(V v);

    V mapFromViewUser(T t);
}
