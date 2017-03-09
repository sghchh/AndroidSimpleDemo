package com.example.as.topbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import myview.Topbar;

public class MainActivity extends AppCompatActivity {
    private Topbar topbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topbar=(Topbar)findViewById(R.id.topbar);
    }
}
