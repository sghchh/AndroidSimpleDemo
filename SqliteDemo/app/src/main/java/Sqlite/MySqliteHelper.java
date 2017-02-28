package Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ASUS on 2017/2/6.
 */

public class MySqliteHelper extends SQLiteOpenHelper {
    public String create_table="create table "+FinalVaules.Table_name+"(_id integer primary key autoincrement,content verchar unique)";
    public String create_index="create index  ID on "+FinalVaules.Table_name+"(_id)";
    public String select_table="select * from"+FinalVaules.Table_name;
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public MySqliteHelper(Context context){
        super(context,FinalVaules.Sqlite_name,null,FinalVaules.version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
        db.execSQL(create_index);
        Log.i("----------", "onCreate----------");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("-------", "onUpgrade-------");
    }
}
