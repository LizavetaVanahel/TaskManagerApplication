package com.example.vanahel.tasksmanagerapplication.contracts;

public interface MainActivityContract {

    interface View {

        void onNewTaskButtonClick();

    }

    interface Presenter {

        void onNewTaskButtonClicked();
    }
}
