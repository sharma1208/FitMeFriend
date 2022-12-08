package com.example.fitmefriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class OptionsActivity extends AppCompatActivity {

    public final String TAG = "Denna";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    public void showMemories(View view) {

    }

    public void UploadPicture(View view) {
        Intent intent = new Intent(OptionsActivity.this, UploadActivity.class);
        startActivity(intent);
    }
    public void Swipe(View view) {
        Intent intent = new Intent(OptionsActivity.this, OutfitSwipeActivity.class);
        startActivity(intent);
    }
    public void WardrobeMenu(View view) {
        Intent intent = new Intent(OptionsActivity.this, SavedOutfitsActivity.class);
        startActivity(intent);
    }

    public void logOutClicked(View view) {
        LogInActivity.firebaseHelper.logOutUser();
        Log.i(TAG, "user logged out");
        Intent intent = new Intent(OptionsActivity.this, LogInActivity.class);
        startActivity(intent);
    }
}
