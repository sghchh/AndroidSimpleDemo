package com.example.asus.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.widget.Button;
import android.widget.TextView;

import Sqlite.MySqliteHelper;

public class Main3Activity extends AppCompatActivity {
   private MyApplication  myApplication;
    private Button no,yes;
    private TextView textView;
    private MySqliteHelper mySqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        no=(Button)findViewById(R.id.button_no);
        yes=(Button)findViewById(R.id.button_yes);
        textView=(TextView)findViewById(R.id.textview_main3);
        myApplication=(MyApplication)getApplication();
        myApplication.setDatabase(new MySqliteHelper(Main3Activity.this));
    }
}
