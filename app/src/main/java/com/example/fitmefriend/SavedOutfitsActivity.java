package com.example.fitmefriend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.fitmefriend.adapter.OutfitsAdapter;
import com.example.fitmefriend.adapter.PantsAdapter;

import java.util.ArrayList;

public class SavedOutfitsActivity extends AppCompatActivity {
    private RecyclerView outfitRecycler;
    public static ArrayList<Outfits> outfitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_outfits);

        outfitRecycler = findViewById(R.id.outfitRecyclerView);
        outfitRecycler.setHasFixedSize(true);
        final LinearLayoutManager layoutManagerS = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        outfitRecycler.setLayoutManager(layoutManagerS);
        OutfitsAdapter outfitsAdapter = new OutfitsAdapter(outfitList, this);
        outfitRecycler.setAdapter(outfitsAdapter);
        Log.i("shreya", String.valueOf(outfitList));


    }
}