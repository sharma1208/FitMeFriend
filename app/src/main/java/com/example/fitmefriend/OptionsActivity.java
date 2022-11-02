package com.example.fitmefriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }
    public void uploadButtonClicked(View view) {
        Intent intent = new Intent(OptionsActivity.this, UploadActivity.class);
        startActivity(intent);
    }
    public void swipeButtonClicked(View view) {
        Intent intent = new Intent(OptionsActivity.this, OutfitSwipeActivity.class);
        startActivity(intent);
    }
    public void menuButtonClicked(View view) {
        Intent intent = new Intent(OptionsActivity.this, WardrobeMenuActivity.class);
        startActivity(intent);


    }
}