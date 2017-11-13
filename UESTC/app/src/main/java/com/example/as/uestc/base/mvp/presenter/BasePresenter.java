package com.example.as.uestc.base.mvp.presenter;

import android.app.Activity;

import com.example.as.uestc.base.mvp.model.BaseModel;
import com.example.as.uestc.base.mvp.view.BaseView;

import java.util.List;

/**
 * Created by as on 2017/11/5.
 */

public interface BasePresenter extends MVPPresenter {
    void attach(Activity activity,BaseView answerView, BaseModel answerModel);
    List getInitDataFromModel();
    void pushScores();
    List getClassDetails();
    BaseView getView();
    BaseModel getModel();
    Activity getActivity();
}
