package com.example.vanahel.tasksmanagerapplication.loader.callback;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.example.vanahel.tasksmanagerapplication.adapter.FavoriteTasksArrayAdapter;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.loader.FavoriteAsyncTaskLoader;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvkoleda on 28.05.17.
 */

public class FavoriteAsyncTasksLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<Task>>  {

    private Context context;
    private TaskDAO taskDAO;
    private FavoriteTasksArrayAdapter favoriteTasksArrayAdapter;

    public FavoriteAsyncTasksLoaderCallbacks (Context context, TaskDAO taskDAO, FavoriteTasksArrayAdapter favoriteTasksArrayAdapter){
        this.context = context;
        this.taskDAO = taskDAO;
        this.favoriteTasksArrayAdapter = favoriteTasksArrayAdapter;
    }

    @Override
    public Loader<List<Task>> onCreateLoader(int i, Bundle bundle) {
        return new FavoriteAsyncTaskLoader(context, taskDAO, true);
    }

    @Override
    public void onLoadFinished(Loader<List<Task>> loader, List<Task> task) {
        favoriteTasksArrayAdapter.setTask(task);
    }

    @Override
    public void onLoaderReset(Loader<List<Task>> loader) {
        favoriteTasksArrayAdapter.setTask(new ArrayList<Task>());
    }
}
