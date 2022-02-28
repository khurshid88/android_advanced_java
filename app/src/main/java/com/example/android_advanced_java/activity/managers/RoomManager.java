package com.example.android_advanced_java.activity.managers;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android_advanced_java.activity.database.UserDao;
import com.example.android_advanced_java.activity.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class RoomManager extends RoomDatabase {

    public abstract UserDao usersDao();
    private static RoomManager INSTANCE;

    public static RoomManager getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomManager.class, "app_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
