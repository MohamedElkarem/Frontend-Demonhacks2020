package com.example.appstract.serverconnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class GetImageListTask extends AsyncTask<String, Void, ArrayList<String>> {

    Context fileContext;
    String questionJson;
    ArrayList<String> fileNames;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public GetImageListTask(String requestData) {
        questionJson = requestData;
    }

    protected ArrayList<String> doInBackground(String... urls) {

        final OkHttpClient client = UnsafeClientFactory.getUnsafeOkHttpClient();

        Request request = new Request.Builder()
                .url(urls[0])
                .post(RequestBody.create(questionJson, JSON))
                .build();

        Response response = null;
        Bitmap mIcon11 = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> targetFiles = new ArrayList<>();
        if (response.isSuccessful()) {
            try {
                JsonElement jsonList = JsonParser.parseString(response.body().string());

                for(JsonElement ele : jsonList.getAsJsonArray()){
                    targetFiles.add(ele.toString().replaceAll("^\"|\"$", ""));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return targetFiles;
    }

    protected void onPostExecute(List<String> result) {
    }
}