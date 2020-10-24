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
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class DownloadImageTask extends AsyncTask<String, Void, Boolean> {

    Context fileContext;
    String questionJson;
    List<String> fileNames;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public DownloadImageTask(Context context, String requestData, List<String> fns) {
        fileContext = context;
        questionJson = requestData;
        fileNames = fns;
    }

    protected Boolean doInBackground(String... urls) {

        final OkHttpClient client = UnsafeClientFactory.getUnsafeOkHttpClient();

        try{
            JsonObject obj = new JsonParser().parse(questionJson).getAsJsonObject();
        }
        catch (Exception e){
            int x = 6;
        }




        RequestBody requestBody = RequestBody.create(questionJson, JSON);

        Request request = new Request.Builder()
                .url(urls[0])
                .post(requestBody)
                .build();

        Response response = null;
        Bitmap mIcon11 = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.isSuccessful()) {
            try {
                InputStream inputStream = response.body().byteStream();
                File file = new File(fileContext.getFilesDir(), fileNames.get(0));

                OutputStream outputStream = new FileOutputStream(file);

                // write the inputStream to a FileOutputStream
                IOUtils.copy(inputStream, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    protected void onPostExecute(Boolean result) {
    }
}