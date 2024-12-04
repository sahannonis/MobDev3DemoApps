package com.example.splashanimation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView animationView = findViewById(R.id.animation_view);
        animationView.setBackgroundResource(R.drawable.frame_animation);

        AnimationDrawable animationDrawable = (AnimationDrawable) animationView.getBackground();
        animationDrawable.start();

        // Redirect to the main activity after animation
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, TweenActivity.class);
            startActivity(intent);
            finish();
        }, 3000); // Adjust time based on total animation duration
    }
}
