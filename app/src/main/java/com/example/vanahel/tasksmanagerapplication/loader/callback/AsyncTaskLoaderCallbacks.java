package com.example.vanahel.tasksmanagerapplication.loader.callback;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.example.vanahel.tasksmanagerapplication.adapter.TasksArrayAdapter;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.loader.AsyncTaskResult;
import com.example.vanahel.tasksmanagerapplication.loader.TasksAsyncTaskLoader;
import com.example.vanahel.tasksmanagerapplication.presenter.TabFragmentPresenter;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.ArrayList;
import java.util.List;

public class AsyncTaskLoaderCallbacks implements LoaderManager.LoaderCallbacks< AsyncTaskResult< List<Task> > > {

    private Activity activity;
    private TaskDAO taskDAO;
    private TasksArrayAdapter tasksArrayAdapter;
    private TabFragmentPresenter tabFragmentPresenter;

    public AsyncTaskLoaderCallbacks ( Activity activity, TaskDAO taskDAO,
                                     TasksArrayAdapter tasksArrayAdapter,
                                     TabFragmentPresenter tabFragmentPresenter ){
        this.activity = activity;
        this.taskDAO = taskDAO;
        this.tasksArrayAdapter = tasksArrayAdapter;
        this.tabFragmentPresenter = tabFragmentPresenter;
    }


    @Override
    public Loader<AsyncTaskResult< List<Task> >> onCreateLoader( int i, Bundle bundle ) {
        return new TasksAsyncTaskLoader( activity, taskDAO );
    }

    @Override
    public void onLoadFinished( Loader< AsyncTaskResult< List<Task> > > loader,
                                AsyncTaskResult< List<Task> > wrapper ) {
        if ( wrapper.getError() == null ){
            tasksArrayAdapter.setTask( wrapper.getResult() );
        } else {
            tabFragmentPresenter.getMessage( wrapper.getError().getMessage() );
        }
    }

    @Override
    public void onLoaderReset( Loader< AsyncTaskResult< List<Task> > > loader ) {
        tasksArrayAdapter.setTask( new ArrayList<Task>() );
    }



}
