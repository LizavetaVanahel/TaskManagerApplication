package com.example.vanahel.tasksmanagerapplication.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.List;

public class FavoriteAsyncTaskLoader extends AsyncTaskLoader<AsyncTaskResult< List<Task> >>{

    private TaskDAO taskDAO;

    public FavoriteAsyncTaskLoader( Context context, TaskDAO taskDAO ) {
        super(context);
        this.taskDAO = taskDAO;
    }

    @Override
    public AsyncTaskResult< List<Task> > loadInBackground() {
        AsyncTaskResult<List<Task>> resultWrapper = new AsyncTaskResult<>();
        try {
            resultWrapper.setResult( taskDAO.getFavoriteTasks() );
        } catch ( RuntimeException error ) {
            resultWrapper.setError( error );
        }

        return resultWrapper;
    }
}

