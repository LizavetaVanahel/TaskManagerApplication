package com.example.vanahel.tasksmanagerapplication.event.listener;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.vanahel.tasksmanagerapplication.NewTaskActivity;
import com.example.vanahel.tasksmanagerapplication.constants.ExtrasConstants;

public class NewTaskButtonListener implements View.OnClickListener {

    private Activity activity;
    private ViewPager viewPager;

    public NewTaskButtonListener (Activity activity, ViewPager viewPager) {
        this.activity = activity;
        this.viewPager = viewPager;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity, NewTaskActivity.class);
       int fragment = viewPager.getCurrentItem();
        if(fragment == 0){
            intent.putExtra(ExtrasConstants.TAB_EXTRAS, false);
        } else if (fragment == 1){
            intent.putExtra(ExtrasConstants.TAB_EXTRAS,true);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivityForResult(intent, 0);
    }
}
