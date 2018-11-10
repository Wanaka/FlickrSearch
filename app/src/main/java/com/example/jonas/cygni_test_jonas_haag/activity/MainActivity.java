package com.example.jonas.cygni_test_jonas_haag.activity;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jonas.cygni_test_jonas_haag.R;
import com.example.jonas.cygni_test_jonas_haag.adapter.FlickrAdapter;
import com.example.jonas.cygni_test_jonas_haag.api.Api;
import com.example.jonas.cygni_test_jonas_haag.constant.Constant;
import com.example.jonas.cygni_test_jonas_haag.model.Photo;
import com.example.jonas.cygni_test_jonas_haag.model.PhotoResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FloatingActionButton button;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Photo> list;
    private EditText inputText;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.customToolbar);
        toolbar.setTitle(Constant.EMPTY_PLACEHOLDER);
        setSupportActionBar(toolbar);
        button = findViewById(R.id.search_button);
        button.setOnClickListener(this);
        inputText = findViewById(R.id.text_input_toolbar);
        progressBar = findViewById(R.id.loading_progressbar);
        list = new ArrayList<>();

        mRecyclerView = findViewById(R.id.flickrRV);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, Constant.GRID_SPAN_COUNT);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Set default text input as 'Cat'
        inputText.setText(Constant.DEFAULT_FLICKR_SEARCH_TEXT);
        searchFlickr(inputText.getText().toString());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.search_button && !inputText.getText().toString().isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);
            list.clear();
            searchFlickr(inputText.getText().toString());
            hideKeyboard(v);
            mAdapter.notifyDataSetChanged();
        } else{
            Toast.makeText(this, R.string.toast_search_without_text, Toast.LENGTH_SHORT).show();
        }
    }

    void searchFlickr(String sendText){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<PhotoResult> call = api.getFlickrList(Constant.METHOD, Constant.API_KEY, sendText, Constant.EXTRAS, Constant.FORMAT, Constant.CALL_BACK);

        call.enqueue(new Callback<PhotoResult>() {
            @Override
            public void onResponse(Call<PhotoResult> call, Response<PhotoResult> response) {
                progressBar.setVisibility(View.INVISIBLE);

                PhotoResult result = response.body();
                for (Photo photo: result.getPhotos().getPhoto()) {
                    list.add(photo);
                }
                //Set list of photos to adapter
                mAdapter = new FlickrAdapter(getApplicationContext(), list);
                mRecyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<PhotoResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.toast_flickr_failed, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(
                view.getWindowToken(), 0);
    }
}
