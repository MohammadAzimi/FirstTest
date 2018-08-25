package com.example.msi.myapplication.datamodel;

/**
 * Created by msi on 5/31/2018.
 */
public class User {

    private String firstName = "";
    private String lastName = "";

    private boolean isHtmlExpert = false;
    private boolean isCssExpert = false;
    private boolean isJavaExpert = false;

    public static final byte MALE = 0;
    public static final byte FEMALE = 1;

    private byte gender = MALE;


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public boolean isHtmlExpert() {
        return isHtmlExpert;
    }
    public void setIsHtmlExpert(boolean isHtmlExpert){
        this.isHtmlExpert = isHtmlExpert;
    }


    public boolean isCssExpert() {
        return isCssExpert;
    }
    public void setIsCssExpert(boolean isCssExpert) {
        this.isCssExpert = isCssExpert;
    }


    public boolean isJavaExpert() {
        return isJavaExpert;
    }
    public void setIsJavaExpert(boolean isJavaExpert) {
        this.isJavaExpert = isJavaExpert;
    }


    public byte getGender() {
        return gender;
    }
    public void setGender(byte gender) {
        this.gender = gender;
    }
}
