package myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.as.topbar.R;

/**
 * Created by as on 2017/3/10.
 */

public class Topbar extends RelativeLayout {
    /*
    1.控件中间显示的文字
    2.文字的颜色
    3.文字的大小
     */
    private String title;
    private int titleTextColour;
    private float titleTextSize;
    /*
    1.左边button的文字
    2.左边button的文字的颜色
     */
    private String leftText;
    private int leftTextColour;
    /*
    1.右边button的文字
    2.右边button的文字的颜色
     */
    private String rightText;
    private int rightTextColour;
    /*
    1.左侧button的params
    2.右侧button的params
    3.中间显示的文本的params
     */
    private LayoutParams leftParams;
    private LayoutParams rightParams;
    private LayoutParams titleParams;
    /*
    左右两侧的button
     */
    private Button leftButton,rightButton;
    //显示中间文本的TextView
    private TextView titleView;
    public Topbar(Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
        //获得封装了属性值的数组
        TypedArray typedArray=context.obtainStyledAttributes(attributeSet, R.styleable.Topbar);

        //先为中间显示的文本设置属性
        title=typedArray.getString(R.styleable.Topbar_title);
        titleTextSize=typedArray.getDimension(R.styleable.Topbar_titleTextSize,60);
        titleTextColour=typedArray.getColor(R.styleable.Topbar_titleTextColour, Color.GREEN);

        //为左侧button设置属性
        leftText=typedArray.getString(R.styleable.Topbar_leftText);
        leftTextColour=typedArray.getColor(R.styleable.Topbar_titleTextColour,Color.RED);

        //为右侧button设置属性
        rightText=typedArray.getString(R.styleable.Topbar_rightText);
        rightTextColour=typedArray.getColor(R.styleable.Topbar_rightTextColour,Color.RED);

        //回收资源
        typedArray.recycle();

        /*
        初始化控件
         */
        leftButton=new Button(context);
        rightButton=new Button(context);
        titleView=new TextView(context);

        /*
        为中间控件设置属性
         */
        titleView.setText(title);
        titleView.setTextSize(titleTextSize);
        titleView.setTextColor(titleTextColour);

        /*
        为左侧button设置属性
         */
        leftButton.setText(leftText);
        leftButton.setTextColor(leftTextColour);

        /*
        为右侧button设置属性
         */
        rightButton.setText(rightText);
        rightButton.setTextColor(rightTextColour);

        //为中间控件设置params
        titleParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        titleView.setLayoutParams(titleParams);

        //为左侧button设置params
        leftParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_LEFT,TRUE);
        leftButton.setLayoutParams(leftParams);

        //为右侧button设置params
        rightParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_RIGHT,TRUE);
        rightButton.setLayoutParams(rightParams);

        addView(titleView);
        addView(leftButton);
        addView(rightButton);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
