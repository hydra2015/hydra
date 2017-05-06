package com.hydra.gjhw.photogallery;

import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gjhw on 2017-5-6.
 */

public class FlickFetchr {

    private static final String TAG = "FlichrFetchr";
    //雅虎账号的key
    private static final String API_KEY = "9330a9e92635ee2b5a8a3564ed0c768a";

    public byte[] getUrlBytes(String urlSpec)  throws IOException{
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with" + urlSpec);
            }

            int byteRead = 0;
            byte[] buffer = new byte[1024];
            while ((byteRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, byteRead);
            }
            out.close();
            return out.toByteArray();
        }finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpect) throws IOException{
        return new String(getUrlBytes(urlSpect));
    }

    public void fetchItems(){
        try {
        String url = Uri.parse("https://api.flickr.com/services/rest/")
                .buildUpon()
                .appendQueryParameter("method", "flickr.photos.getRecent")
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter("format", "json")
                .appendQueryParameter("nojsoncallback", "1")
                .appendQueryParameter("extras", "url_s")
                .build().toString();
            String jsonString = getUrlString(url);
            Log.e(TAG, "链接成功，返回 JSON: " + jsonString);
        } catch (IOException e) {
            Log.e(TAG, "取回条目失败", e);
        }
    }
}
