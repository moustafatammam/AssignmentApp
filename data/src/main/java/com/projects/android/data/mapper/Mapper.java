package com.projects.android.data.mapper;

public interface Mapper<T, V> {

    V mapFromDataModel(T t);

    T mapToDataModel(V v);
}
