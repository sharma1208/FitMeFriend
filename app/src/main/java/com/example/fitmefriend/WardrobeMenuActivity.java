package com.example.fitmefriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WardrobeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe_menu);
    }
    public void savedOutfitClicked(View view) {
        Intent intent = new Intent(WardrobeMenuActivity.this, SavedOutfitsActivity.class);
        startActivity(intent);


    }
}