package com.example.vanahel.tasksmanagerapplication.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.vanahel.tasksmanagerapplication.R;
import com.example.vanahel.tasksmanagerapplication.contracts.TaskArrayAdapterContract;
import com.example.vanahel.tasksmanagerapplication.presenter.TaskArrayAdapterPresenter;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class TasksArrayAdapter extends BaseAdapter implements TaskArrayAdapterContract.View {

    private LayoutInflater layoutInflater;
    private List<Task> tasks = new ArrayList<>();
    private Activity activity;
    private TaskArrayAdapterPresenter presenter;
    private Fragment fragment;

    public TasksArrayAdapter ( Activity activity, Fragment fragment ){
        this.activity = activity;
        layoutInflater = LayoutInflater.from( activity );
        this.fragment = fragment;
    }


    @Override
    public View getView( final int position, View convertView, final ViewGroup parent ) {
        ButterKnife.bind( activity );
        if ( convertView == null ) {
            convertView = layoutInflater.inflate( R.layout.listview_item, parent, false );
        }
        final Task task = (Task)getItem( position );
        final TextView title = (TextView) convertView.findViewById(R.id.title_item);
        final TextView description = (TextView) convertView.findViewById(R.id.description_item);
        final Button menuButton = (Button) convertView.findViewById(R.id.list_item_menu_button);

        presenter = new TaskArrayAdapterPresenter( activity, task, fragment );

        title.setText( task.getTitle() );
        description.setText( task.getDescription() );


        menuButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                presenter.onMenuButtonClicked(view);
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem( int position ) {
        return tasks.get( position );
    }

    @Override
    public long getItemId( int position ) {
        Task task = ( Task )getItem( position );
       return position;
    }

    public void setTask ( List<Task> data ){
        tasks.clear();
        tasks.addAll(data);
        notifyDataSetChanged();
    }
}
