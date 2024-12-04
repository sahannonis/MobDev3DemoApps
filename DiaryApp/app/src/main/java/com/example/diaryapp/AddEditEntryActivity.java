package com.example.diaryapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditEntryActivity extends AppCompatActivity {

    private EditText entryEditText;
    private Button saveButton, deleteButton;
    private int entryPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_entry);

        entryEditText = findViewById(R.id.entry_edit_text);
        saveButton = findViewById(R.id.save_button);
        deleteButton = findViewById(R.id.delete_button);

        // Get data passed from MainActivity
        String entry = getIntent().getStringExtra("entry");
        entryPosition = getIntent().getIntExtra("position", -1);

        if (entry != null) {
            entryEditText.setText(entry);
            deleteButton.setVisibility(View.VISIBLE);
        }

        saveButton.setOnClickListener(v -> {
            String newEntry = entryEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(newEntry)) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("entry", newEntry);
                resultIntent.putExtra("position", entryPosition);
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(this, "Entry cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("entry", ""); // Pass an empty string to signal deletion
            resultIntent.putExtra("position", entryPosition);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

    }
}
