package com.example.jonas.cygni_test_jonas_haag.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Photos {

    @SerializedName("photo")
    private List<Photo> photo = null;

    public List<Photo> getPhoto() {
        return photo;
    }

}
