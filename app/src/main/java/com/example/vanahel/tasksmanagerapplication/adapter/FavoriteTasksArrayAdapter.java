package com.example.vanahel.tasksmanagerapplication.adapter;

import android.content.Context;
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


public class FavoriteTasksArrayAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Task> tasks = new ArrayList<>();
    private Context context;

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

        Button menuButton = (Button) convertView.findViewById(R.id.list_item_menu_button);

        ListItemMenuListener listItemMenuListener =  new ListItemMenuListener(context, task);
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

    public void setFavoriteTask (List<Task> data) {
        tasks.addAll(data);
        notifyDataSetChanged();
    }

}
