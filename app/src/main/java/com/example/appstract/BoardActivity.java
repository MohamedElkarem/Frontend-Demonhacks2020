package com.example.appstract;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;

public class BoardActivity extends AppCompatActivity {

    private TextView mTextView;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);


        //The below is a proof of concept in how to extract the files once they are loaded on disk
        mTextView = (TextView) findViewById(R.id.text);

        File file = new File(this.getFilesDir(), "mans.jpg");

        Boolean x = file.exists();

        // Create a ConstraintLayout in which to add the ImageView
        constraintLayout = new ConstraintLayout(this);

        // Instantiate an ImageView and define its properties
        ImageView i = new ImageView(this);
        i.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
        i.setContentDescription("bruv");

        // set the ImageView bounds to match the Drawable's dimensions
        i.setAdjustViewBounds(true);
        i.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // Add the ImageView to the layout and set the layout as the content view.
        constraintLayout.addView(i);

        setContentView(constraintLayout);
    }
}