package com.example.vanahel.tasksmanagerapplication.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.vanahel.tasksmanagerapplication.R;
import com.example.vanahel.tasksmanagerapplication.context.provider.ListItemMenuContextProvider;
import com.example.vanahel.tasksmanagerapplication.contracts.TaskArrayAdapterContract;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.io.IOException;


public class TaskArrayAdapterPresenter implements TaskArrayAdapterContract.Presenter,
        TaskArrayAdapterContract.Menu {

    private Activity activity;
    private Task task;
    private Fragment fragment;
    private ListItemMenuContextProvider listItemMenuContextProvider = new ListItemMenuContextProvider();

    public TaskArrayAdapterPresenter(Activity activity, Task task, Fragment fragment) {
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
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        edit();
                        break;
                    case R.id.delete:
                        delete();
                        break;
                    case R.id.add_to_favorite:
                        addToFavorite();
                        break;
                    case R.id.delete_from_favorite:
                        removeFromFavorite();
                    default:
                        break;
                    case R.id.share:
                        share();
                }
                return true;
            }
        });
        popup.show();
    }


    @Override
    public void edit() {
        listItemMenuContextProvider.callStartActivityForResult(activity, task);
    }

    @Override
    public void delete() {
        try {
            DAOManager.getInstance().getTaskDAO().delete(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listItemMenuContextProvider.callFragmentOnResume(fragment);
    }

    @Override
    public void addToFavorite() {
        Task favoriteTask = new Task(task.getTitle(), task.getDescription(), true, task.getId());
        try {
            DAOManager.getInstance().getTaskDAO().updateTask(favoriteTask, task);
        } catch (IOException e) {
        }
        listItemMenuContextProvider.callFragmentOnResume(fragment);
    }

    @Override
    public void removeFromFavorite() {
        Task simpleTask = new Task(task.getTitle(), task.getDescription(), false, task.getId());
        try {
            DAOManager.getInstance().getTaskDAO().updateTask(simpleTask, task);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listItemMenuContextProvider.callFragmentOnResume(fragment);
    }

    @Override
    public void share() {
        listItemMenuContextProvider.callStartActivityForResultFragment(activity, task);
    }

}

