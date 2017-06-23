package com.example.vanahel.tasksmanagerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vanahel.tasksmanagerapplication.constants.ExtrasConstants;
import com.example.vanahel.tasksmanagerapplication.constants.TabConstants;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.io.IOException;
import java.util.UUID;

public class NewTaskActivity extends ActionBarActivity {

    private Task task;
    private Boolean savedIsFavorite;
    private String savedId;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        final Button saveButton = (Button) findViewById(R.id.save_button);
        final EditText title = (EditText) findViewById(R.id.title_et);
        final EditText description = (EditText) findViewById(R.id.description_et);

        try {
            task = getIntent().getParcelableExtra(ExtrasConstants.TASK_EXTRAS);
            String savedTitle = task.getTitle();
            String savedDescription = task.getDescription();
            savedIsFavorite = task.getFavorite();
            savedId = task.getId();
            title.setText(savedTitle);
            title.setSelection(title.getText().length());
            description.setText(savedDescription);
            description.setSelection(description.getText().length());
        } catch (Exception e) {
            System.out.println("The fields are empty");
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredTitle = title.getText().toString();
                String enteredDescription = description.getText().toString();
                Intent intent = new Intent(NewTaskActivity.this, MainActivity.class);
                if (getIntent().hasExtra(ExtrasConstants.TASK_EXTRAS)) {
                    try {
                        DAOManager.getInstance().getTaskDAO().updateTask
                                (new Task(enteredTitle, enteredDescription, savedIsFavorite, savedId), task);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                } else {
                    if (getIntent().getStringExtra(ExtrasConstants.TAB_EXTRAS).equals(TabConstants.ALL_TAB)) {
                        DAOManager.getInstance().getTaskDAO().save(new Task(enteredTitle,
                                enteredDescription, false, UUID.randomUUID().toString()));
                    } else if (getIntent().getStringExtra(ExtrasConstants.TAB_EXTRAS).equals
                            (TabConstants.FAVORITE_TAB)) {
                        DAOManager.getInstance().getTaskDAO().save
                                (new Task(enteredTitle, enteredDescription, true, UUID.randomUUID().toString()));
                    }
                    startActivity(intent);
                }
            }
        });
    }
}


