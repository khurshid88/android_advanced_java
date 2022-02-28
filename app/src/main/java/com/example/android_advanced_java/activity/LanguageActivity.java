package com.example.android_advanced_java.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_advanced_java.R;
import com.example.android_advanced_java.activity.managers.LocaleManager;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        initViews();
    }

    void initViews() {
        context = this;
        Button b_english = findViewById(R.id.b_english);
        b_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setLocale("en");
                MyApplication.localeManager.setNewLocale(context, LocaleManager.LANGUAGE_ENGLISH);
                finish();
            }
        });
        Button b_russian = findViewById(R.id.b_russian);
        b_russian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setLocale("ru");
                MyApplication.localeManager.setNewLocale(context, LocaleManager.LANGUAGE_RUSSIAN);
                finish();
            }
        });
        Button b_uzbek = findViewById(R.id.b_uzbek);
        b_uzbek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setLocale("uz");
                MyApplication.localeManager.setNewLocale(context, LocaleManager.LANGUAGE_UZBEK);
                finish();
            }
        });

        // one = 1
        String one = getResources().getQuantityString(R.plurals.my_cats, 1,1);
        // few = 2~4
        String few = getResources().getQuantityString(R.plurals.my_cats, 2,3);
        // many = 5~
        String many = getResources().getQuantityString(R.plurals.my_cats, 5, 10);

        Log.d("@@@one ", one);
        Log.d("@@@few ", few);
        Log.d("@@@many ", many);
    }

    public void setLocale(String lang_code) {
        Locale locale = new Locale(lang_code);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        finish();
    }
}
