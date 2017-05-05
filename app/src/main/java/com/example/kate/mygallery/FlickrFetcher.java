package com.example.kate.mygallery;

import android.net.Uri;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Kate on 05.05.2017.
 */

public class FlickrFetcher {

    private static final String TAG = GalleryActivity.class.getSimpleName();
    private static final String API_KEY = "";

    public String getJSONString(String urlSpec) throws IOException{

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlSpec)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public List<GalleryView> fetchViews(){

        List<GalleryView> galleryViews = new ArrayList<>();

        try{
            String url = Uri.parse("https://api.flickr.com/services/rest").buildUpon()
        }
    }
}
