package com.example.appstract;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BoardActivity extends AppCompatActivity {

    private TextView mTextView;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        ArrayList<String> fileNames = getIntent().getExtras().getStringArrayList("file_names");

        RelativeLayout relativeLayout = findViewById(R.id.board_relative_layout);

        for(int i = 0; i< relativeLayout.getChildCount(); i++){

            View targetView = relativeLayout.getChildAt(i);
            if (targetView instanceof ImageView) {
                ImageView imageView = (ImageView) targetView;

                File file = new File(this.getFilesDir(), fileNames.get(i));

                imageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            }
        }
    }
}