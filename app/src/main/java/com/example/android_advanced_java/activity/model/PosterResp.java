package com.example.android_advanced_java.activity.model;

import com.google.gson.annotations.SerializedName;

public class PosterResp {
    @SerializedName("userId")
    int userId;
    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("body")
    String body;
}
