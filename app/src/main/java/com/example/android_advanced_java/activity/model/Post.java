package com.example.android_advanced_java.activity.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Post extends RealmObject {
    @PrimaryKey
    private long id;
    private String caption;

    public Post(){

    }

    public Post(long id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
