package com.example.vanahel.tasksmanagerapplication.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.List;

public class FavoriteAsyncTaskLoader extends AsyncTaskLoader< List<Task> > {

    private TaskDAO taskDAO;

    public FavoriteAsyncTaskLoader( Context context, TaskDAO taskDAO ) {
        super(context);
        this.taskDAO = taskDAO;
    }

    @Override
    public List<Task> loadInBackground() {
        return taskDAO.getFavoriteTasks();
    }
}
