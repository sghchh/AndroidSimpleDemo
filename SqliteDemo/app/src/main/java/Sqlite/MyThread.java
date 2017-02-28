package Sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.widget.SimpleCursorAdapter;

/**
 * Created by as on 2017/2/23.
 */

public class MyThread extends Thread {
    public SQLiteDatabase sqLiteDatabase;
    public String SQL;
    public Handler handler;
    public MyThread(SQLiteDatabase sqLiteDatabase,String SQL)
    {
        this.sqLiteDatabase=sqLiteDatabase;
        this.SQL=SQL;
    }

    @Override
    public void run() {
       sqLiteDatabase.execSQL(SQL);
    }
}
