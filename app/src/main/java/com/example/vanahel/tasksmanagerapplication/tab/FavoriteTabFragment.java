package com.example.vanahel.tasksmanagerapplication.tab;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vanahel.tasksmanagerapplication.events.ExceptionMessageEvent;
import com.example.vanahel.tasksmanagerapplication.R;
import com.example.vanahel.tasksmanagerapplication.adapter.TasksArrayAdapter;
import com.example.vanahel.tasksmanagerapplication.contracts.TabFragmentContract;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.dao.TaskDAO;
import com.example.vanahel.tasksmanagerapplication.loader.callback.FavoriteAsyncTasksLoaderCallbacks;
import com.example.vanahel.tasksmanagerapplication.presenter.TabFragmentPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteTabFragment extends Fragment implements TabFragmentContract.View{

    private FavoriteAsyncTasksLoaderCallbacks asyncTaskLoaderCallbacks;
    private TasksArrayAdapter favoriteTaskArrayAdapter;
    private TabFragmentPresenter presenter;
    @BindView(R.id.favorite_tab_list_view)
    protected ListView favoriteTaskList;
    private View view;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.favorite_tasks_tab, container, false);
        ButterKnife.bind( this, view );
        favoriteTaskArrayAdapter = new TasksArrayAdapter(getActivity(), FavoriteTabFragment.this);
        TaskDAO taskDAO = DAOManager.getInstance().getTaskDAO();
        asyncTaskLoaderCallbacks = new FavoriteAsyncTasksLoaderCallbacks( getActivity(),
                taskDAO, favoriteTaskArrayAdapter, presenter );
        presenter = new TabFragmentPresenter( getActivity(), this );
        favoriteTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                presenter.onTaskListItemClicked( adapterView, position );
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if ( !EventBus.getDefault().isRegistered(this) ) {
            EventBus.getDefault().register(this);
        }

        presenter.loadTask( asyncTaskLoaderCallbacks );
        favoriteTaskList.setAdapter( favoriteTaskArrayAdapter );
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void showExceptionMessage( String message ) {
        Snackbar snackbar = Snackbar.make( view, message, Snackbar.LENGTH_LONG );
        snackbar.show();
    }

    @Subscribe
    public void onMessageEvent( ExceptionMessageEvent event ) {
        String message = event.getExceptionMessage();
        showExceptionMessage( message );
    }
}
