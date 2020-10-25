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
    String fileName;

    public DownloadImageTask(Context context, String fn) {
        fileContext = context;
        fileName = fn;
    }

    protected Boolean doInBackground(String... urls) {

        final OkHttpClient client = UnsafeClientFactory.getUnsafeOkHttpClient();

        Request request = new Request.Builder()
                .url(urls[0])
                .addHeader("Connection","close")
                .addHeader("content-type", "image/jpeg")
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
                byte[] bytes = response.body().bytes();

                File file = new File(fileContext.getFilesDir(), fileName);

                OutputStream outputStream = new FileOutputStream(file);
                outputStream.write(bytes);
                // write the inputStream to a FileOutputStream
//                IOUtils.copy(inputStream, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        }

        return true;
    }

    protected void onPostExecute(Boolean result) {
    }
}