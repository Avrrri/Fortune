package com.example.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

    }
    public void nextClassMethod (View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
