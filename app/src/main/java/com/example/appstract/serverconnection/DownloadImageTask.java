package com.example.appstract.serverconnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadImageTask extends AsyncTask<String, Void, Boolean> {

    Context fileContext;

    public DownloadImageTask(Context context) {
        fileContext = context;
    }

    protected Boolean doInBackground(String... urls) {

        final OkHttpClient client = UnsafeClientFactory.getUnsafeOkHttpClient();

        Request request = new Request.Builder()
                .url(urls[0])
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
                File file = new File(fileContext.getFilesDir(), "mans.jpg");

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