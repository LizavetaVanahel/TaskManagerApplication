package com.example.vanahel.tasksmanagerapplication.dao;

import android.os.Environment;

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

public class ExternalStorageDAO implements TaskDAO {

    private final static String APP_DIR_NAME = "Tasks";
    private final static String EXTERNAL_TASK_FILE_NAME = "task.csv";
    private final static String COMMA = ",";

    @Override
    public void save(Task task) {
        try {
            File appDir = new File(Environment.getExternalStorageDirectory(), APP_DIR_NAME);
            if( !appDir.exists() ) {
                appDir.mkdirs();
            }
            File taskFile = new File(appDir, EXTERNAL_TASK_FILE_NAME);
            BufferedWriter writer = new BufferedWriter( new FileWriter(taskFile, true) );

            String taskCSVStr = task.getTitle() + COMMA + task.getDescription() + COMMA
                    + task.getFavoriteAsInt() + COMMA + task.getId();
            writer.write(taskCSVStr);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("File is not created");
        }
    }

    @Override
    public List<Task> getTasks (){
        BufferedReader myReader = null;
        List<Task> taskList = new LinkedList<>();
        String dataRow;

        try {
            File appDir = new File( Environment.getExternalStorageDirectory(), APP_DIR_NAME );
            File taskFile = new File( appDir, EXTERNAL_TASK_FILE_NAME );
            FileInputStream fIn = new FileInputStream(taskFile);
            myReader = new BufferedReader( new InputStreamReader(fIn) );
            while ( (dataRow = myReader.readLine()) != null ) {
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

    @Override
    public List<Task> getFavoriteTasks() {
        BufferedReader myReader = null;
        List<Task> taskList = new LinkedList<>();
        String dataRow;

        try {
            File appDir = new File( Environment.getExternalStorageDirectory(), APP_DIR_NAME );
            File taskFile = new File(appDir, EXTERNAL_TASK_FILE_NAME);
            FileInputStream fileInputStream = new FileInputStream(taskFile);
            myReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while ( (dataRow = myReader.readLine()) != null ) {
                if ( dataRow.contains("1") ) {
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

    private Task buildTaskFromStr(String taskCSV) {
        String[] taskProps = taskCSV.split(COMMA);
        return new Task( taskProps[0], taskProps[1], taskProps[2].equals("1"), taskProps[3] );
    }

    @Override
    public void delete(Task task) throws IOException {

        File appDir = new File( Environment.getExternalStorageDirectory(), APP_DIR_NAME );
        File taskFile = new File( appDir, EXTERNAL_TASK_FILE_NAME );
        FileInputStream fIn = new FileInputStream(taskFile);
        BufferedReader myReader = new BufferedReader( new InputStreamReader(fIn) );
        String line;
        String input = "";

        while ( (line = myReader.readLine()) != null ) {
            String[] taskString = line.split(COMMA);
                if ( taskString[3].equals(task.getId()) ) {
                    System.out.println("Line deleted.");
                } else {
                    input += line + System.lineSeparator();
                }
            }
        BufferedWriter writer = new BufferedWriter( new FileWriter(taskFile, false) );
        writer.write(input);
        myReader.close();
        writer.close();
    }

    @Override
    public void updateTask( Task newTask, Task oldTask ) throws IOException {
        File appDir = new File(Environment.getExternalStorageDirectory(), APP_DIR_NAME);
        File taskFile = new File(appDir, EXTERNAL_TASK_FILE_NAME);
        FileInputStream fIn = new FileInputStream(taskFile);
        BufferedReader myReader = new BufferedReader( new InputStreamReader(fIn) );
        String line;
        String input = "";
        String oldTaskString = oldTask.getTitle() + COMMA + oldTask.getDescription() + COMMA +
                oldTask.getFavoriteAsInt() + COMMA + oldTask.getId();
        String newTaskString = newTask.getTitle() + COMMA + newTask.getDescription() + COMMA +
                newTask.getFavoriteAsInt() + COMMA + newTask.getId();

        while( ( line = myReader.readLine() ) != null )
            input += line + System.lineSeparator();
            input = input.replace(oldTaskString, newTaskString);

        BufferedWriter writer = new BufferedWriter( new FileWriter(taskFile, false) );
        writer.write(input);
        myReader.close();
        writer.close();
    }
}



