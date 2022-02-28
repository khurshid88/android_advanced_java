package com.example.android_advanced_java.activity.utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static void fireToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
