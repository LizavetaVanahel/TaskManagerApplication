package com.example.vanahel.tasksmanagerapplication.dao;

public class DAOManager {

    private TaskDAO taskDAO;

    private static final DAOManager DAOManagerInstance = new DAOManager();

    private DAOManager() {
    }

    public static DAOManager getInstance() {
        return DAOManager.DAOManagerInstance;
    }

    public void setTaskDAO(TaskDAO taskDAO){
        this.taskDAO = taskDAO;
    }

    public TaskDAO getTaskDAO() {
        if ( null == this.taskDAO ) {
            throw new NullPointerException("DAOManager: taskDAO object wasn't initialized");
        }
        return this.taskDAO;
    }
}
