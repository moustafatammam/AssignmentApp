package com.projects.android.presentation.mapper;

public interface Mapper<T, V> {

    V mapToPreModel(T t);

    T mapFromPreModel(V v);
}
