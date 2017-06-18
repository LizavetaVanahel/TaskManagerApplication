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
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_IS_FAVORITE +  " INTEGER);";

    private Context mCtx;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DatabaseDAO(Context ctx) {
        mCtx = ctx;
        open();
    }

    private void open() {
        this.mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        this.mDB = mDBHelper.getWritableDatabase();
    }

    public void close() {
        if (mDBHelper!=null) mDBHelper.close();
    }

    public void updateTask (Task newTask, Task oldTask){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, newTask.getTitle());
        cv.put(COLUMN_DESCRIPTION, newTask.getDescription());
        cv.put(COLUMN_IS_FAVORITE, newTask.getFavoriteAsInt());

        int affectedRows = mDB.update(DB_TABLE, cv, COLUMN_TITLE + "=? AND " +
                COLUMN_DESCRIPTION + "=?",
                new String[] {oldTask.getTitle(), oldTask.getDescription()});
        System.out.print("SQL update method. Rows affected: " + affectedRows);
    }

    public void save(String title, String description, Boolean isFavorite) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_IS_FAVORITE, isFavorite? 1 : 0 );
        mDB.insert(DB_TABLE, null, cv);
    }

    public List<Task> getFavoriteTasks () {
        try (Cursor cursor = mDB.rawQuery("SELECT * FROM " + DB_TABLE + " WHERE " +
                        COLUMN_IS_FAVORITE + "=?", new String[]{"true"})){
            List tasks = new LinkedList();
            while (cursor.moveToNext()) {
                int Title_index = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_TITLE);
                int Description_index = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_DESCRIPTION);
                int IsFavorite_index = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_IS_FAVORITE);
                String title = cursor.getString(Title_index);
                String description = cursor.getString(Description_index);
                boolean isFavorite = cursor.getInt(IsFavorite_index) == 1;
                tasks.add(new Task(title, description, isFavorite));
            }
            return tasks;
        }
    }

    @Override
    public List<Task> getTasks() {
        try (Cursor cursor = mDB.query(DB_TABLE, null, null, null, null, null, null)) {
            List tasks = new LinkedList();
            while (cursor.moveToNext()) {
                int Title_index = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_TITLE);
                int Description_index = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_DESCRIPTION);
                int IsFavorite_index = cursor.getColumnIndexOrThrow(DatabaseDAO.COLUMN_IS_FAVORITE);
                String title = cursor.getString(Title_index);
                String description = cursor.getString(Description_index);
                boolean isFavorite = cursor.getInt(IsFavorite_index) == 1;
                tasks.add(new Task(title, description, isFavorite));
            }
            return tasks;
        }
    }

    public void delete(Task task) {
        mDB.delete(DB_TABLE, COLUMN_TITLE + "=? AND " +
        COLUMN_DESCRIPTION + "=?", new String[]{task.getTitle(),task.getDescription()});
    }

    private class DBHelper extends SQLiteOpenHelper {

        private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
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


