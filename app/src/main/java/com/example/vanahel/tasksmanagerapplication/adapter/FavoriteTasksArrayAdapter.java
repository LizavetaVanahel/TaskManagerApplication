package com.example.vanahel.tasksmanagerapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.vanahel.tasksmanagerapplication.NewTaskActivity;
import com.example.vanahel.tasksmanagerapplication.R;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvkoleda on 04.06.17.
 */

public class FavoriteTasksArrayAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Task> tasks = new ArrayList<Task>();
    private Context context;
    private Button menuButton;


    public FavoriteTasksArrayAdapter (Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_item, parent, false);
        }
        final Task task = (Task)getItem(position);
        TextView title = (TextView) convertView.findViewById(R.id.title_item);
        TextView description = (TextView) convertView.findViewById(R.id.description_item);
        title.setText(task.getTitle());
        description.setText(task.getDescription());

        menuButton = (Button) convertView.findViewById(R.id.list_item_menu_button);

        Button.OnClickListener btnOnclickListener = new Button.OnClickListener(){
            public void onClick (View v){
                final Task task = (Task)getItem(position);
                PopupMenu popup = new PopupMenu(context, menuButton);
                popup.getMenuInflater().inflate(R.menu.favorite_popupmenu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit:
                                Intent intent = new Intent(context, NewTaskActivity.class);
                                intent.putExtra("task", task);
                                context.startActivity(intent);
                                break;
                            case R.id.delete:
                                try {
                                    DAOManager.getInstance().getTaskDAO().delete(task);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        };
        menuButton.setOnClickListener(btnOnclickListener);
        return convertView;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        Task task = (Task)getItem(position);
        return position;
    }

    public void setTask (List<Task> data){
        tasks.addAll(data);
        notifyDataSetChanged();
    }
}
