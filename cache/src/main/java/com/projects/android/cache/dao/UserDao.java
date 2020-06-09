package com.projects.android.cache.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.projects.android.cache.model.CacheUser;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void saveTask(CacheUser cacheUser);

    @Query("SELECT * FROM user ORDER BY id DESC")
    List<CacheUser> getAllTasks();

    @Delete
    void deleteTask(CacheUser cacheUser);
}
