package com.example.jonas.cygni_test_jonas_haag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Photo {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("url_s")
    @Expose
    private String urlS;

    public Photo(String mId, String mTitle, String mUrls ){
        id = mId;
        title = mTitle;
        urlS = mUrls;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public String getUrlS() {
        return urlS;
    }
}
