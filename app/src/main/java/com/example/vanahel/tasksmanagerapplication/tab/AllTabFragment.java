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
import com.example.vanahel.tasksmanagerapplication.loader.callback.AsyncTaskLoaderCallbacks;
import com.example.vanahel.tasksmanagerapplication.presenter.TabFragmentPresenter;

public class AllTabFragment extends Fragment implements TabFragmentContract.View{

    private AsyncTaskLoaderCallbacks asyncTaskLoaderCallbacks;
    private TasksArrayAdapter allTasksArrayAdapter;
    private static AllTabFragment allTabFragment;
    private TabFragmentPresenter presenter;
    private ListView tasksList;
    private TaskDAO taskDAO;

    public AllTabFragment(){

    }

    public static AllTabFragment getAllTabFragment(){
        if (allTabFragment == null){
            allTabFragment = new AllTabFragment();
        }

        return allTabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_tasks_tab, container, false);
        tasksList = (ListView) view.findViewById(R.id.simple_tab_list_view);
        allTasksArrayAdapter = new TasksArrayAdapter(getActivity(), AllTabFragment.this);
        taskDAO = DAOManager.getInstance().getTaskDAO();
        asyncTaskLoaderCallbacks = new AsyncTaskLoaderCallbacks(getActivity(), taskDAO, allTasksArrayAdapter);
        presenter = new TabFragmentPresenter(getActivity(), this);
        tasksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        tasksList.setAdapter(allTasksArrayAdapter);

    }

    @Override
    public void onTaskItemClick(AdapterView adapterView, int position) {
        presenter.onTaskListItemClicked(adapterView, position);
    }

}