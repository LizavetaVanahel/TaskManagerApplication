package com.example.vanahel.tasksmanagerapplication.contracts;

import android.support.v4.app.LoaderManager;
import android.widget.AdapterView;

 public interface TabFragmentContract {

        interface View {

            void onTaskItemClick(AdapterView adapterView, int position);

            void onResume();

        }

        interface Presenter {

            void onTaskListItemClicked(AdapterView adapterView, int position);

            void loadTask(LoaderManager.LoaderCallbacks asyncTaskLoaderCallbacks);
        }

    }