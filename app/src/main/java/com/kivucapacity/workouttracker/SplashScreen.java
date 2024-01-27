package com.kivucapacity.workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // To hide status bar :
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent iMain = new Intent(this, MainActivity.class);
        new Handler().postDelayed(() -> {
            startActivity(iMain);
            finish();
        }, 6000);

    }
}