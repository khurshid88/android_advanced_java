package com.example.android_advanced_java.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_advanced_java.R;
import com.example.android_advanced_java.activity.services.BoundService;
import com.example.android_advanced_java.activity.services.StartedService;

public class ServiceActivity extends AppCompatActivity {
    BoundService boundService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initViews();
    }

    void initViews() {
        Button b_start1 = findViewById(R.id.b_start1);
        Button b_stop1 = findViewById(R.id.b_stop1);
        Button b_start2 = findViewById(R.id.b_start2);
        Button b_stop2 = findViewById(R.id.b_stop2);
        Button b_timestamp = findViewById(R.id.b_timestamp);
        TextView tv_timestamp = findViewById(R.id.tv_timestamp);

        b_start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStartedService();
            }
        });
        b_stop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopStartedService();
            }
        });
        b_start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBoundService();
            }
        });
        b_stop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopBoundService();
            }
        });
        b_timestamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBound) {
                    tv_timestamp.setText(boundService.getTimestamp());
                }
            }
        });
    }

    public void startStartedService() {
        startService(new Intent(this, StartedService.class));
    }

    public void stopStartedService() {
        stopService(new Intent(this, StartedService.class));
    }

    public void startBoundService() {
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void stopBoundService() {
        if (isBound) {
            unbindService(mServiceConnection);
            isBound = false;
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) service;
            boundService = myBinder.getService();
            isBound = true;
        }
    };
}
