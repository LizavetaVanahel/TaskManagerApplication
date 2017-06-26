package com.example.vanahel.tasksmanagerapplication.event.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.vanahel.tasksmanagerapplication.NewTaskActivity;
import com.example.vanahel.tasksmanagerapplication.R;
import com.example.vanahel.tasksmanagerapplication.constants.ExtrasConstants;
import com.example.vanahel.tasksmanagerapplication.task.Task;

public class TaskListItemClickListener implements AdapterView.OnItemClickListener {

    private Activity activity;

    public TaskListItemClickListener (Activity activity){
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Task task = (Task) adapterView.getItemAtPosition(position);
        TextView title = (TextView) adapterView.findViewById(R.id.title_item);
        TextView description = (TextView) adapterView.findViewById(R.id.description_item);
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        Intent intent = new Intent(activity, NewTaskActivity.class);
        intent.putExtra(ExtrasConstants.TASK_EXTRAS, task);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivityForResult(intent, 0);
    }
}
