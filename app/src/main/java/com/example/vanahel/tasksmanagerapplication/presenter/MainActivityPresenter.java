package com.example.vanahel.tasksmanagerapplication.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.example.vanahel.tasksmanagerapplication.NewTaskActivity;
import com.example.vanahel.tasksmanagerapplication.constants.ExtrasConstants;
import com.example.vanahel.tasksmanagerapplication.contracts.MainActivityContract;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private Activity activity;
    private ViewPager viewPager;

    public MainActivityPresenter (Activity activity, ViewPager viewPager){
        this.activity = activity;
        this.viewPager = viewPager;
    }

    @Override
    public void onNewTaskButtonClicked() {
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
