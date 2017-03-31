package com.example.as.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button1,button2,button3,button4,button5,button6;
    private String[] content=new String[]{"六芒星","三勾玉","写轮眼"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //用来实现简单对话框
        button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("我的对话框")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("简单实现的对话框")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"确定按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"取消按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();
            }
        });

        //用来实现简单列表项对话框
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("单选列表项简单对话框")
                        .setIcon(R.mipmap.eye)
                        .setItems(content, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"你选中了 "+content[i],Toast.LENGTH_LONG).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"确定按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"取消按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();

            }
        });

        //用来实现多选列表项对话框
        button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("多选列表项对话框")
                        .setIcon(R.mipmap.eye1)
                        .setMultiChoiceItems(content, new boolean[]{false, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                b=b==false?true:false;
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"确定按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"取消按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();
            }
        });

        //用来实现自定义列表想对话框
        button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("自定义列表项的对话框")
                        .setIcon(R.mipmap.eye2)

                        //设置adapter
                        //第二个参数是OnClickListener监听器
                        .setAdapter(new BaseAdapter() {
                            @Override
                            public int getCount() {
                                return content.length;
                            }

                            @Override
                            public Object getItem(int i) {
                                return content[i];
                            }

                            @Override
                            public long getItemId(int i) {
                                return i;
                            }

                            @Override
                            public View getView(int i, View view, ViewGroup viewGroup) {
                                LinearLayout line = new LinearLayout(MainActivity.this);
                                //添加这个imageview主要是区别前面的列表对话框
                                ImageView imageView=new ImageView(MainActivity.this);
                                line.addView(imageView);
                                imageView.setImageResource(R.mipmap.eye);
                                //为imageview设置layout属性
                                ViewGroup.LayoutParams layout=imageView.getLayoutParams();
                                layout.width=200;
                                layout.height=200;
                                imageView.setLayoutParams(layout);
                                //为textview设置相关属性
                                TextView textView = new TextView(MainActivity.this);
                                line.addView(textView);
                                textView.setText(content[i]);
                                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                return line;
                            }
                        },
                                //这是setAdapter的第二个参数，监听器
                                new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"你选中了 "+content[i],Toast.LENGTH_LONG).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"确定按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"取消按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();

            }
        });

        //用来实现自定义view对话框
        button5=(Button)findViewById(R.id.button5);
        final LinearLayout line=(LinearLayout) getLayoutInflater().inflate(R.layout.dialog,null);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("自定义view的对话框")
                        .setIcon(R.mipmap.eye3)
                        .setView(line)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"确定按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"取消按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();
            }
        });

        //单选列表项对话框
        button6=(Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("自定义view的对话框")
                        .setIcon(R.mipmap.eye3)
                        .setSingleChoiceItems(content, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"你选中了"+content[i],Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"确定按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this,"取消按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();
            }
        });
    }
}
