package com.example.fitmefriend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fitmefriend.adapter.PantsAdapter;
import com.example.fitmefriend.adapter.ShirtsAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OutfitSwipeActivity extends AppCompatActivity {
    Button click;
    private RecyclerView shirtRecyclerView, pantRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit_swipe);
        click = findViewById(R.id.ssButton);




        shirtRecyclerView = findViewById(R.id.shirtRecyclerView);
        shirtRecyclerView.setHasFixedSize(true);
        shirtRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        List<Shirts> shirtsList = new ArrayList<>();

        shirtsList.add(new Shirts(Integer.toString(R.drawable.sampleimage)));
        shirtsList.add(new Shirts(Integer.toString(R.drawable.sampleimage)));
        shirtsList.add(new Shirts(Integer.toString(R.drawable.sampleimage)));
        ShirtsAdapter shirtsAdapter = new ShirtsAdapter(shirtsList);
        shirtRecyclerView.setAdapter(shirtsAdapter);

        pantRecyclerView = findViewById(R.id.pantRecyclerView);
        pantRecyclerView.setHasFixedSize(true);
        pantRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        List<Pants> pantsList = new ArrayList<>();

        pantsList.add(new Pants(Integer.toString(R.drawable.sampleimage)));
        pantsList.add(new Pants(Integer.toString(R.drawable.sampleimage)));
        pantsList.add(new Pants(Integer.toString(R.drawable.sampleimage)));
        PantsAdapter pantsAdapter = new PantsAdapter(pantsList);
        pantRecyclerView.setAdapter(pantsAdapter);
    }

    public void saveWardrobe(View view) {
        Intent intent = new Intent(OutfitSwipeActivity.this, SavedOutfitsActivity.class);
        startActivity(intent);

    }





}
