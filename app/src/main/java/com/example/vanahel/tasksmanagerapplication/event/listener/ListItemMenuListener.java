package com.example.vanahel.tasksmanagerapplication.event.listener;

import android.content.Context;
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

    private Context context;
    private Task task;

    public ListItemMenuListener (Context context, Task task){
        this.context = context;
        this.task = task;
    }

    public void onClick (View v){
        Button menuButton = (Button) v.findViewById(R.id.list_item_menu_button);
        PopupMenu popup = new PopupMenu(context, menuButton);
        if (!task.getFavorite()) {
            popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());
        } else {
            popup.getMenuInflater().inflate(R.menu.favorite_popupmenu, popup.getMenu());
        }
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.edit:
                        Intent intent = new Intent(context, NewTaskActivity.class);
                        intent.putExtra(ExtrasConstants.TASK_EXTRAS, task);
                        context.startActivity(intent);
                        break;
                    case R.id.delete:
                        try {
                            DAOManager.getInstance().getTaskDAO().delete(task);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Intent deleteIntent = new Intent(context, MainActivity.class);
                        context.startActivity(deleteIntent);
                        break;
                    case R.id.addToFavorite:
                        Intent updateIntent = new Intent(context, MainActivity.class);
                        Task favoriteTask = new Task (task.getTitle(), task.getDescription(), true);
                        try {
                            DAOManager.getInstance().getTaskDAO().updateTask(favoriteTask, task);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        context.startActivity(updateIntent);
                        break;
                    case R.id.delete_from_favorite:
                        Intent deleteFromFavoriteIntent = new Intent(context, MainActivity.class);
                        Task simpleTask = new Task (task.getTitle(), task.getDescription(), false);
                        try {
                            DAOManager.getInstance().getTaskDAO().updateTask(simpleTask, task);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        context.startActivity(deleteFromFavoriteIntent);
                    default:
                        break;
                }
                return true;
            }
        });
        popup.show();
    }
}
