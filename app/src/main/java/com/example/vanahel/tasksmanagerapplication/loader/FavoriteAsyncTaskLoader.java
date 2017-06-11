package com.example.vanahel.tasksmanagerapplication.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvkoleda on 04.06.17.
 */

public class FavoriteAsyncTaskLoader extends AsyncTaskLoader< List<Task> > {

    private TaskDAO taskDAO;
    private Boolean isFavorite;

    public FavoriteAsyncTaskLoader(Context context, TaskDAO taskDAO, boolean isFavorite) {
        super(context);
        this.taskDAO = taskDAO;
        this.isFavorite = isFavorite;

    }

    @Override
    public List<Task> loadInBackground() {
        List<Task> list = null;
            list = new ArrayList<>();
            list.addAll(taskDAO.getFavoriteTasks());
        return list;
    }
}
