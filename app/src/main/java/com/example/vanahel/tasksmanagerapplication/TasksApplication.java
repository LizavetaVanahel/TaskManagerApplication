package com.example.vanahel.tasksmanagerapplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.vanahel.tasksmanagerapplication.constants.SettingsConstants;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.dao.DatabaseDAO;
import com.example.vanahel.tasksmanagerapplication.dao.ExternalStorageDAO;
import com.example.vanahel.tasksmanagerapplication.dao.InternalStorageDAO;
import com.example.vanahel.tasksmanagerapplication.dao.InternetDAO;
import com.example.vanahel.tasksmanagerapplication.dao.MemoryDAO;
import com.example.vanahel.tasksmanagerapplication.dao.SharedPreferencesDAO;
import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class TasksApplication extends Application {

    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        Firebase.setAndroidContext(this);
        FirebaseMessaging.getInstance().subscribeToTopic("tasks");

        if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
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
                case SettingsConstants.INTERNET:
                    DAOManager.getInstance().setTaskDAO( new InternetDAO() );
                    break;
            }
        } else {
            DAOManager.getInstance().setTaskDAO( new DatabaseDAO(this)) ;
        }
    }
}

