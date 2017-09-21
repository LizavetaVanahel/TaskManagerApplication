package com.example.vanahel.tasksmanagerapplication.dao;

import android.content.Context;
import android.util.Log;

import com.example.vanahel.tasksmanagerapplication.exception.FileLoadException;
import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class InternalStorageDAO implements TaskDAO {

    private final static String FILE_DOWNLOAD_EXCEPTION =
            "Failed to download data from Internal Storage";
    private final static String FILE_SAVE_EXCEPTION =
            "Failed to save data to Internal Storage";
    private final static String INTERNAL_TASK_FILE_NAME = "Task";
    private final static String COMMA = ",";
    private static final String TAG = "InternalStorageDAO";
    private Context context;

    public InternalStorageDAO(Context context) {
        this.context = context;
    }

    @Override
    public void save(Task task) {

        try {
            File file = new File(context.getFilesDir(), INTERNAL_TASK_FILE_NAME);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            String taskCSVStr = task.getTitle() + COMMA + task.getDescription()
                    + COMMA + task.getFavoriteAsInt() + COMMA + task.getId();
            writer.write(taskCSVStr);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new FileLoadException(FILE_SAVE_EXCEPTION);
        }
    }

    public List<Task> getTasks() {

        BufferedReader myReader = null;
        List<Task> taskList = new LinkedList<>();
        String dataRow;

        try {
            File taskFile = new File(context.getFilesDir(), INTERNAL_TASK_FILE_NAME);
            FileInputStream fIn = new FileInputStream(taskFile);
            myReader = new BufferedReader(new InputStreamReader(fIn));
            while ((dataRow = myReader.readLine()) != null) {
                taskList.add(buildTaskFromStr(dataRow));
            }

        } catch (IOException e) {
            throw new FileLoadException(FILE_DOWNLOAD_EXCEPTION);
        } finally {
            try {
                if (myReader != null) {
                    myReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return taskList;
    }

    private Task buildTaskFromStr(String taskCSV) {
        String[] taskProps = taskCSV.split(COMMA);
        return new Task(taskProps[0], taskProps[1], taskProps[2].equals("1"), taskProps[3]);
    }

    @Override
    public List<Task> getFavoriteTasks() {

        BufferedReader myReader = null;
        List<Task> taskList = new LinkedList<>();
        String dataRow;

        try {
            File taskFile = new File(context.getFilesDir(), INTERNAL_TASK_FILE_NAME);
            FileInputStream fileInputStream = new FileInputStream(taskFile);
            myReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((dataRow = myReader.readLine()) != null) {
                String[] task = dataRow.split(COMMA);
                if (task[2].contains("1")) {
                    taskList.add(buildTaskFromStr(dataRow));
                }
            }
        } catch (IOException e) {
            throw new FileLoadException(FILE_DOWNLOAD_EXCEPTION);
        } finally {
            try {
                if (myReader != null) {
                    myReader.close();
                }
            } catch (IOException e) {
                throw new FileLoadException(FILE_DOWNLOAD_EXCEPTION);
            }
        }
        return taskList;
    }

    @Override
    public void delete(Task task) throws IOException {

        try {
            File taskFile = new File(context.getFilesDir(), INTERNAL_TASK_FILE_NAME);
            FileInputStream fIn = new FileInputStream(taskFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String line;
            String input = "";

            while ((line = myReader.readLine()) != null) {
                String[] taskString = line.split(COMMA);
                if (taskString[3].equals(task.getId())) {
                    Log.d(TAG, "Line deleted.");
                } else {
                    input += line + System.lineSeparator();
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile, false));
            writer.write(input);
            myReader.close();
            writer.close();
        } catch (RuntimeException e) {
            throw new FileLoadException(FILE_DOWNLOAD_EXCEPTION);
        }

    }

    @Override
    public void updateTask(Task newTask, Task oldTask) throws IOException {

        try {
            File taskFile = new File(context.getFilesDir(), INTERNAL_TASK_FILE_NAME);
            FileInputStream fIn = new FileInputStream(taskFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String line;
            String input = "";
            String oldTaskString = oldTask.getTitle() + COMMA + oldTask.getDescription()
                    + COMMA + oldTask.getFavoriteAsInt() + COMMA + oldTask.getId();
            String newTaskString = newTask.getTitle() + COMMA + newTask.getDescription()
                    + COMMA + newTask.getFavoriteAsInt() + COMMA + newTask.getId();

            while ((line = myReader.readLine()) != null)
                input += line + System.lineSeparator();
            input = input.replace(oldTaskString, newTaskString);

            BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile, false));
            writer.write(input);
            myReader.close();
            writer.close();
        } catch (RuntimeException e) {
            throw new FileLoadException(FILE_SAVE_EXCEPTION);
        }
    }
}
