package com.example.android_advanced_java.activity.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.Toast;

public class BoundService extends Service {
    private IBinder mBinder = new MyBinder();
    private Chronometer mChronometer;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Bound Service Created", Toast.LENGTH_LONG).show();
        mChronometer = new Chronometer(this);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                Toast.makeText(getApplication(), String.valueOf(chronometer.getBase()), Toast.LENGTH_LONG).show();
            }
        });
        mChronometer.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Bound Service onBind", Toast.LENGTH_LONG).show();
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Toast.makeText(this, "Bound Service onRebind", Toast.LENGTH_LONG).show();
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "Bound Service onUnbind", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Bound Service Stopped", Toast.LENGTH_LONG).show();
        mChronometer.stop();
    }

    public String getTimestamp() {
        long elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();
        int hours = (int) (elapsedMillis / 3600000);
        int minutes = (int) (elapsedMillis - hours * 3600000) / 60000;
        int seconds = (int) (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000;
        int millis = (int) (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000);
        return hours + ":" + minutes + ":" + seconds + ":" + millis;
    }

    public class MyBinder extends Binder {
        public BoundService getService() {
            return BoundService.this;
        }
    }
}
