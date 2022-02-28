package com.example.android_advanced_java.activity.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android_advanced_java.activity.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long saveUser(User user);

    @Query("select * from user_table")
    List<User> getUsers();
}
