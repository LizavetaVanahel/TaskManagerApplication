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
import com.example.vanahel.tasksmanagerapplication.loader.callback.FavoriteAsyncTasksLoaderCallbacks;

import static com.example.vanahel.tasksmanagerapplication.constants.LoaderConstants.ALL_TASKS_LOADER_ID;

public class FavoriteTabFragment extends Fragment {

    private FavoriteAsyncTasksLoaderCallbacks asyncTaskLoaderCallbacks;
    private ListView favoriteTaskList;
    private TasksArrayAdapter favoriteTaskArrayAdapter;
    private static FavoriteTabFragment favoriteTabFragment;

    public FavoriteTabFragment(){

    }

    public static FavoriteTabFragment getAllTabFragment(){
        if (favoriteTabFragment == null){
            favoriteTabFragment = new FavoriteTabFragment();
        }

        return favoriteTabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_tasks_tab, container, false);
        favoriteTaskList = (ListView) view.findViewById(R.id.favorite_tab_list_view);
        favoriteTaskArrayAdapter = new TasksArrayAdapter(getActivity());
        TaskDAO taskDAO = DAOManager.getInstance().getTaskDAO();
        asyncTaskLoaderCallbacks = new FavoriteAsyncTasksLoaderCallbacks(getActivity(),
                taskDAO, favoriteTaskArrayAdapter);
        TaskListItemClickListener taskListItemClickListener = new TaskListItemClickListener(getActivity());
        favoriteTaskList.setOnItemClickListener(taskListItemClickListener);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getLoaderManager().restartLoader(ALL_TASKS_LOADER_ID, null,
                asyncTaskLoaderCallbacks).forceLoad();

        favoriteTaskList.setAdapter(favoriteTaskArrayAdapter);
    }
}
