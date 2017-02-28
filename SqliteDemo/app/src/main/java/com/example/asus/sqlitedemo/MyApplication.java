package com.example.asus.sqlitedemo;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Sqlite.FinalVaules;
import Sqlite.MySqliteHelper;

/**
 * Created by as on 2017/2/23.
 */
public class MyApplication extends Application {
    public SQLiteDatabase database;
    final String SQL = "select * from " + FinalVaules.Table_name;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public SQLiteDatabase getDatabase()
    {
        return this.database;
    }
    public void setDatabase(MySqliteHelper msh)
    {
        this.database=msh.getReadableDatabase();
    }
}
