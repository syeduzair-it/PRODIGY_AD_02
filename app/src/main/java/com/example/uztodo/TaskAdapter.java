package com.example.uztodo;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context context;
    private ArrayList<Task> taskList;

    public TaskAdapter(Context context, ArrayList<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.txtTask.setText(task.getTaskText());

        // Edit Task
        holder.btnEdit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Edit Task");

            final EditText input = new EditText(context);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            input.setText(task.getTaskText());
            builder.setView(input);

            builder.setPositiveButton("Save", (dialog, which) -> {
                task.setTaskText(input.getText().toString());
                notifyItemChanged(position);
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            builder.show();
        });

        // Delete Task
        holder.btnDelete.setOnClickListener(v -> {
            taskList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, taskList.size());
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView txtTask;
        Button btnEdit, btnDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTask = itemView.findViewById(R.id.txtTask);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
