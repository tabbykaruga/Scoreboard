package com.example.scoreboard;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashscreenActivity.this, MainActivity.class));
                    }
                }, 1000);
    }
    @Override
    public void onBackPressed() {}
}