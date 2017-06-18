package com.example.vanahel.tasksmanagerapplication.event.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TabHost;

import com.example.vanahel.tasksmanagerapplication.NewTaskActivity;
import com.example.vanahel.tasksmanagerapplication.constants.ExtrasConstants;
import com.example.vanahel.tasksmanagerapplication.constants.TabConstants;

public class NewTaskButtonListener implements View.OnClickListener {

    private Context context;
    private TabHost tabHost;

    public NewTaskButtonListener (Context context, TabHost tabHost) {
        this.context = context;
        this.tabHost = tabHost;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, NewTaskActivity.class);
        if (tabHost.getCurrentTabTag().equals(TabConstants.ALL_TAB)){
            intent.putExtra(ExtrasConstants.TAB_EXTRAS, TabConstants.ALL_TAB);
        } else if (tabHost.getCurrentTabTag().equals(TabConstants.FAVORITE_TAB)){
            intent.putExtra(ExtrasConstants.TAB_EXTRAS, TabConstants.FAVORITE_TAB);
        }
        context.startActivity(intent);
    }
}
