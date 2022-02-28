package com.example.android_advanced_java.activity.database;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.android_advanced_java.activity.managers.RoomManager;
import com.example.android_advanced_java.activity.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Application application) {
        RoomManager db = RoomManager.getDatabase(application);
        userDao = db.usersDao();
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }
}
