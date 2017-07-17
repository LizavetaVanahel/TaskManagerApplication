package com.example.vanahel.tasksmanagerapplication.contracts;

import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;

public interface SettingsActivityContract {

    interface View {

        void onTaskSettingsChange(Object object, TaskDAO taskDAO);
    }

    interface Presenter {

        void onTaskSettingsChanged(Object object, TaskDAO taskDAO);
    }
}
