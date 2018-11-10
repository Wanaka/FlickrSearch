package com.example.jonas.cygni_test_jonas_haag.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jonas.cygni_test_jonas_haag.R;
import com.example.jonas.cygni_test_jonas_haag.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FlickrAdapter extends RecyclerView.Adapter<FlickrAdapter.ViewHolder> {
    private List<Photo> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    Context c;

    // data is passed into the constructor
    public FlickrAdapter(Context context, List<Photo> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.c = context;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String myListData = mData.get(position).getUrlS();
        Picasso.with(c).load(myListData).into(holder.img);
    }

    // total number of cells
    @Override
    public int getItemCount() {
            return mData.size();
    }



    //ViewHolder
    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_rv_row);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
