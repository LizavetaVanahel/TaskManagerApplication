package com.example.vanahel.tasksmanagerapplication.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.vanahel.tasksmanagerapplication.R;
import com.example.vanahel.tasksmanagerapplication.adapter.TasksArrayAdapter;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.event.listener.TaskListItemClickListener;
import com.example.vanahel.tasksmanagerapplication.loader.callback.AsyncTaskLoaderCallbacks;

import static com.example.vanahel.tasksmanagerapplication.constants.LoaderConstants.ALL_TASKS_LOADER_ID;

public class AllTabFragment extends Fragment {

    private AsyncTaskLoaderCallbacks asyncTaskLoaderCallbacks;
    private ListView tasksList;
    private TasksArrayAdapter allTasksArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_tasks_tab, container, false);
        tasksList = (ListView) view.findViewById(R.id.simple_tab_list_view);
        allTasksArrayAdapter = new TasksArrayAdapter(getActivity());
        TaskDAO taskDAO = DAOManager.getInstance().getTaskDAO();
        asyncTaskLoaderCallbacks = new AsyncTaskLoaderCallbacks(getActivity(), taskDAO, allTasksArrayAdapter);
        TaskListItemClickListener taskListItemClickListener = new TaskListItemClickListener(getActivity());
        tasksList.setOnItemClickListener(taskListItemClickListener);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getLoaderManager().restartLoader(ALL_TASKS_LOADER_ID, null,
                asyncTaskLoaderCallbacks).forceLoad();

        tasksList.setAdapter(allTasksArrayAdapter);

    }
}