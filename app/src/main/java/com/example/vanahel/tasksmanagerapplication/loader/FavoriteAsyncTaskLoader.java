package com.example.vanahel.tasksmanagerapplication.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAsyncTaskLoader extends AsyncTaskLoader< List<Task> > {

    private TaskDAO taskDAO;

    public FavoriteAsyncTaskLoader(Context context, TaskDAO taskDAO) {
        super(context);
        this.taskDAO = taskDAO;
    }

    @Override
    public List<Task> loadInBackground() {
        List<Task> favoriteTasksList;
        favoriteTasksList = new ArrayList<>();
        List<Task> allTasksList;
        allTasksList = new ArrayList<>();
        allTasksList.addAll(taskDAO.getTasks());

        for ( Task currentTask : allTasksList ) {
            if (currentTask.getFavorite()) {
                favoriteTasksList.add(currentTask);
            }
        }
        return favoriteTasksList;
    }
}
