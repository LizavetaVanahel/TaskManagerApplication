package com.example.vanahel.tasksmanagerapplication.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.List;

public class TasksAsyncTaskLoader extends AsyncTaskLoader< List<Task> > {

    private TaskDAO taskDAO;

    public TasksAsyncTaskLoader(Context context, TaskDAO taskDAO) {
        super(context);
        this.taskDAO = taskDAO;
    }

    @Override
    public List<Task> loadInBackground() {
        return taskDAO.getTasks();
    }
}