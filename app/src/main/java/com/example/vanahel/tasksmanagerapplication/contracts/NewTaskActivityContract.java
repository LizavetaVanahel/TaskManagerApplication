package com.example.vanahel.tasksmanagerapplication.contracts;

import com.example.vanahel.tasksmanagerapplication.task.Task;


public interface NewTaskActivityContract {

    interface View {

        void onSaveButtonClick();

        Task getTask ();

        void setTask (Task task);

    }

    interface Presenter {

        void onSaveButtonCLicked(Task task, Boolean savedIsFavorite, String savedId);

        Task getTask ();

        void setTask (Task task);

    }
}