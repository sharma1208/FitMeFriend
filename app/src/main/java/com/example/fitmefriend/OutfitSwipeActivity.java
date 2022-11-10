package com.example.fitmefriend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OutfitSwipeActivity extends AppCompatActivity {

    private RecyclerView shirtRecyclerView, pantRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit_swipe);
        shirtRecyclerView = findViewById(R.id.shirtRecyclerView);
        shirtRecyclerView.setHasFixedSize(true);
        pantRecyclerView = findViewById(R.id.pantRecyclerView);
        pantRecyclerView.setHasFixedSize(true);
    }
    public void saveWardrobe(View view) {
        Intent intent = new Intent(OutfitSwipeActivity.this, SavedOutfitsActivity.class);
        startActivity(intent);

    }



}