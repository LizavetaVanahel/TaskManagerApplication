package com.example.vanahel.tasksmanagerapplication.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.vanahel.tasksmanagerapplication.events.ExceptionMessageEvent;
import com.example.vanahel.tasksmanagerapplication.NewTaskActivity;
import com.example.vanahel.tasksmanagerapplication.R;
import com.example.vanahel.tasksmanagerapplication.constants.ExtrasConstants;
import com.example.vanahel.tasksmanagerapplication.contracts.TabFragmentContract;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import org.greenrobot.eventbus.EventBus;

import static com.example.vanahel.tasksmanagerapplication.constants.LoaderConstants.ALL_TASKS_LOADER_ID;

public class TabFragmentPresenter implements TabFragmentContract.Presenter {

    private Activity activity;
    private Fragment fragment;

    public TabFragmentPresenter ( Activity activity, Fragment fragment ){
        this.activity = activity;
        this.fragment = fragment;
    }

    @Override
    public void onTaskListItemClicked( AdapterView adapterView, int position ) {
        Task task = (Task) adapterView.getItemAtPosition(position);
        TextView title = (TextView) adapterView.findViewById(R.id.title_item);
        TextView description = (TextView) adapterView.findViewById(R.id.description_item);
        title.setText( task.getTitle() );
        description.setText( task.getDescription() );
        Intent intent = new Intent( activity, NewTaskActivity.class );
        intent.putExtra( ExtrasConstants.TASK_EXTRAS, task );
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivityForResult(intent, 0);
    }

    @Override
    public void loadTask( LoaderManager.LoaderCallbacks asyncTaskLoaderCallbacks ) {

        fragment.getLoaderManager().
                restartLoader( ALL_TASKS_LOADER_ID, null, asyncTaskLoaderCallbacks ).forceLoad();
    }

    public void getMessage (String message){
        EventBus.getDefault().post( new ExceptionMessageEvent(message) );
    }
}
