package com.example.as.uestc.Answer.presenter;

import android.app.Activity;

import com.example.as.uestc.base.mvp.model.BaseModel;
import com.example.as.uestc.base.mvp.presenter.BasePresenter;
import com.example.as.uestc.base.mvp.view.BaseView;

/**
 * Created by as on 2017/11/5.
 */

abstract class AnswerPre implements BasePresenter {
    private BaseView mAnswerView;
    private BaseModel mAnswerModel;

    private Activity activity;
    @Override
    public void attach(Activity activity,BaseView baseView, BaseModel baseModel) {
        this.mAnswerModel=baseModel;
        this.mAnswerView=baseView;
        this.activity=activity;
        baseView.attachPresenter(this);
        baseView.getListener().attachPresenter(this);

    }

    @Override
    public Activity getActivity() {
        return activity;
    }

    public BaseModel getModel() {
        return mAnswerModel;
    }

    @Override
    public BaseView getView() {
        return mAnswerView;
    }
}
