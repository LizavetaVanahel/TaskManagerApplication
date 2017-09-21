package com.example.vanahel.tasksmanagerapplication.context.provider;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.vanahel.tasksmanagerapplication.task.Task;

public interface ListItemMenuHandler {

    void callFragmentOnResume(Fragment fragment);

    void callStartActivityForResult(Activity activity, Task task);

}
