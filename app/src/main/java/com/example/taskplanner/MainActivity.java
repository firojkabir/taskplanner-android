package com.example.taskplanner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskplanner.controller.TaskController;
import com.example.taskplanner.model.Task;
import com.example.taskplanner.view.TaskAdapter;
import com.example.taskplanner.view.TaskRecyclerAdapter;

import java.sql.Date;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText;
    private Button addTaskButton;
    private RecyclerView taskRecyclerView;

    private TaskController taskController;
    private TaskRecyclerAdapter taskRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        addTaskButton = findViewById(R.id.addTaskButton);
        taskRecyclerView = findViewById(R.id.taskRecyclerView);

        // Initialize TaskController
        taskController = new TaskController();

        // Initialize TaskRecyclerAdapter
        taskRecyclerAdapter = new TaskRecyclerAdapter(taskController.getTasks());

        // Set up RecyclerView
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskRecyclerView.setAdapter(taskRecyclerAdapter);

        // Add Task Button Click Listener
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        // Set up item click listener for RecyclerView
        taskRecyclerAdapter.setOnItemClickListener(new TaskRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showStatusDialog(position);
            }
        });
    }

    private void addTask() {
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please enter title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the current date as the due date
        Calendar calendar = Calendar.getInstance();
        Date dueDate = new Date(calendar.getTimeInMillis());

        // Add the task using TaskController
        Task newTask = taskController.addTask(title, description, dueDate, new TaskAdapter());

        if (newTask != null) {
            // Notify the adapter that the data set has changed
            taskRecyclerAdapter.notifyDataSetChanged();

            // Clear the input fields
            titleEditText.setText("");
            descriptionEditText.setText("");

            Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add task", Toast.LENGTH_SHORT).show();
        }
    }

    private void showStatusDialog(int position) {
        // Create a dialog to allow the user to update the task status
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Task Status");

        // List of status options
        String[] statusOptions = {"Pending", "InProgress", "Completed"};
        builder.setItems(statusOptions, (dialog, which) -> {
            String newStatus = statusOptions[which];
            taskController.updateTaskStatus(position, newStatus);
            taskRecyclerAdapter.notifyDataSetChanged(); // Refresh the RecyclerView
            Toast.makeText(this, "Task status updated to " + newStatus, Toast.LENGTH_SHORT).show();
        });

        builder.create().show();
    }
}