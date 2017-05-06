package com.example.as.urlpicturedownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView show;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.dowmload);
        show=(ImageView)findViewById(R.id.show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread()
                {
                    @Override
                    public void run() {
                        try
                        {
                            URL url=new URL("http://www.crazyit.org/attachments/month_1008/20100812_7763e970f822325bfb019ELQVym8tW3A.png");
                            Log.d("", "run: 资源名"+url.getFile()+"主机名"+url.getHost()+"port:"+url.getPort());
                            //打开url对应的输入流
                            InputStream in =url.openStream();
                            //从输入流解析图片
                            bitmap= BitmapFactory.decodeStream(in);
                            //发送消息通知显示该图片
                            handler.sendEmptyMessage(0x123);
                            in.close();
                            //再次打开URL对应的输入流
                            in=url.openStream();
                            //打开手机文件对应的输入出流
                            OutputStream out=openFileOutput("crazyit.png",MODE_PRIVATE);
                            byte[] buff=new byte[1024];
                            int hasRead=0;
                            //将图片下载到本地
                            while ((hasRead=in.read(buff))>0)
                            {
                                out.write(buff,0,hasRead);
                            }
                            in.close();
                            out.close();

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0x123)
            {
                show.setImageBitmap(bitmap);
            }
        }
    };
}
