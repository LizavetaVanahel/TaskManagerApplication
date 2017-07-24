package com.example.vanahel.tasksmanagerapplication.context.provider;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.vanahel.tasksmanagerapplication.NewTaskActivity;
import com.example.vanahel.tasksmanagerapplication.constants.ExtrasConstants;
import com.example.vanahel.tasksmanagerapplication.task.Task;

public class ListItemMenuContextProvider implements ListItemMenuHandler {

    @Override
    public void callFragmentOnResume( Fragment fragment ) {
        fragment.onResume();
    }

    @Override
    public void callStartActivityForResult( Activity activity, Task task)  {
        Intent intent = new Intent( activity, NewTaskActivity.class );
        intent.putExtra( ExtrasConstants.TASK_EXTRAS, task );
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        activity.startActivityForResult( intent, 0 );
    }
}
