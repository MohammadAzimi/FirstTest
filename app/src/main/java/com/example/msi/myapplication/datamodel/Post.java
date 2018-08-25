package com.example.msi.myapplication.datamodel;

import android.graphics.drawable.Drawable;

/**
 * Created by msi on 8/13/2017.
 */
public class Post {
    private int id;
    private String postImageUrl;
    private String title;
    private String content;
    private String date;
    private int isVisited = 0;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getPostImageUrl() {
        return postImageUrl;
    }
    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }


    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }


    public int isVisited() {
        return isVisited;
    }
    public void setIsVisited(int isVisited) {
        this.isVisited = isVisited;
    }
}
