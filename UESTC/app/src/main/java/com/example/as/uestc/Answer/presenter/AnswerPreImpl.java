package com.example.as.uestc.Answer.presenter;

import android.app.Activity;
import android.util.Log;

import com.example.as.uestc.Answer.beans.ClassList;
import com.example.as.uestc.Answer.beans.CurrentClass;
import com.example.as.uestc.Answer.beans.PostData;
import com.example.as.uestc.Answer.beans.PostRes;
import com.example.as.uestc.Answer.model.AnswerModelImpl;
import com.example.as.uestc.Answer.view.AnswerActivity;
import com.example.as.uestc.base.mvp.model.BaseModel;
import com.example.as.uestc.base.mvp.view.BaseView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by as on 2017/11/5.
 */

public class AnswerPreImpl extends AnswerPre {

    private AnswerPreImpl instance;
    private io.reactivex.Observer<ClassList> observer;
    private Observer<CurrentClass> currentObserver;
    private Observer<PostRes> postResObserver;
    public AnswerPreImpl(Activity activity,BaseView answerView, BaseModel answerModel)
    {
        attach(activity,answerView,answerModel);
        instance=this;
    }
    @Override
    public List getInitDataFromModel() {
        return null;
    }

    @Override
    public void pushScores() {

    }

    @Override
    public List getClassDetails() {
        return null;
    }


    public void loadCurrentClass()
    {
        observer=new Observer<ClassList>() {
            Disposable disposable;
            @Override
            public void onSubscribe(Disposable d) {
                disposable=d;
            }

            @Override
            public void onNext(ClassList classList) {
                Log.d("onNext", "onNext: +++++++++++++++"+(classList ==null));
                instance.getView().initView(instance.getActivity(), classList);
                reflashFragment(classList.getInfo().get(0).getClassID());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d("", "onComplete: ++++++++++++++++++++++");
                disposable.dispose();
            }
        };
        ((AnswerModelImpl)instance.getModel()).getCurrentClass()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void reflashFragment(String classID)
    {
        currentObserver=new Observer<CurrentClass>() {
            Disposable disposable;
            @Override
            public void onSubscribe(Disposable d) {
                disposable=d;
            }

            @Override
            public void onNext(CurrentClass currentClass) {
                getView().reflashView(currentClass);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                disposable.dispose();
            }
        };
        ((AnswerModelImpl)instance.getModel()).getCurrent(classID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currentObserver);

    }

    public void postScore(PostData postData)
    {
        postResObserver=new Observer<PostRes>() {
            Disposable disposable;
            @Override
            public void onSubscribe(Disposable d) {
                disposable=d;
            }

            @Override
            public void onNext(PostRes postRes) {
                ((AnswerActivity)getView()).test.setText(postRes.getErrcode());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        ((AnswerModelImpl)instance.getModel()).getPostRes(postData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postResObserver);
    }
}


