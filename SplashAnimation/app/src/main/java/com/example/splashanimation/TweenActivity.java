package com.example.splashanimation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TweenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);

        ImageView imageView = findViewById(R.id.tween_view);
        Animation tweenAnimation = AnimationUtils.loadAnimation(this, R.anim.tween_animation);
        imageView.startAnimation(tweenAnimation);
    }
}

//package com.example.splashanimation;
//
//import android.os.Bundle;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.Button;
//import android.widget.ImageView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class TweenActivity extends AppCompatActivity {
//
//    private Animation tweenAnimation;
//    private ImageView imageView;
//    private Button startButton;
//    private Button stopButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tween);
//
//        imageView = findViewById(R.id.tween_view);
//        startButton = findViewById(R.id.start_button);
//        stopButton = findViewById(R.id.stop_button);
//
//        // Load the tween animation
//        tweenAnimation = AnimationUtils.loadAnimation(this, R.anim.tween_animation);
//
//        // Start Button Listener
//        startButton.setOnClickListener(v -> {
//            imageView.startAnimation(tweenAnimation);
//            startButton.setVisibility(View.GONE);
//            stopButton.setVisibility(View.VISIBLE);
//        });
//
//        // Stop Button Listener
//        stopButton.setOnClickListener(v -> {
//            imageView.clearAnimation();
//            stopButton.setVisibility(View.GONE);
//            startButton.setVisibility(View.VISIBLE);
//        });
//    }
//}

