package com.example.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private EditText taskInput;
    private Button addButton;
    private ListView taskListView;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> taskAdapter;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "ToDoListPrefs";
    private static final String TASKS_KEY = "Tasks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.task_input);
        addButton = findViewById(R.id.add_button);
        taskListView = findViewById(R.id.task_list);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Load tasks from SharedPreferences
        taskList = new ArrayList<>(loadTasks());

        // Set up the ListView with an adapter
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        taskListView.setAdapter(taskAdapter);

        // Add button click listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = taskInput.getText().toString().trim();
                if (!TextUtils.isEmpty(task)) {
                    taskList.add(task);
                    taskAdapter.notifyDataSetChanged();
                    saveTasks();
                    taskInput.setText("");
                    Toast.makeText(MainActivity.this, "Task added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Enter a task", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Long click to remove a task
        taskListView.setOnItemLongClickListener((parent, view, position, id) -> {
            taskList.remove(position);
            taskAdapter.notifyDataSetChanged();
            saveTasks();
            Toast.makeText(MainActivity.this, "Task removed", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    private Set<String> loadTasks() {
        return sharedPreferences.getStringSet(TASKS_KEY, new HashSet<>());
    }

    private void saveTasks() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> taskSet = new HashSet<>(taskList);
        editor.putStringSet(TASKS_KEY, taskSet);
        editor.apply();
    }
}
