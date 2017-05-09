package com.example.kate.mygallery;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kate.mygallery.adapters.PhotosAdapter;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = GalleryActivity.class.getSimpleName();
    private RecyclerView photosView;
    private List<GalleryView> galleryViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        photosView = (RecyclerView) findViewById(R.id.recycler_view_for_photos);
        photosView.setLayoutManager(new GridLayoutManager(this, 3));

        new FetchItemTask().execute();

        setupAdapter();
    }

    private void setupAdapter(){
        photosView.setAdapter(new PhotosAdapter(galleryViews));
    }

    private class FetchItemTask extends AsyncTask<Void, Void, List<GalleryView>>{

        @Override
        protected List<GalleryView> doInBackground(Void... params) {
            return new FlickrFetcher().fetchItems();
        }

        @Override
        protected void onPostExecute(List<GalleryView> items) {
            galleryViews = items;
            setupAdapter();
        }
    }
}
