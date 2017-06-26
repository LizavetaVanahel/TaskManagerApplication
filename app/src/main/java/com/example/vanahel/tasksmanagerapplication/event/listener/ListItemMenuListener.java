package com.example.vanahel.tasksmanagerapplication.event.listener;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.vanahel.tasksmanagerapplication.MainActivity;
import com.example.vanahel.tasksmanagerapplication.NewTaskActivity;
import com.example.vanahel.tasksmanagerapplication.R;
import com.example.vanahel.tasksmanagerapplication.constants.ExtrasConstants;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.io.IOException;

public class ListItemMenuListener implements View.OnClickListener  {

    private Activity activity;
    private Task task;

    public ListItemMenuListener (Activity activity, Task task){
        this.activity = activity;
        this.task = task;
    }

    public void onClick (View v){
        Button menuButton = (Button) v.findViewById(R.id.list_item_menu_button);
        PopupMenu popup = new PopupMenu(activity, menuButton);
        if (!task.getFavorite()) {
            popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());
        } else {
            popup.getMenuInflater().inflate(R.menu.favorite_popupmenu, popup.getMenu());
        }
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.edit:
                        Intent intent = new Intent(activity, NewTaskActivity.class);
                        intent.putExtra(ExtrasConstants.TASK_EXTRAS, task);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivityForResult(intent, 0);
                        break;
                    case R.id.delete:
                        try {
                            DAOManager.getInstance().getTaskDAO().delete(task);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Intent deleteIntent = new Intent(activity, MainActivity.class);
                        deleteIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivityForResult(deleteIntent, 0);
                        break;
                    case R.id.add_to_favorite:
                        Intent updateIntent = new Intent(activity, MainActivity.class);
                        Task favoriteTask = new Task (task.getTitle(), task.getDescription(), true, task.getId());
                        try {
                            DAOManager.getInstance().getTaskDAO().updateTask(favoriteTask, task);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        updateIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivityForResult(updateIntent, 0);
                        break;
                    case R.id.delete_from_favorite:
                        Intent deleteFromFavoriteIntent = new Intent(activity, MainActivity.class);
                        Task simpleTask = new Task (task.getTitle(), task.getDescription(), false, task.getId());
                        try {
                            DAOManager.getInstance().getTaskDAO().updateTask(simpleTask, task);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        deleteFromFavoriteIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivityForResult(deleteFromFavoriteIntent, 0);
                    default:
                        break;
                }
                return true;
            }
        });
        popup.show();
    }
}
