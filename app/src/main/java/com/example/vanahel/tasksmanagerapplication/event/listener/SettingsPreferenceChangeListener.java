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

public class SettingsPreferenceChangeListener implements Preference.OnPreferenceChangeListener  {

    private Context context;
    private TaskDAO taskDAO;

    public SettingsPreferenceChangeListener (Context context, TaskDAO taskDAO){
        this.context = context;
        this.taskDAO = taskDAO;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        switch(preference.getKey()) {
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
            default:
                break;
        }
        return true;
    }
}
