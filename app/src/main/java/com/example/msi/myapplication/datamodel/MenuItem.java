package com.example.msi.myapplication.datamodel;

import android.graphics.drawable.Drawable;

/**
 * Created by msi on 7/15/2018.
 */
public class MenuItem {
    public static final int ID_POSTS_ACTIVITY = 0;
    public static final int ID_USER_PROFILE = 1;
    public static final int ID_FASHION = 2;
    public static final int ID_MUSIC = 3;
    public static final int ID_VIDEO = 4;
    public static final int ID_LOGIN = 5;
    public static final int ID_ANIMATION = 6;

    private Drawable menuImage;
    private String menuTitleText;
    private int id;
    private Class DestinationActivity;

    public String getMenueTitleText() {
        return menuTitleText;
    }

    public void setMenueTitleText(String menuTitleText) {
        this.menuTitleText = menuTitleText;
    }

    public Drawable getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(Drawable menuImage) {
        this.menuImage = menuImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Class getDestinationActivity() {
        return DestinationActivity;
    }

    public void setDestinationActivity(Class destinationActivity) {
        DestinationActivity = destinationActivity;
    }
}
