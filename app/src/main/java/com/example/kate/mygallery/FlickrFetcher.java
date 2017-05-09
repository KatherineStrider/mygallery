package com.example.kate.mygallery;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    private static final String ERROR_MESSAGE_DATA_LOAD_FAILED = "Ошибка загрузки данных";
    private static final String ERROR_MESSAGE_JSON = "Ошибка парсинга Json";

    public String getJSONString(String urlSpec) throws IOException{

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlSpec)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public List<GalleryView> fetchItems(){

        List<GalleryView> items = new ArrayList<>();

        try{
            String url = Uri.parse("https://api.flickr.com/services/rest").buildUpon()
                    .appendQueryParameter("method", "flickr.photos.getRecent")
//                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s")
                    .build().toString();

            String jsonString = getJSONString(url);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        } catch (IOException e) {

            Log.e(TAG, ERROR_MESSAGE_DATA_LOAD_FAILED, e);
        }catch (JSONException e) {
            Log.e(TAG, ERROR_MESSAGE_JSON, e);
        }

        return items;
    }

    public void parseItems(List<GalleryView> galleryViews, JSONObject jsonBody) throws IOException, JSONException{

        JSONObject photosJsonObject = jsonBody.getJSONObject("photos");
        JSONArray photoJsonArray = photosJsonObject.getJSONArray("photo");

        for (int i = 0; i < photoJsonArray.length(); i++) {

            JSONObject photoJsonObject = photoJsonArray.getJSONObject(i);

            GalleryView galleryView = new GalleryView();
            galleryView.setId(photoJsonObject.getString("id"));
            galleryView.setCaption(photoJsonObject.getString("title"));

            if(!photoJsonObject.has("url_s")){
                continue;
            }
            galleryView.setUrl(photoJsonObject.getString("url_s"));
            galleryViews.add(galleryView);
        }
    }
}
