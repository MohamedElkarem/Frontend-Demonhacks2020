package com.example.appstract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.example.appstract.serverconnection.DownloadImageTask;
import com.example.appstract.serverconnection.GetImageListTask;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    public void moveToBoard(View view) throws ExecutionException, InterruptedException {
        String problemPool = getString(R.string.problem_pool);

        final ArrayList<String> targetFiles = new GetImageListTask(problemPool).execute("https://34.72.56.190:443/generate-board").get();

        for(String fileName : targetFiles){
            new DownloadImageTask(this, fileName).execute("https://34.72.56.190:443/get-image/"+fileName);
        }

        Intent intent = new Intent(this, BoardActivity.class);

        intent.putExtra("file_names", targetFiles);

        startActivity(intent);
    }
}