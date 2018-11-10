package com.example.jonas.cygni_test_jonas_haag.model;

import com.google.gson.annotations.SerializedName;


public class PhotoResult {
    @SerializedName("photos")
    private Photos photos;

    @SerializedName("stat")
    private String stat;

    public Photos getPhotos() {
        return photos;
    }
}
