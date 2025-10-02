package com.example.uztodo;

public class Task {
    private String taskText;

    public Task(String taskText) {
        this.taskText = taskText;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }
}
