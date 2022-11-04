package com.example.fitmefriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OutfitSwipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit_swipe);
    }
    public void saveWardrobe(View view) {
        Intent intent = new Intent(OutfitSwipeActivity.this, SavedOutfitsActivity.class);
        startActivity(intent);

    }
}