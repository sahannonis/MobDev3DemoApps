package com.example.diaryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView diaryListView;
    private Button addEntryButton;
    private ArrayList<String> diaryEntries;
    private ArrayAdapter<String> diaryAdapter;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "DiaryPrefs";
    private static final String DIARY_KEY = "DiaryEntries";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diaryListView = findViewById(R.id.diary_list);
        addEntryButton = findViewById(R.id.add_entry_button);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Load diary entries from SharedPreferences
        diaryEntries = loadDiaryEntries();

        // Set up the ListView with an adapter
        diaryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diaryEntries);
        diaryListView.setAdapter(diaryAdapter);

        // Add Entry button click listener
        addEntryButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditEntryActivity.class);
            startActivityForResult(intent, 1);
        });

        // Edit or Delete on long press
        diaryListView.setOnItemLongClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, AddEditEntryActivity.class);
            intent.putExtra("entry", diaryEntries.get(position));
            intent.putExtra("position", position);
            startActivityForResult(intent, 2);
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String entry = data.getStringExtra("entry");
            int position = data.getIntExtra("position", -1);

            if (requestCode == 1) { // Add Entry
                diaryEntries.add(entry);
            } else if (requestCode == 2 && position != -1) { // Edit or Delete Entry
                if (entry.isEmpty()) { // Handle deletion
                    diaryEntries.remove(position);
                } else {
                    diaryEntries.set(position, entry); // Update the entry
                }
            }

            saveDiaryEntries();
            diaryAdapter.notifyDataSetChanged();
        }
    }


    private ArrayList<String> loadDiaryEntries() {
        ArrayList<String> entries = new ArrayList<>();
        String json = sharedPreferences.getString(DIARY_KEY, "[]");
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                entries.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entries;
    }

    private void saveDiaryEntries() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        JSONArray jsonArray = new JSONArray(diaryEntries);
        editor.putString(DIARY_KEY, jsonArray.toString());
        editor.apply();
    }
}
