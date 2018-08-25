package com.example.msi.myapplication.datamodel;

import android.graphics.drawable.Drawable;

/**
 * Created by msi on 8/16/2017.
 */
public class Cloth {
    private int id;
    private Drawable image;
    private String title;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
