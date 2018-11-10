package com.example.jonas.cygni_test_jonas_haag.api;

import com.example.jonas.cygni_test_jonas_haag.model.PhotoResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://api.flickr.com/services/";

    @GET("rest/?")
    Call<PhotoResult> getFlickrList(
            @Query("method") String method,
            @Query("api_key") String key,
            @Query("text") String text,
            @Query("extras") String extras,
            @Query("format") String format,
            @Query("nojsoncallback") int call_back
    );
}
