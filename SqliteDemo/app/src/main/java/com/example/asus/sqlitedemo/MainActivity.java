package com.example.asus.sqlitedemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import Sqlite.FinalVaules;
import Sqlite.MyDatabaseManager;
import Sqlite.MySqliteHelper;
import Sqlite.MyThread;

public class MainActivity extends AppCompatActivity {
    private MySqliteHelper helper;
    private Button button;
    private ListView LISTVIEW;
    private MyApplication app;
    private MyDatabaseManager myDatabaseManager;
    final String SQL = "select * from " + FinalVaules.Table_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabaseManager=new MyDatabaseManager();
        app=(MyApplication)getApplication();
        //实例化helper工具类
        helper=new MySqliteHelper(this);
        app.setDatabase(helper);
        //获得SQLiteDatabase对象来进行对数据库的操作
        //final SQLiteDatabase database=helper.getReadableDatabase();
        //获得button控件
        button=(Button)findViewById(R.id.button_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });
        //获得listview控件
        LISTVIEW=(ListView)findViewById(R.id.list_main);
        //获得数据库查询后的游标
        final Cursor cursor=app.database.rawQuery(SQL,null);
        //为listview设置适配器
        final SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.cursor_adapter,cursor,new String[]{"content"},new int[]{R.id.textview_adapter},CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        LISTVIEW.setAdapter(adapter);
        LISTVIEW.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.title)
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage(R.string.message)
                        .setPositiveButton(R.string.sure_btn, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                         int id=adapter.getCursor().getInt(adapter.getCursor().getColumnIndex("_id"));
                         if(app.database.isOpen())
                         {
                             //String CANCLE_SQL="delete from "+FinalVaules.Table_name+" where _id = "+id;
                             //new MyThread(app.database,CANCLE_SQL).start();
                             myDatabaseManager.cancleByID(app.database,id);
                             //为适配器设置新的Cursor
                             adapter.changeCursor(app.database.rawQuery(SQL,null));
                         }
                        else
                         {
                             /*String CANCLE_SQL="delete from "+FinalVaules.Table_name+" where _id = "+id;
                             new MyThread(app.database,CANCLE_SQL).start();*/

                             //由于Main2Activity调用了finish方法导致数据库关闭，所以要重新打开
                             app.setDatabase(new MySqliteHelper(MainActivity.this));
                             myDatabaseManager.cancleByID(app.database,id);
                             //为适配器设置新的Cursor
                             adapter.changeCursor(app.database.rawQuery(SQL,null));
                         }
                            }
                        })
                        .setNegativeButton(R.string.cancle_btn, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create()
                        .show();
                return false;
            }
        });

        app.database.close();
    }
}
