package com.example.android_advanced_java.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_advanced_java.R;
import com.example.android_advanced_java.activity.database.UserRepository;
import com.example.android_advanced_java.activity.model.User;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    void initViews() {
        context = this;
        Button b_home = findViewById(R.id.b_home);
        b_home.setText(getString(R.string.app_name));

        b_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNetworkActivity();
            }
        });
    }

    void callNetworkActivity() {
        Intent intent = new Intent(context, NetworkActivity.class);
        startActivity(intent);
    }

    void callReceiverActivity() {
        Intent intent = new Intent(context, ReceiverActivity.class);
        startActivity(intent);
    }

    void callServiceActivity() {
        Intent intent = new Intent(context, ServiceActivity.class);
        startActivity(intent);
    }

    void callStorageActivity() {
        Intent intent = new Intent(context, StorageActivity.class);
        startActivity(intent);
    }

    void callDatabaseActivity() {
        Intent intent = new Intent(context, DatabaseActivity.class);
        startActivity(intent);
    }

    void callPreferenceActivity() {
        Intent intent = new Intent(context, PreferenceActivity.class);
        startActivity(intent);
    }

    void callLanguageActivity() {
        Intent intent = new Intent(context, LanguageActivity.class);
        startActivity(intent);
    }
}