package com.example.uztodo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTask;
    Button btnAdd;
    RecyclerView recyclerViewTasks;
    TaskAdapter adapter;
    ArrayList<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTask = findViewById(R.id.editTask);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        taskList = new ArrayList<>();
        adapter = new TaskAdapter(this, taskList);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setAdapter(adapter);

        // Add Task
        btnAdd.setOnClickListener(v -> {
            String taskText = editTask.getText().toString().trim();
            if (!taskText.isEmpty()) {
                taskList.add(new Task(taskText));
                adapter.notifyItemInserted(taskList.size() - 1);
                recyclerViewTasks.scrollToPosition(taskList.size() - 1);
                editTask.setText(""); // Clear input
            }
        });
    }
}
