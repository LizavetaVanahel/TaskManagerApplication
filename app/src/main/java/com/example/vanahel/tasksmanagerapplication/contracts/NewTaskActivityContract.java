package com.example.vanahel.tasksmanagerapplication.contracts;

import com.example.vanahel.tasksmanagerapplication.task.Task;


public interface NewTaskActivityContract {

    interface View {

        Task getTask ();

        void setTask ( Task task );

        void showExceptionMessage( String message );


    }

    interface Presenter {

        void onSaveButtonCLicked( Task task );

        Task getTask ();

        void setTask ( Task task );

    }
}