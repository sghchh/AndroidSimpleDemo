package com.example.asus.sqlitedemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Sqlite.FinalVaules;
import Sqlite.MyDatabaseManager;
import Sqlite.MySqliteHelper;
import Sqlite.MyThread;

public class Main2Activity extends AppCompatActivity {
    private EditText EDITTEXT;
    private Button CANCLE,SURE;
    private MySqliteHelper helper;
    private SQLiteDatabase db;
    private MyApplication app1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        app1=(MyApplication)getApplication();
        //在布局中获得edittext和button控件
        EDITTEXT=(EditText)findViewById(R.id.edittext_main2);
        CANCLE=(Button)findViewById(R.id.cancle_button);
        SURE=(Button)findViewById(R.id.sure_button);
        //获取helper对象用来操作数据库
        helper=new MySqliteHelper(Main2Activity.this);
        app1.setDatabase(helper);
        /*
        为“取消”按钮设置监听事件，
        点击“取消”按钮时，
        对编辑框中的文字不做处理，
        直接返回MainActivity
         */
        CANCLE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_cancle=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent_cancle);
                finish();
            }
        });
        /*
        为“确定”按钮设置监听事件
        点击时将编辑框中的文字导入到数据库中
        之后返回MainActivity
         */
        SURE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String context_add=EDITTEXT.getText().toString();
                //如果编辑框不为空则插入到数据库中
                if ((context_add!=null&&!context_add.equals("")))
                {

                    /*MyDatabaseManager MANAGER=new MyDatabaseManager();
                    MANAGER.INSERT_DATA(app1.database,context_add);
                    String SQL_INSERT="insert into "+ FinalVaules.Table_name+" values(null,'"+context_add+"')";
                    new MyThread(app1.database,SQL_INSERT).start();*/

                    //获得数据库对象
                    //db=helper.getReadableDatabase();
                    //通过DatabaseManager对象来使用操作数据库的方法
                    MyDatabaseManager MANAGER=new MyDatabaseManager();
                    MANAGER.INSERT_DATA(app1.database,context_add);
                }
                else
                {
                    Toast.makeText(Main2Activity.this,"添加失败：文本内容为空",Toast.LENGTH_SHORT).show();
                }
                Intent intent_sure=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent_sure);
                System.out.println(app1.database.isOpen());
                finish();
            }
        });
    }
}
