package com.example.vanahel.tasksmanagerapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vanahel.tasksmanagerapplication.contracts.NewTaskActivityContract;
import com.example.vanahel.tasksmanagerapplication.presenter.NewTaskActivityPresenter;
import com.example.vanahel.tasksmanagerapplication.services.firebase.TasksFireBaseMessagingService;
import com.example.vanahel.tasksmanagerapplication.task.Task;
import com.example.vanahel.tasksmanagerapplication.util.screenshot.ScreenshotProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewTaskActivity extends AppCompatActivity implements NewTaskActivityContract.View {

    @BindView(R.id.save_button)
    Button saveButton;
    @BindView(R.id.title_et)
    EditText title;
    @BindView(R.id.description_et)
    EditText description;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String titleContent = intent.getStringExtra("title");
            String descriptionContent = intent.getStringExtra("description");
            title.setText(titleContent);
            title.setSelection(title.getText().length());
            description.setText(descriptionContent);
            description.setSelection(description.getText().length());
        }
    };
    private Task task;
    private NewTaskActivityPresenter presenter;
    private ScreenshotProvider screenshotProvider;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        ButterKnife.bind(this);

//        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        presenter = new NewTaskActivityPresenter(this);
        screenshotProvider = new ScreenshotProvider(this);

        try {
            task = getTask();
            setTask(task);
        } catch (Exception e) {
            Log.d("Empty field", "The fields are empty");

        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSaveButtonCLicked(task);
                String message = presenter.sendExceptionMessage();
                showExceptionMessage(message);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().hasExtra("brodcastreceiver")) {
            LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                    new IntentFilter(TasksFireBaseMessagingService.INTENT_FILTER));
            broadcastReceiver.onReceive(this, getIntent());
        } else if (getIntent().hasExtra("screenshot")) {
            screenshotProvider.takeScreenshotShare();
        }
    }

    @Override
    public Task getTask() {
        return presenter.getTask();
    }

    @Override
    public void setTask(Task task) {
        presenter.setTask(task);
    }

    @Override
    public void showExceptionMessage(String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}


