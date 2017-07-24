package com.example.vanahel.tasksmanagerapplication.loader.callback;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.example.vanahel.tasksmanagerapplication.adapter.TasksArrayAdapter;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.loader.TasksAsyncTaskLoader;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.ArrayList;
import java.util.List;

public class AsyncTaskLoaderCallbacks implements LoaderManager.LoaderCallbacks< List<Task> > {

    private Activity activity;
    private TaskDAO taskDAO;
    private TasksArrayAdapter tasksArrayAdapter;

    public AsyncTaskLoaderCallbacks ( Activity activity, TaskDAO taskDAO,
                                     TasksArrayAdapter tasksArrayAdapter ){
        this.activity = activity;
        this.taskDAO = taskDAO;
        this.tasksArrayAdapter = tasksArrayAdapter;
    }

    @Override
    public Loader<List<Task>> onCreateLoader( int i, Bundle bundle ) {
        return new TasksAsyncTaskLoader( activity, taskDAO );
    }

    @Override
    public void onLoadFinished( Loader<List<Task> > loader, List<Task> task ) {
        tasksArrayAdapter.setTask(task);
    }

    @Override
    public void onLoaderReset( Loader< List<Task> > loader ) {
        tasksArrayAdapter.setTask( new ArrayList<Task>() );
    }

}
