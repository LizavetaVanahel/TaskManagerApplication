package com.example.vanahel.tasksmanagerapplication;

import android.app.Application;

import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.dao.DatabaseDAO;

public class TasksApplication extends Application {

    public void onCreate (){
        super.onCreate();
        DAOManager.getInstance().setTaskDAO(new DatabaseDAO(this));

    }

}
