package com.example.kate.mygallery.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kate.mygallery.GalleryView;
import com.example.kate.mygallery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kate on 05.05.2017.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosHolder> {

    private List<GalleryView> galleryViews;
    private Context context;

    public PhotosAdapter(List<GalleryView> views){

        galleryViews = views;
    }

    @Override
    public PhotosHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_gallery, parent, false);
        return new PhotosHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotosHolder holder, int position) {

        GalleryView galleryView = galleryViews.get(position);
        Picasso.with(context)
                .load(galleryView.getUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return galleryViews.size();
    }

    protected class PhotosHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        protected PhotosHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.photo);
        }
    }

}
