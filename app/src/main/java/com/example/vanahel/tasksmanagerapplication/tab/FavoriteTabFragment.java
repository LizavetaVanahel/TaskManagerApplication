package com.example.vanahel.tasksmanagerapplication.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vanahel.tasksmanagerapplication.R;
import com.example.vanahel.tasksmanagerapplication.adapter.TasksArrayAdapter;
import com.example.vanahel.tasksmanagerapplication.contracts.TabFragmentContract;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.loader.callback.FavoriteAsyncTasksLoaderCallbacks;
import com.example.vanahel.tasksmanagerapplication.presenter.TabFragmentPresenter;

public class FavoriteTabFragment extends Fragment implements TabFragmentContract.View{

    private FavoriteAsyncTasksLoaderCallbacks asyncTaskLoaderCallbacks;
    private TasksArrayAdapter favoriteTaskArrayAdapter;
    private static FavoriteTabFragment favoriteTabFragment;
    private TabFragmentPresenter presenter;
    private ListView favoriteTaskList;

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
        favoriteTaskArrayAdapter = new TasksArrayAdapter(getActivity(), FavoriteTabFragment.this);
        TaskDAO taskDAO = DAOManager.getInstance().getTaskDAO();
        asyncTaskLoaderCallbacks = new FavoriteAsyncTasksLoaderCallbacks(getActivity(),
                taskDAO, favoriteTaskArrayAdapter);
        presenter = new TabFragmentPresenter(getActivity(), this);
        favoriteTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                onTaskItemClick(adapterView, position);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.loadTask(asyncTaskLoaderCallbacks);
        favoriteTaskList.setAdapter(favoriteTaskArrayAdapter);
    }

    @Override
    public void onTaskItemClick(AdapterView adapterView, int position) {
        presenter.onTaskListItemClicked(adapterView, position);
    }
}
