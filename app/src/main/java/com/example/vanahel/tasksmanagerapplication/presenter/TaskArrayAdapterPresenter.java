package com.example.vanahel.tasksmanagerapplication.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.vanahel.tasksmanagerapplication.R;
import com.example.vanahel.tasksmanagerapplication.contracts.TaskArrayAdapterContract;
import com.example.vanahel.tasksmanagerapplication.task.Task;


public class TaskArrayAdapterPresenter implements TaskArrayAdapterContract.Presenter {

    private Activity activity;
    private Task task;
    private TaskArrayAdapterMenu menu;
    private Fragment fragment;

    public TaskArrayAdapterPresenter (Activity activity, Task task, Fragment fragment) {
        this.activity = activity;
        this.task = task;
        this.fragment = fragment;
    }

    @Override
    public void onMenuButtonClicked(View view) {
        Button menuButton = (Button) view.findViewById(R.id.list_item_menu_button);
        PopupMenu popup = new PopupMenu(activity, menuButton);
        if (!task.getFavorite()) {
            popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());
        } else {
            popup.getMenuInflater().inflate(R.menu.favorite_popupmenu, popup.getMenu());
        }
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

            public boolean onMenuItemClick(MenuItem item) {
                menu = new TaskArrayAdapterMenu(activity, task, fragment);
                switch (item.getItemId()){
                    case R.id.edit:
                        menu.edit();
                        break;
                    case R.id.delete:
                       menu.delete();
                        break;
                    case R.id.add_to_favorite:
                        menu.addToFavorite();
                        break;
                    case R.id.delete_from_favorite:
                        menu.removeFromFavorite();
                    default:
                        break;
                }
                return true;
            }
        });
        popup.show();
    }
}
