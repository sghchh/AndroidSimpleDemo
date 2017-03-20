package com.example.as.mymenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import MyMenu.MyMenu;

public class MainActivity extends AppCompatActivity {
    private MyMenu myMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myMenu=(MyMenu)findViewById(R.id.mymenu);

    }
}
