package Sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.asus.sqlitedemo.MainActivity;
import com.example.asus.sqlitedemo.R;

/**
 * Created by ASUS on 2017/2/7.
 */

public class MyDatabaseManager {
    /*
    定义一个插入字符串的工具方法
    第一个参数：数据库对象
    第二个参数：需要存入数据库的字符串
     */

   public void INSERT_DATA(SQLiteDatabase db,String string)
   {
       String SQL_INSERT="insert into "+FinalVaules.Table_name+" values(null,'"+string+"')";
       db.execSQL(SQL_INSERT);
       db.close();
   }
    public void cancleByID(SQLiteDatabase db,int id)
    {
        String sql="delete from "+FinalVaules.Table_name+" where _id = "+id;
        db.execSQL(sql);
    }

}
