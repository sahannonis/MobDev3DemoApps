package com.example.hotelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView hotelListView;
    private Button myBookingsButton;
    private ArrayList<String> hotelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hotelListView = findViewById(R.id.hotel_list);
        myBookingsButton = findViewById(R.id.my_bookings_button);

        // Sample list of hotels
        hotelList = new ArrayList<>();
        hotelList.add("Hotel Sunshine - New York - $150/night");
        hotelList.add("Ocean View - Miami - $200/night");
        hotelList.add("Mountain Retreat - Denver - $120/night");
        hotelList.add("City Lights Hotel - Las Vegas - $180/night");

        // Set up the ListView with an adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hotelList);
        hotelListView.setAdapter(adapter);

        // On clicking a hotel, go to the booking screen
        hotelListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedHotel = hotelList.get(position);
            Intent intent = new Intent(MainActivity.this, BookingActivity.class);
            intent.putExtra("hotel", selectedHotel);
            startActivity(intent);
        });

        // My Bookings button click listener
        myBookingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyBookingsActivity.class);
            startActivity(intent);
        });
    }

    //to pass an image to the booking use this
    //Add Images for Hotels: Store images in the res/drawable folder with names like hotel1.png, hotel2.png, etc.
//    hotelListView.setOnItemClickListener((parent, view, position, id) -> {
//        String selectedHotel = hotelList.get(position);
//        int[] hotelImages = {
//                R.drawable.hotel1,
//                R.drawable.hotel2,
//                R.drawable.hotel3,
//                R.drawable.hotel4
//        };
//
//        Intent intent = new Intent(MainActivity.this, BookingActivity.class);
//        intent.putExtra("hotel", selectedHotel);
//        intent.putExtra("hotelImage", hotelImages[position]); // Pass the image resource ID
//        startActivity(intent);
//    });
}
