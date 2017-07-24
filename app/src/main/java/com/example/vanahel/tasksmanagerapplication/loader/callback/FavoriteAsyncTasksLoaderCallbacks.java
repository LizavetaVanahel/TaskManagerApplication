package com.example.vanahel.tasksmanagerapplication.loader.callback;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.example.vanahel.tasksmanagerapplication.adapter.TasksArrayAdapter;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.loader.FavoriteAsyncTaskLoader;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAsyncTasksLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<Task>> {

    private Activity activity;
    private TaskDAO taskDAO;
    private TasksArrayAdapter favoriteTasksArrayAdapter;

    public FavoriteAsyncTasksLoaderCallbacks ( Activity activity, TaskDAO taskDAO,
                                              TasksArrayAdapter favoriteTasksArrayAdapter ){
        this.activity = activity;
        this.taskDAO = taskDAO;
        this.favoriteTasksArrayAdapter = favoriteTasksArrayAdapter;
    }

    @Override
    public Loader<List<Task>> onCreateLoader( int i, Bundle bundle ) {
        return new FavoriteAsyncTaskLoader( activity, taskDAO );
    }

    @Override
    public void onLoadFinished( Loader<List<Task>> loader, List<Task> task ) {
                favoriteTasksArrayAdapter.setTask(task);
    }

    @Override
    public void onLoaderReset( Loader<List<Task>> loader ) {
        favoriteTasksArrayAdapter.setTask( new ArrayList<Task>() );
    }
}
