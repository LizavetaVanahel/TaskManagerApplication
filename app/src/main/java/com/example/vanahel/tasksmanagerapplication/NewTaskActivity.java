package com.example.vanahel.tasksmanagerapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vanahel.tasksmanagerapplication.contracts.NewTaskActivityContract;
import com.example.vanahel.tasksmanagerapplication.presenter.NewTaskActivityPresenter;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewTaskActivity extends ActionBarActivity implements NewTaskActivityContract.View {

    private Task task;
    private Boolean savedIsFavorite;
    private String savedId;
    private NewTaskActivityPresenter presenter;
    @BindView(R.id.save_button) Button saveButton;
    @BindView(R.id.title_et) EditText title;
    @BindView(R.id.description_et) EditText description;

    @Override
    public void onCreate( final Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        ButterKnife.bind(this);

        presenter = new NewTaskActivityPresenter(this);

        try{
            task = getTask();
            setTask(task);
        }catch (Exception e){
            Log.d("Empty field","The fields are empty");

        }

        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSaveButtonCLicked( task, savedIsFavorite, savedId );
            }
        });
    }

    @Override
    public Task getTask() {
        return presenter.getTask();
    }

    @Override
    public void setTask(Task task) {
        presenter.setTask(task);
    }
}


