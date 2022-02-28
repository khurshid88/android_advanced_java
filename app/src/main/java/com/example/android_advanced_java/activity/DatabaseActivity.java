package com.example.android_advanced_java.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_advanced_java.R;
import com.example.android_advanced_java.activity.database.UserDao;
import com.example.android_advanced_java.activity.database.UserRepository;
import com.example.android_advanced_java.activity.managers.RealmManager;
import com.example.android_advanced_java.activity.model.Post;
import com.example.android_advanced_java.activity.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {
    TextView tv_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        initViews();
    }

    void initViews() {
        tv_size = findViewById(R.id.tv_size);
        Button b_realm = findViewById(R.id.b_realm);
        Button b_room = findViewById(R.id.b_room);
        b_realm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realmDatabase();
            }
        });
        b_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomDatabase();
            }
        });
    }

    void realmDatabase() {
        Post post = new Post(10, "PDP");
        RealmManager.getInstance().savePost(post);
        List<Post> posts = RealmManager.getInstance().loadPosts();
        tv_size.setText("Realm DB size is " + posts.size());
    }

    void roomDatabase() {
        UserRepository repository = new UserRepository(getApplication());
        User user = new User(10, "Kurbanov Xurshidbek");
        new UserAsyncTask(repository).execute(user);
    }

    class UserAsyncTask extends AsyncTask<User, Void, List<User>> {
        UserRepository repository;

        UserAsyncTask(UserRepository repository) {
            this.repository = repository;
        }

        @Override
        protected List<User> doInBackground(User... users) {
            repository.saveUser(users[0]);
            return repository.getUsers();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            tv_size.setText("Room DB size is " + users.size());
        }
    }
}
