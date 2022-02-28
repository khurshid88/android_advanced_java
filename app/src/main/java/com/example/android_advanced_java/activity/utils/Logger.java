package com.example.android_advanced_java.activity.utils;

import android.util.Log;

import com.example.android_advanced_java.activity.network.volley.VolleyHttp;

public class Logger {

    public static void d(String tag, String msg) {
        if (VolleyHttp.IS_TESTER) Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (VolleyHttp.IS_TESTER) Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (VolleyHttp.IS_TESTER) Log.v(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (VolleyHttp.IS_TESTER) Log.e(tag, msg);
    }
}
