package com.rsb.skerdi.personpicker;

import android.graphics.drawable.Drawable;

/**
 * Created by user on 7/31/2018.
 */

public class Person {

    private String name;
    private String surname;
    private Integer id;
    private Drawable profile_image;
    private String profile_image_url;

    public Person(){
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, Drawable profile_image) {
        this.name = name;
        this.surname = surname;
        this.profile_image = profile_image;
        this.profile_image_url = null;
    }

    public Person(String name, String surname, String profile_image_url) {
        this.name = name;
        this.surname = surname;
        this.profile_image_url = profile_image_url;
        this.profile_image = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Drawable getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(Drawable profile_image) {
        this.profile_image = profile_image;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }
}
