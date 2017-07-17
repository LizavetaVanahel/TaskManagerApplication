package com.example.vanahel.tasksmanagerapplication.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.vanahel.tasksmanagerapplication.NewTaskActivity;
import com.example.vanahel.tasksmanagerapplication.constants.ExtrasConstants;
import com.example.vanahel.tasksmanagerapplication.contracts.TaskArrayAdapterContract;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.io.IOException;

public class TaskArrayAdapterMenu implements TaskArrayAdapterContract.Menu {

    private Activity activity;
    private Task task;
    private Fragment fragment;

    public TaskArrayAdapterMenu(Activity activity, Task task, Fragment fragment){
        this.activity = activity;
        this.task = task;
        this.fragment = fragment;
    }

    @Override
    public void edit() {
        Intent intent = new Intent(activity, NewTaskActivity.class);
        intent.putExtra(ExtrasConstants.TASK_EXTRAS, task);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivityForResult(intent, 0);
    }

    @Override
    public void delete() {
        try {
            DAOManager.getInstance().getTaskDAO().delete(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fragment.onResume();
    }

    @Override
    public void addToFavorite() {
        Task favoriteTask = new Task (task.getTitle(), task.getDescription(), true, task.getId());
        try {
            DAOManager.getInstance().getTaskDAO().updateTask(favoriteTask, task);
        } catch (IOException e) {
        }
        fragment.onResume();
    }

    @Override
    public void removeFromFavorite() {
        Task simpleTask = new Task (task.getTitle(), task.getDescription(), false, task.getId());
        try {
            DAOManager.getInstance().getTaskDAO().updateTask(simpleTask, task);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fragment.onResume();
    }
}
