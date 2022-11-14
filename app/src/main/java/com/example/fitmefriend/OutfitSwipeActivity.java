package com.example.fitmefriend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fitmefriend.adapter.PantsAdapter;
import com.example.fitmefriend.adapter.ShirtsAdapter;

import java.util.ArrayList;
import java.util.List;

public class OutfitSwipeActivity extends AppCompatActivity {

    private RecyclerView shirtRecyclerView, pantRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit_swipe);
        shirtRecyclerView = findViewById(R.id.shirtRecyclerView);
        shirtRecyclerView.setHasFixedSize(true);
        shirtRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        List<Shirts> shirtsList = new ArrayList<>();

        shirtsList.add(new Shirts(R.drawable.sampleimage));
        ShirtsAdapter shirtsAdapter = new ShirtsAdapter(shirtsList);
        shirtRecyclerView.setAdapter(shirtsAdapter);

        pantRecyclerView = findViewById(R.id.pantRecyclerView);
        pantRecyclerView.setHasFixedSize(true);
        pantRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        List<Pants> pantsList = new ArrayList<>();

        pantsList.add(new Pants(R.drawable.sampleimage));
        PantsAdapter pantsAdapter = new PantsAdapter(pantsList);
        pantRecyclerView.setAdapter(pantsAdapter);
    }



}