package com.example.vanahel.tasksmanagerapplication;

import android.app.Application;

import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.dao.DatabaseDAO;
import com.example.vanahel.tasksmanagerapplication.task.Task;

/**
 * Created by dvkoleda on 22.05.17.
 */

public class TasksApplication extends Application {
    private DatabaseDAO databaseDAO;
    private Task task;

    public void onCreate (){
        super.onCreate();
        DAOManager.getInstance().setTaskDAO(new DatabaseDAO(this));
    }

}
