package com.example.vanahel.tasksmanagerapplication.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vanahel.tasksmanagerapplication.R;

/**
 * Created by dvkoleda on 29.06.17.
 */

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksRecyclerViewAdapter.TasksViewHolder> {

    private String[] dataset;

    public static class TasksViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView textView;
        public TasksViewHolder (View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.card_view);
            textView = (TextView) view.findViewById(R.id.title_item);
            textView = (TextView) view.findViewById(R.id.description_item);

        }
    }

    public TasksRecyclerViewAdapter (String[]dataset) {
        dataset = dataset;
    }

    @Override
    public TasksRecyclerViewAdapter.TasksViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_card, parent, false);
        TasksViewHolder tasksViewHolder = new TasksViewHolder(v);
        return tasksViewHolder;
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, final int position) {
        holder.textView.setText(dataset [position]);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentValue = dataset [position];
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }
}
