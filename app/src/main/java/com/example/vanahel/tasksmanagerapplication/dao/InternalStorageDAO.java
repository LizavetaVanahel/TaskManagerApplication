package com.example.vanahel.tasksmanagerapplication.dao;

import android.content.Context;

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

    private Context context;
    private final static String INTERNAL_TASK_FILE_NAME = "Task";

    public InternalStorageDAO(Context context){
        this.context = context;
    }

    @Override
    public void save(String title, String description, Boolean isFavorite) {
        try {
            File file = new File(context.getFilesDir(), INTERNAL_TASK_FILE_NAME);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            int favorite = isFavorite ? 1 : 0;
            String taskCSVStr = title + "," + description + "," + favorite;
            writer.write(taskCSVStr);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("File is not created");
        }
    }

    public List<Task> getTasks (){
        BufferedReader myReader = null;
        List<Task> taskList = new LinkedList<>();
        String dataRow;

        try {
            File taskFile = new File(context.getFilesDir(), INTERNAL_TASK_FILE_NAME);
            FileInputStream fIn = new FileInputStream(taskFile);
            myReader = new BufferedReader(new InputStreamReader(fIn));
            while ((dataRow = myReader.readLine()) != null) {
                taskList.add( buildTaskFromStr(dataRow) );
            }

        } catch (IOException e) {
            System.out.println("File is not created");
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
        String[] taskProps = taskCSV.split(",");
        return new Task(taskProps[0], taskProps[1], taskProps[2].equals("1"));
    }

    @Override
    public List<Task> getFavoriteTasks() {
        BufferedReader myReader = null;
        List<Task> taskList = new LinkedList<>();
        String dataRow;

        try {
            File taskFile = new File(context.getFilesDir(), INTERNAL_TASK_FILE_NAME);
            FileInputStream fIn = new FileInputStream(taskFile);
            myReader = new BufferedReader(new InputStreamReader(fIn));
            while ((dataRow = myReader.readLine()) != null) {
                if (dataRow.contains("1")) {
                    taskList.add(buildTaskFromStr(dataRow));
                }
            }
        } catch (IOException e) {
            System.out.println("File is not created");
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

    @Override
    public void delete(Task task) throws IOException {
        File taskFile = new File(context.getFilesDir(), INTERNAL_TASK_FILE_NAME);
        FileInputStream fIn = new FileInputStream(taskFile);
        BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));

        String line;
        String input = "";
        String taskCSVStr = task.getTitle() + "," + task.getDescription() + "," + (task.getFavoriteAsInt());

        while ((line = myReader.readLine()) != null) {
            if (line.trim().equals(taskCSVStr)) {
                System.out.println("Line deleted.");
            } else {
                input += line + System.lineSeparator();
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile, false));
        writer.write(input);
        myReader.close();
        writer.close();
    }

    @Override
    public void updateTask(Task newTask, Task oldTask) throws IOException {
        File taskFile = new File(context.getFilesDir(), INTERNAL_TASK_FILE_NAME);
        FileInputStream fIn = new FileInputStream(taskFile);
        BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
        String line;
        String input = "";
        String oldTaskString = oldTask.getTitle() + "," + oldTask.getDescription() + "," + (oldTask.getFavoriteAsInt());
        String newTaskString = newTask.getTitle() + "," + newTask.getDescription() + "," + (newTask.getFavoriteAsInt());

        while ((line = myReader.readLine()) != null)
            input += line + System.lineSeparator();
            input = input.replace(oldTaskString, newTaskString);

        BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile, false));
        writer.write(input);
        myReader.close();
        writer.close();
    }
}
