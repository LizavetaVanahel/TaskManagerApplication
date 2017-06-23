package com.example.vanahel.tasksmanagerapplication.event.listener;

import android.content.Context;
import android.preference.Preference;

import com.example.vanahel.tasksmanagerapplication.constants.SettingsConstants;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.dao.DatabaseDAO;
import com.example.vanahel.tasksmanagerapplication.dao.ExternalStorageDAO;
import com.example.vanahel.tasksmanagerapplication.dao.InternalStorageDAO;
import com.example.vanahel.tasksmanagerapplication.dao.MemoryDAO;
import com.example.vanahel.tasksmanagerapplication.dao.SharedPreferencesDAO;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;


public class TaskSettingsChangeListener implements Preference.OnPreferenceChangeListener {

    private Context context;
    private TaskDAO taskDAO;

    public TaskSettingsChangeListener (Context context, TaskDAO taskDAO){
        this.context = context;
        this.taskDAO = taskDAO;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        switch (o.toString()) {
            case SettingsConstants.SHARED_PREFERENCES:
                taskDAO = new SharedPreferencesDAO(context);
                DAOManager.getInstance().setTaskDAO(taskDAO);
                break;
            case SettingsConstants.DATABASE:
                taskDAO = new DatabaseDAO(context);
                DAOManager.getInstance().setTaskDAO(taskDAO);
                break;
            case SettingsConstants.INTERNAL_STORAGE:
                taskDAO = new InternalStorageDAO(context);
                DAOManager.getInstance().setTaskDAO(taskDAO);
                break;
            case SettingsConstants.EXTERNAL_STORAGE:
                taskDAO = new ExternalStorageDAO();
                DAOManager.getInstance().setTaskDAO(taskDAO);
                break;
            case SettingsConstants.MEMORY:
                taskDAO = new MemoryDAO();
                DAOManager.getInstance().setTaskDAO(taskDAO);
                break;

        }
        return true;

    }

}
