package com.example.as.tickview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.as.tickview.TickView.TickView;

public class MainActivity extends AppCompatActivity {

    private TickView tickView;
    private Button button,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tickView=(TickView)findViewById(R.id.tick);
        button=(Button)findViewById(R.id.button);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tickView.setChecked(false);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tickView.setChecked(true);
            }
        });
    }
}
