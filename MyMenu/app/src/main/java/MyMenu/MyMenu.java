package MyMenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.as.mymenu.R;

/**
 * Created by as on 2017/3/20.
 */

public class MyMenu extends ViewGroup {
    //半径
    public int mRadius;

    /**
     * 枚举类，菜单的状态
     */

    public enum Status
    {
        CLOSE,OPEN;
    }

    //当前的状态
    public Status currentStatus=Status.CLOSE;

    //主控件
    public View mainView;

    /**
     *   ViewGroup的宽和高
     */
    public int parentsHeight;
    public int parentsWidth;

    /**
     * 主控件的监听器
     */
    public interface OnMainViewClickListenter
    {
        public abstract void click();
    }
    
    /*
    主控件的监听器
     */
    public OnMainViewClickListenter onMainViewClickListenter;

    /**
     *为主控件设置监听器的set方法
     */
    public void setOnMainViewClickListenter(OnMainViewClickListenter onMainViewClickListenter)
    {
        this.onMainViewClickListenter=onMainViewClickListenter;
    }

    public MyMenu(Context context)
    {
        super(context);
    }

    public MyMenu(Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
        TypedArray typeArray=context.obtainStyledAttributes(attributeSet, R.styleable.MyMenu);
        //获取半径，并且设置默认值为100dp
        mRadius=(int) typeArray.getDimension(R.styleable.MyMenu_radius, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,getResources().getDisplayMetrics()));
        typeArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount=getChildCount();
        for(int i=0;i<childCount;i++)
        {
            measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        if (b)
        {
            setmainViewPosition();
            setChildLayout();
            mainView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeCurrentStatus();
                    int count=getChildCount();
                    for (int i=1;i<count;i++)
                    {
                        View childView=getChildAt(i);
                        setChildAnimation(childView,i);
                    }
                    setRotaForMainView(view,0f,720f,500);
                    }
            });
        }

    }

    private void setChildAnimation(final View childView, int i) {
        int transleft=(int) ((mRadius)*Math.cos(Math.PI*2/(getChildCount()-1)*(i-1)));
        int transtop=(int)((mRadius)*Math.sin(Math.PI*2/(getChildCount()-1)*(i-1)));

        AnimationSet anim=new AnimationSet(true);
        TranslateAnimation trans=null;

        /**
         * 为自控件添加旋转动画
         */
        RotateAnimation rotate=new RotateAnimation(0f,720f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(500);
        rotate.setFillAfter(true);

        /**
         * 为子控件添加平移动画
         */
        if (currentStatus==Status.CLOSE)
        {
            trans=new TranslateAnimation(0,transleft,0,transtop);
            trans.setFillAfter(true);
            trans.setDuration(500);
            childView.setClickable(true);
            childView.setFocusable(true);
        }
        else
        {
            childView.setVisibility(View.VISIBLE);
            trans=new TranslateAnimation(transleft,0,transtop,0);
            trans.setFillAfter(true);
            trans.setDuration(500);
            childView.setClickable(true);
            childView.setFocusable(true);
        }

        //添加监听事件，动画结束后如果状态为CLOSE则子空间不可见
        trans.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (currentStatus==Status.CLOSE)
                    childView.setVisibility(GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        /*
        为控件设置动画
        注意：动画的添加顺序不同，效果可能不同
        此处应该先添加旋转动画
         */
        anim.addAnimation(rotate);
        anim.addAnimation(trans);
        childView.startAnimation(anim);


    }

    /**
     * 为主控件设置旋转动画
     */
    private void setRotaForMainView(View view,float x,float y,int time) {
        RotateAnimation rotate=new RotateAnimation(x,y, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(time);
        rotate.setFillAfter(true);
        view.startAnimation(rotate);

    }


    /**
     * 设置主控件的layout
     */
    private void setmainViewPosition() {
        mainView=getChildAt(0);
        parentsHeight=getMeasuredHeight();
        parentsWidth=getMeasuredWidth();
        /**
         * 主控件的宽高
         */
        int mainViewWidth=mainView.getMeasuredWidth();
        int mainViewHeight=mainView.getMeasuredHeight();

        /**
         * 主控件的实际位置
         */
        int mainStartX=(parentsWidth-mainViewWidth)/2;
        int mainStartY=(parentsHeight-mainViewHeight)/2;
        int mainEndX=(parentsWidth+mainViewWidth)/2;
        int mainEndY=(parentsHeight+mainViewHeight)/2;

        mainView.layout(mainStartX,mainStartY,mainEndX,mainEndY);

    }

    /**
     * 设置子控件的layout
     */
    private void setChildLayout() {
        /*
        获得父容器的宽高参数
         */
        parentsWidth=getMeasuredWidth();
        parentsHeight=getMeasuredHeight();
        //子控件
        View childView;
        int count=getChildCount();
        for (int i=1;i<count;i++)
        {
            childView=getChildAt(i);
            /*
            如果当前是GONE状态，则子控件不可见
            如果当前是VISUABLE状态，则子控件可见
             */
            if (this.currentStatus==Status.CLOSE)
            {
                childView.setVisibility(View.GONE);
            }
            else
            {
                childView.setVisibility(View.VISIBLE);
            }
            int childWidth=childView.getMeasuredWidth();
            int childHeight=childView.getMeasuredHeight();
            /**
             * 每一个子控件相对于主控件的位置
             */
            int childStarX=(int)(parentsWidth/2-childView.getMeasuredWidth()/2-((mRadius)*Math.cos(Math.PI*2/(count-1)*(i-1))));
            int childStartY=(int)(parentsHeight/2-childView.getMeasuredHeight()/2-((mRadius)*Math.sin(Math.PI*2/(count-1)*(i-1))));
            childView.layout(childStarX,childStartY,childStarX+childView.getMeasuredWidth(),childStartY+childView.getMeasuredHeight());

        }
    }

    /**
     * 切换当前状态
     */
    public void changeCurrentStatus()
    {
        currentStatus=(currentStatus==Status.CLOSE?Status.OPEN:Status.CLOSE);
    }
}
