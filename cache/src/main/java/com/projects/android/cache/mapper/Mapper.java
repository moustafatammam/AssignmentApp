package com.projects.android.cache.mapper;

public interface Mapper<T, V> {

    V mapFromCached(T t);

    T mapToCached(V v);

}
