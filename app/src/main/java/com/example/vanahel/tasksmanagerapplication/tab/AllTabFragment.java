package com.example.vanahel.tasksmanagerapplication.tab;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import com.example.vanahel.tasksmanagerapplication.events.ExceptionMessageEvent;
import com.example.vanahel.tasksmanagerapplication.loader.callback.AsyncTaskLoaderCallbacks;
import com.example.vanahel.tasksmanagerapplication.presenter.TabFragmentPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllTabFragment extends Fragment implements TabFragmentContract.View {

    @BindView(R.id.simple_tab_list_view)
    protected ListView tasksList;
    private AsyncTaskLoaderCallbacks asyncTaskLoaderCallbacks;
    private TasksArrayAdapter allTasksArrayAdapter;
    private TabFragmentPresenter presenter;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.all_tasks_tab, container, false);
        ButterKnife.bind(this, view);
        allTasksArrayAdapter = new TasksArrayAdapter(getActivity(), AllTabFragment.this);
        TaskDAO taskDAO = DAOManager.getInstance().getTaskDAO();
        presenter = new TabFragmentPresenter(getActivity(), this);
        asyncTaskLoaderCallbacks = new AsyncTaskLoaderCallbacks(getActivity(),
                taskDAO, allTasksArrayAdapter, presenter);
        tasksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                presenter.onTaskListItemClicked(adapterView, position);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        presenter.loadTask(asyncTaskLoaderCallbacks);
        tasksList.setAdapter(allTasksArrayAdapter);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void showExceptionMessage(String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Subscribe
    public void onMessageEvent(ExceptionMessageEvent event) {
        String message = event.getExceptionMessage();
        showExceptionMessage(message);
    }


}
