package com.example.hotelbooking;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyBookingsActivity extends AppCompatActivity {

    private ListView bookingsListView;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "HotelBookingPrefs";
    private static final String BOOKINGS_KEY = "Bookings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        bookingsListView = findViewById(R.id.bookings_list);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Load bookings
        Set<String> bookingsSet = sharedPreferences.getStringSet(BOOKINGS_KEY, new HashSet<>());
        ArrayList<String> bookingsList = new ArrayList<>(bookingsSet);

        // Set up the ListView with an adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookingsList);
        bookingsListView.setAdapter(adapter);
    }
}
