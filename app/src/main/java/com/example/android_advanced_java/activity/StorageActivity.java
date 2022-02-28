package com.example.android_advanced_java.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.android_advanced_java.R;
import com.example.android_advanced_java.activity.utils.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class StorageActivity extends AppCompatActivity {
    boolean isPersistent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        initViews();

        checkPermission();
    }

    void initViews() {
        Button b_save_int = findViewById(R.id.b_save_int);
        Button b_read_int = findViewById(R.id.b_read_int);
        Button b_delete_int = findViewById(R.id.b_delete_int);

        Button b_save_ext = findViewById(R.id.b_save_ext);
        Button b_read_ext = findViewById(R.id.b_read_ext);
        Button b_delete_ext = findViewById(R.id.b_delete_ext);

        b_save_int.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInternalFile("PDP Internal");
            }
        });
        b_read_int.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readInternalFile();
            }
        });
        b_delete_int.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteInternalFile();
            }
        });
        b_save_ext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveExternalFile("PDP External");
            }
        });
        b_read_ext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readExternalFile();
            }
        });
        b_delete_ext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteExternalFile();
            }
        });

        //checkStoragePaths();
        //createInternalFile();
    }

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Log.d("@@@", "isGranted");
                } else {
                    Log.d("@@@", "Not isGranted");
                }
            });

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.d("@@@", "checkSelfPermission");
        } else {
            Log.d("@@@", "requestPermissionLauncher.launch");
            requestPermissionLauncher.launch(
                    Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    //Internal & External Paths
    void checkStoragePaths() {
        File internal_m1 = getDir("custom", 0);
        File internal_m2 = getFilesDir();

        File external_m1 = getExternalFilesDir(null);
        File external_m2 = getExternalCacheDir();
        File external_m3 = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        Log.d("StorageActivity ", internal_m1.getAbsolutePath());
        Log.d("StorageActivity ", internal_m2.getAbsolutePath());
        Log.d("StorageActivity ", external_m1.getAbsolutePath());
        Log.d("StorageActivity ", external_m2.getAbsolutePath());
        Log.d("StorageActivity ", external_m3.getAbsolutePath());
    }

    //Internal Storages
    private void createInternalFile() {
        String fileName = "pdp_internal.txt";
        File file;
        if (isPersistent) {
            file = new File(getFilesDir(), fileName);
        } else {
            file = new File(getCacheDir(), fileName);
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
                Utils.fireToast(this, String.format
                        ("File %s has been created", fileName));
            } catch (IOException e) {
                Utils.fireToast(this, String.format
                        ("File %s creation failed", fileName));
            }
        } else {
            Utils.fireToast(this, String.format
                    ("File %s already exists", fileName));
        }
    }

    private void saveInternalFile(String data) {
        String fileName = "pdp_internal.txt";
        try {
            FileOutputStream fileOutputStream;
            if (isPersistent) {
                fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            } else {
                File file = new File(getCacheDir(), fileName);
                fileOutputStream = new FileOutputStream(file);
            }
            fileOutputStream.write(data.getBytes(Charset.forName("UTF-8")));
            Utils.fireToast(this, String.format
                    ("Write to %s successful", fileName));
        } catch (Exception e) {
            e.printStackTrace();
            Utils.fireToast(this, String.format
                    ("Write to file %s failed", fileName));
        }
    }

    private void readInternalFile() {
        String fileName = "pdp_internal.txt";
        try {
            FileInputStream fileInputStream;
            if (isPersistent) {
                fileInputStream = openFileInput(fileName);
            } else {
                File file = new File(getCacheDir(), fileName);
                fileInputStream = new FileInputStream(file);
            }

            InputStreamReader inputStreamReader = new InputStreamReader
                    (fileInputStream, Charset.forName("UTF-8"));
            List<String> lines = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            String readText = TextUtils.join("\n", lines);
            Utils.fireToast(this, String.format
                    ("Read from file %s successful", fileName));
        } catch (Exception e) {
            e.printStackTrace();
            Utils.fireToast(this, String.format
                    ("Read from file %s failed", fileName));
        }
    }

    private void deleteInternalFile() {
        String fileName = "pdp_internal.txt";
        File file;
        if (isPersistent) {
            file = new File(getFilesDir(), fileName);
        } else {
            file = new File(getCacheDir(), fileName);
        }
        if (file.exists()) {
            file.delete();
            Utils.fireToast(this, String.format
                    ("File %s has been deleted", fileName));
        } else {
            Utils.fireToast(this, String.format
                    ("File %s doesn't exist", fileName));
        }
    }


    //External Storages
    private void saveExternalFile(String data) {

        String fileName = "pdp_external.txt";
        File file;
        if (isPersistent) {
            file = new File(getExternalFilesDir(null), fileName);
        } else {
            file = new File(getExternalCacheDir(), fileName);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes(Charset.forName("UTF-8")));
            Utils.fireToast(this, String.format
                    ("Write to %s successful", fileName));
        } catch (Exception e) {
            e.printStackTrace();
            Utils.fireToast(this, String.format
                    ("Write to file %s failed", fileName));
        }
    }

    private void readExternalFile() {

        String fileName = "pdp_external.txt";
        File file;
        if (isPersistent)
            file = new File(getExternalFilesDir(null), fileName);
        else
            file = new File(getExternalCacheDir(), fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
            List<String> lines = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            String readText = TextUtils.join("\n", lines);
            Log.d("StorageActivity", readText);
            Utils.fireToast(this, String.format
                    ("Read from file %s successful", fileName));
        } catch (Exception e) {
            e.printStackTrace();
            Utils.fireToast(this, String.format
                    ("Read from file %s failed", fileName));
        }
    }

    private void deleteExternalFile() {
        String fileName = "pdp_external.txt";
        File file;
        if (isPersistent) {
            file = new File(getExternalFilesDir(null), fileName);
        } else {
            file = new File(getExternalCacheDir(), fileName);
        }
        if (file.exists()) {
            file.delete();
            Utils.fireToast(this, String.format
                    ("File %s has been deleted", fileName));
        } else {
            Utils.fireToast(this, String.format
                    ("File %s doesn't exist", fileName));
        }
    }
}



