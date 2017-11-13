package com.example.as.uestc.base.mvp.view;

import android.app.Activity;

import com.example.as.uestc.Answer.beans.ClassList;
import com.example.as.uestc.Answer.beans.CurrentClass;
import com.example.as.uestc.base.mvp.EventListener;
import com.example.as.uestc.base.mvp.presenter.BasePresenter;

/**
 * Created by as on 2017/11/5.
 */

public interface BaseView extends MVPView {


    EventListener listener=null;
    EventListener getListener();
    void setEventListener(EventListener eventListener);
    public void attachPresenter(BasePresenter basePresenter);

    public void detachPrenster();

    public abstract void initView(Activity activity,ClassList classList);
    public abstract void pushDataToInternet();
    void reflashView(CurrentClass currentClass);
}
