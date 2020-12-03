package com.example.battlebot_programming_assignment_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BotAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_about);
        getSupportActionBar().setTitle("About BattleBot");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}