package com.example.fitmefriend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.fitmefriend.adapter.PantsAdapter;
import com.example.fitmefriend.adapter.ShirtsAdapter;

import java.util.ArrayList;
import java.util.List;

public class OutfitSwipeActivity extends AppCompatActivity {
    private RecyclerView shirtRecyclerView, pantRecyclerView;
    private List<Pants> pantsList = new ArrayList<>();
    private List<Shirts> shirtsList = new ArrayList<>();
    private int lastPosition, lastPositionS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit_swipe);




        shirtRecyclerView = findViewById(R.id.shirtRecyclerView);
        shirtRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManagerS = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        shirtRecyclerView.setLayoutManager(layoutManagerS);
        SharedPreferences getPrefsS = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        lastPositionS = getPrefsS.getInt("lastPos", 0);
        shirtRecyclerView.scrollToPosition(lastPositionS);
        shirtRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                lastPositionS = layoutManagerS.findFirstVisibleItemPosition();
                Log.i("shirt",(String.valueOf(lastPositionS)));
            }
        });

        shirtsList.add(new Shirts(Integer.toString(R.drawable.sampleimage)));
        shirtsList.add(new Shirts(Integer.toString(R.drawable.sampleimage)));
        shirtsList.add(new Shirts(Integer.toString(R.drawable.sampleimage)));
        ShirtsAdapter shirtsAdapter = new ShirtsAdapter(shirtsList, this);
        shirtRecyclerView.setAdapter(shirtsAdapter);
        SnapHelper z = new LinearSnapHelper();
        z.attachToRecyclerView(shirtRecyclerView);



        pantRecyclerView = findViewById(R.id.pantRecyclerView);
        pantRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        pantRecyclerView.setLayoutManager(layoutManager);
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        lastPosition = getPrefs.getInt("lastPos", 0);
        pantRecyclerView.scrollToPosition(lastPosition);
        pantRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                lastPosition = layoutManager.findFirstVisibleItemPosition();
                Log.i("pant",(String.valueOf(lastPosition)));
            }
        });


        pantsList.add(new Pants(Integer.toString(R.drawable.sampleimage)));
        pantsList.add(new Pants(Integer.toString(R.drawable.sampleimage)));
        pantsList.add(new Pants(Integer.toString(R.drawable.sampleimage)));
        pantsList.add(new Pants(Integer.toString(R.drawable.sampleimage)));
        pantsList.add(new Pants(Integer.toString(R.drawable.sampleimage)));
        PantsAdapter pantsAdapter = new PantsAdapter(pantsList, this);
        pantRecyclerView.setAdapter(pantsAdapter);
        SnapHelper H = new LinearSnapHelper();
        H.attachToRecyclerView(pantRecyclerView);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //save position in sharedpreferenses on destroy
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor e = getPrefs.edit();
        e.putInt("lastPos", lastPosition);
        e.putInt("shirtLast", lastPositionS);
        e.apply();
    }
   // public void makeOutfit(View view){

  //  }

    public void saveWardrobe(View view) {
        Intent intent = new Intent(OutfitSwipeActivity.this, SavedOutfitsActivity.class);
        startActivity(intent);

    }
    public RecyclerView getShirtRecyclerView() {
        return shirtRecyclerView;
    }

    public void setShirtRecyclerView(RecyclerView shirtRecyclerView) {
        this.shirtRecyclerView = shirtRecyclerView;
    }

    public RecyclerView getPantRecyclerView() {
        return pantRecyclerView;
    }

    public void setPantRecyclerView(RecyclerView pantRecyclerView) {
        this.pantRecyclerView = pantRecyclerView;
    }




}
