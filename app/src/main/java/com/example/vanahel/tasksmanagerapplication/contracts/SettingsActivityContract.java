package com.example.vanahel.tasksmanagerapplication.contracts;

import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;

public interface SettingsActivityContract {

    interface View {

    }

    interface Presenter {

        void onTaskSettingsChanged(Object object, TaskDAO taskDAO);
    }
}
