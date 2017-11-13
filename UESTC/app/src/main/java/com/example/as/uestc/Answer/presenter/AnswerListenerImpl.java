package com.example.as.uestc.Answer.presenter;

import com.example.as.uestc.Answer.beans.PostData;
import com.example.as.uestc.Answer.view.AnswerActivity;
import com.example.as.uestc.Answer.view.AnswerView;

/**
 * Created by as on 2017/11/5.
 */

public class AnswerListenerImpl extends AnswerListener {

    @Override
    public void notifyTickView(AnswerView answerView) {
        AnswerActivity activity=(AnswerActivity)answerView;
        activity.notifyTickViewChange();
    }

    public void postScore(PostData data)
    {
        ((AnswerPreImpl)getPresenter()).postScore(data);
    }
}
