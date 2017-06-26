package com.example.vanahel.tasksmanagerapplication.event.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TabHost;

import com.example.vanahel.tasksmanagerapplication.NewTaskActivity;
import com.example.vanahel.tasksmanagerapplication.constants.ExtrasConstants;
import com.example.vanahel.tasksmanagerapplication.constants.TabConstants;

public class NewTaskButtonListener implements View.OnClickListener {

    private Activity activity;
    private TabHost tabHost;

    public NewTaskButtonListener (Activity activity, TabHost tabHost) {
        this.activity = activity;
        this.tabHost = tabHost;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity, NewTaskActivity.class);

        if(tabHost.getCurrentTabTag().equals(TabConstants.ALL_TAB)){
            intent.putExtra(ExtrasConstants.TAB_EXTRAS, false);
        } else if (tabHost.getCurrentTabTag().equals(TabConstants.FAVORITE_TAB)){
            intent.putExtra(ExtrasConstants.TAB_EXTRAS,true);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivityForResult(intent, 0);
    }
}
