package com.example.vanahel.tasksmanagerapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vanahel.tasksmanagerapplication.task.Task;

import java.util.LinkedList;
import java.util.List;

public class DatabaseDAO implements TaskDAO {

    private static final String DB_NAME = "Task";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "TaskTable";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IS_FAVORITE = "is_favorite";

    private static final String DB_CREATE =
            "CREATE TABLE " + DB_TABLE + "(" +
                    COLUMN_ID + " STRING PRIMARY KEY, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_IS_FAVORITE +  " INTEGER);";

    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseDAO(Context context) {
        this.context = context;
        open();
    }

    private void open() {
        this.dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        this.database = dbHelper.getWritableDatabase();
    }

    public void close() {
        if ( dbHelper !=null ) dbHelper.close();
    }

    public void updateTask ( Task newTask, Task oldTask ){
        ContentValues cv = new ContentValues();
        cv.put( COLUMN_TITLE, newTask.getTitle() );
        cv.put( COLUMN_DESCRIPTION, newTask.getDescription() );
        cv.put( COLUMN_IS_FAVORITE, newTask.getFavoriteAsInt() );
        cv.put( COLUMN_ID, newTask.getId() );

        int affectedRows = database.update( DB_TABLE, cv, COLUMN_ID + "=?", new String[] {oldTask.getId()} );
        System.out.print("SQL update method. Rows affected: " + affectedRows);
    }

    public void save(Task task) {
        ContentValues cv = new ContentValues();
        cv.put( COLUMN_TITLE, task.getTitle() );
        cv.put( COLUMN_DESCRIPTION, task.getDescription() );
        cv.put( COLUMN_IS_FAVORITE, task.getFavoriteAsInt() );
        cv.put( COLUMN_ID, task.getId() );
        database.insert(DB_TABLE, null, cv);
    }

    public List<Task> getFavoriteTasks () {
        try ( Cursor cursor = database.rawQuery("SELECT * FROM " + DB_TABLE + " WHERE "
                + COLUMN_IS_FAVORITE + "=?", new String[]{"1"}) ){
            List tasks = new LinkedList();
            while (cursor.moveToNext()) {
                int titleIndex = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_TITLE);
                int descriptionIndex = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_DESCRIPTION);
                int IsFavorite_index = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_IS_FAVORITE);
                int idIndex = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_ID);
                String title = cursor.getString(titleIndex);
                String description = cursor.getString(descriptionIndex);
                boolean isFavorite = cursor.getInt(IsFavorite_index) == 1;
                String id = cursor.getString(idIndex);
                tasks.add(new Task(title, description, isFavorite, id));
            }
            return tasks;
        }
    }

    @Override
    public List<Task> getTasks() {
        try ( Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null) ) {
            List tasks = new LinkedList();
            while (cursor.moveToNext()) {
                int titleIndex = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_TITLE);
                int descriptionIndex = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_DESCRIPTION);
                int isFavoriteIndex = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_IS_FAVORITE);
                int idIndex = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_ID);
                String title = cursor.getString(titleIndex);
                String description = cursor.getString(descriptionIndex);
                boolean isFavorite = cursor.getInt(isFavoriteIndex) == 1;
                String id = cursor.getString(idIndex);
                tasks.add(new Task(title, description, isFavorite, id));
            }
            return tasks;
        }
    }

    public void delete(Task task) {
        database.delete( DB_TABLE, COLUMN_ID + "=?", new String[] {task.getId()} );
    }

    private class DBHelper extends SQLiteOpenHelper {

        private DBHelper( Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version ) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}


