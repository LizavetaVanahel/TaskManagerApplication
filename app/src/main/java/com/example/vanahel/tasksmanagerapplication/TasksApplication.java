package com.example.vanahel.tasksmanagerapplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.vanahel.tasksmanagerapplication.constants.SettingsConstants;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.dao.DatabaseDAO;
import com.example.vanahel.tasksmanagerapplication.dao.ExternalStorageDAO;
import com.example.vanahel.tasksmanagerapplication.dao.InternalStorageDAO;
import com.example.vanahel.tasksmanagerapplication.dao.MemoryDAO;
import com.example.vanahel.tasksmanagerapplication.dao.SharedPreferencesDAO;

public class TasksApplication extends Application {

    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String settingsPreferences = sharedPreferences.getString("listPref", null);
        if ( settingsPreferences !=null ) {
            switch (settingsPreferences) {
                case SettingsConstants.SHARED_PREFERENCES:
                    DAOManager.getInstance().setTaskDAO( new SharedPreferencesDAO(this) );
                    break;
                case SettingsConstants.DATABASE:
                    DAOManager.getInstance().setTaskDAO( new DatabaseDAO(this) );
                    break;
                case SettingsConstants.INTERNAL_STORAGE:
                    DAOManager.getInstance().setTaskDAO( new InternalStorageDAO(this) );
                    break;
                case SettingsConstants.EXTERNAL_STORAGE:
                    DAOManager.getInstance().setTaskDAO( new ExternalStorageDAO() );
                    break;
                case SettingsConstants.MEMORY:
                    DAOManager.getInstance().setTaskDAO( new MemoryDAO() );
                    break;
            }
        } else {
            DAOManager.getInstance().setTaskDAO( new DatabaseDAO(this)) ;
        }
    }
}
