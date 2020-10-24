package com.example.appstract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appstract.serverconnection.DownloadImageTask;
import com.example.appstract.serverconnection.GetImageListTask;
import com.example.appstract.serverconnection.UnsafeClientFactory;

//import org.apache.commons.io.IOUtils;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    MainActivity selfReference = this;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.main_image_view);
    }

    public void moveToQuestion(View view) throws InterruptedException, IOException, ExecutionException {
        Intent intent = new Intent(this, QuestionActivity.class);

        startActivity(intent);
    }
}