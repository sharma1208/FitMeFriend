package com.example.fitmefriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }
    public void logInButtonClicked(View view) {
        Intent intent = new Intent(LogInActivity.this, OptionsActivity.class);
        startActivity(intent);


    }
}