package com.example.vanahel.tasksmanagerapplication.presenter;

import android.content.Context;

import com.example.vanahel.tasksmanagerapplication.constants.SettingsConstants;
import com.example.vanahel.tasksmanagerapplication.contracts.SettingsActivityContract;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.dao.DatabaseDAO;
import com.example.vanahel.tasksmanagerapplication.dao.ExternalStorageDAO;
import com.example.vanahel.tasksmanagerapplication.dao.InternalStorageDAO;
import com.example.vanahel.tasksmanagerapplication.dao.InternetDAO;
import com.example.vanahel.tasksmanagerapplication.dao.MemoryDAO;
import com.example.vanahel.tasksmanagerapplication.dao.SharedPreferencesDAO;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;

public class SettingsActivityPresenter implements SettingsActivityContract.Presenter {

    private Context context;

    public SettingsActivityPresenter ( Context context){
        this.context = context;
    }

    @Override
    public void onTaskSettingsChanged( Object object, TaskDAO taskDAO ) {
        switch ( object.toString() ) {
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
            case  SettingsConstants.INTERNET:
                taskDAO = new InternetDAO();
                DAOManager.getInstance().setTaskDAO(taskDAO);
        }
    }
}
