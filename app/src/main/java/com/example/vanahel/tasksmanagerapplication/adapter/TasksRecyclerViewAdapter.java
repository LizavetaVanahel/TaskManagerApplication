package com.example.vanahel.tasksmanagerapplication.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vanahel.tasksmanagerapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dvkoleda on 29.06.17.
 */

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksRecyclerViewAdapter.TasksViewHolder> {

    private String[] dataset;

    public TasksRecyclerViewAdapter(String[] dataset) {
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
        holder.title.setText(dataset[position]);
        holder.description.setText(dataset[position]);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentValue = dataset[position];
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public static class TasksViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.title_item)
        TextView title;
        @BindView(R.id.description_item)
        TextView description;

        public TasksViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
