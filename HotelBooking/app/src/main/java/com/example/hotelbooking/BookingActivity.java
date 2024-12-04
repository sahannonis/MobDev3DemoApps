package com.example.hotelbooking;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class BookingActivity extends AppCompatActivity {

    private TextView hotelDetailsTextView;
    private Button bookButton;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "HotelBookingPrefs";
    private static final String BOOKINGS_KEY = "Bookings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        hotelDetailsTextView = findViewById(R.id.hotel_details);
        bookButton = findViewById(R.id.book_button);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Get the selected hotel details
        String hotelDetails = getIntent().getStringExtra("hotel");
//        int hotelImageResId = getIntent().getIntExtra("hotelImage", R.drawable.default_image);
        hotelDetailsTextView.setText(hotelDetails);
//        hotelImageView.setImageResource(hotelImageResId);

        // Booking button click listener
        bookButton.setOnClickListener(v -> {
            saveBooking(hotelDetails);
            Toast.makeText(BookingActivity.this, "Hotel booked successfully!", Toast.LENGTH_SHORT).show();
        });
    }

    private void saveBooking(String hotelDetails) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> bookings = sharedPreferences.getStringSet(BOOKINGS_KEY, new HashSet<>());
        bookings.add(hotelDetails);
        editor.putStringSet(BOOKINGS_KEY, bookings);
        editor.apply();
    }
}
