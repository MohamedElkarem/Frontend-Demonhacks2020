package com.example.appstract;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appstract.serverconnection.UnsafeClientFactory;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    //somehow this async code needs to be moved out into its own class - idk how yet
    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            OkHttpClient client = UnsafeClientFactory.getUnsafeOkHttpClient();

            Request request = new Request.Builder()
                            .url(urls[0])
                            .build();

            Response response = null;

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (response.isSuccessful()) {
                try {
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return "Connection failed I guess?";
        }

        @Override
        protected void onPostExecute(String result) {
            textView.setText(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
    }

    public void moveToQuestion(View view) throws InterruptedException {
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(new String[] { "https://34.72.56.190/" });
    }

}