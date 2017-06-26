package com.example.vanahel.tasksmanagerapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.vanahel.tasksmanagerapplication.R;
import com.example.vanahel.tasksmanagerapplication.event.listener.ListItemMenuListener;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TasksArrayAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Task> tasks = new ArrayList<>();
    private Activity activity;

    public TasksArrayAdapter (Activity activity){
        this.activity = activity;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_item, parent, false);
        }
        final Task task = (Task)getItem(position);
        final TextView title = (TextView) convertView.findViewById(R.id.title_item);
        final TextView description = (TextView) convertView.findViewById(R.id.description_item);
        final Button menuButton = (Button) convertView.findViewById(R.id.list_item_menu_button);

        title.setText(task.getTitle());
        description.setText(task.getDescription());

        ListItemMenuListener listItemMenuListener = new ListItemMenuListener(activity, task);
        menuButton.setOnClickListener(listItemMenuListener);
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
        tasks.clear();
        tasks.addAll(data);
        notifyDataSetChanged();
    }
}
