package com.example.vanahel.tasksmanagerapplication.presenter;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;

import com.example.vanahel.tasksmanagerapplication.MainActivity;
import com.example.vanahel.tasksmanagerapplication.R;
import com.example.vanahel.tasksmanagerapplication.constants.ExtrasConstants;
import com.example.vanahel.tasksmanagerapplication.contracts.NewTaskActivityContract;
import com.example.vanahel.tasksmanagerapplication.dao.DAOManager;
import com.example.vanahel.tasksmanagerapplication.exception.DatabaseLoadException;
import com.example.vanahel.tasksmanagerapplication.exception.FileLoadException;
import com.example.vanahel.tasksmanagerapplication.exception.InternetDataLoadException;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.io.IOException;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class NewTaskActivityPresenter implements NewTaskActivityContract.Presenter {

    private Activity activity;
    private EditText title;
    private EditText description;
    private String message;

    public NewTaskActivityPresenter ( Activity activity ){
        this.activity = activity;
        title = (EditText) activity.findViewById(R.id.title_et);
        description = (EditText) activity.findViewById(R.id.description_et);
    }

    @Override
    public void onSaveButtonCLicked(Task task ) {
        String enteredTitle = title.getText().toString();
        String enteredDescription = description.getText().toString();
        Intent intent = new Intent(activity, MainActivity.class);
        if ( activity.getIntent().hasExtra( ExtrasConstants.TASK_EXTRAS ) ) {
            try {
                DAOManager.getInstance().getTaskDAO().updateTask
                        ( new Task( enteredTitle, enteredDescription, task.getFavorite(), task.getId() ), task );
                activity.setResult( RESULT_OK, intent );
                activity.finish();
            } catch ( IOException e ) {
                message = e.getMessage();
            }  catch ( DatabaseLoadException | FileLoadException | InternetDataLoadException e){
                message = e.getMessage();
            } catch ( RuntimeException e ){
                message = e.getMessage();
            }

        } else {
            boolean isTabFavorite = activity.getIntent().
                    getBooleanExtra( ExtrasConstants.TAB_EXTRAS, false );
            try {
                DAOManager.getInstance().getTaskDAO().save(new Task(enteredTitle,
                        enteredDescription, isTabFavorite, UUID.randomUUID().toString()));
                activity.setResult(RESULT_OK, intent);
                activity.finish();
            } catch ( DatabaseLoadException | FileLoadException | InternetDataLoadException e ){
                message = e.getMessage();
            } catch ( RuntimeException e ){
                message = e.getMessage();
            }
        }
    }

    @Override
    public Task getTask() {
        Task task = activity.getIntent().getParcelableExtra(ExtrasConstants.TASK_EXTRAS);
        String savedTitle = task.getTitle();
        String savedDescription = task.getDescription();
        Boolean savedIsFavorite = task.getFavorite();
        String savedId = task.getId();
        return new Task ( savedTitle, savedDescription, savedIsFavorite, savedId );
    }

    @Override
    public void setTask(Task task) {
        title.setText( task.getTitle() );
        title.setSelection(title.getText().length());
        description.setText( task.getDescription() );
        description.setSelection( description.getText().length() );
    }

    public String sendExceptionMessage (){
        return message;
    }

}
