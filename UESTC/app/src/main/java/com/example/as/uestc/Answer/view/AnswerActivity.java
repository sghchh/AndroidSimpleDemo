package com.example.as.uestc.Answer.view;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.as.uestc.Answer.TickView.TickView;
import com.example.as.uestc.Answer.beans.ClassList;
import com.example.as.uestc.Answer.beans.CurrentClass;
import com.example.as.uestc.Answer.fragments.MainFragment;
import com.example.as.uestc.Answer.model.AnswerModelImpl;
import com.example.as.uestc.Answer.presenter.AnswerListenerImpl;
import com.example.as.uestc.Answer.presenter.AnswerPreImpl;
import com.example.as.uestc.Answer.view.adapter.MyAdapter;
import com.example.as.uestc.R;

public class AnswerActivity extends AnswerView {

    public TextView test;
    private String TOKEN;
    private AnswerPreImpl pre;
    private RecyclerView recycler;
    private ImageView head;
    private MainFragment fragment;

    private MyAdapter.RecyclerClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        TOKEN=getIntent().getStringExtra("token");
        setEventListener(new AnswerListenerImpl());
        pre=new AnswerPreImpl(this,this,new AnswerModelImpl());
        pre.loadCurrentClass();
    }

    @Override
    public void initView(Activity activity,ClassList classList) {
        test=(TextView)activity.findViewById(R.id.test);
        test.setText(TOKEN);
        head=(ImageView)activity.findViewById(R.id.answer_activity_head);
        recycler=(RecyclerView)activity.findViewById(R.id.answer_acitvity_recycler);
        LinearLayoutManager line=new LinearLayoutManager(activity);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        final MyAdapter myAdapter=new MyAdapter(classList,activity);
        recycler.setLayoutManager(line);
        recycler.setAdapter(myAdapter);

        listener=new MyAdapter.RecyclerClickListener() {
            @Override
            public void recyclerClick(int position) {

            }
        };

        /*
        fragment=new MainFragment(getListener());
        Bundle data=new Bundle();
        data.putString("token",TOKEN);
        data.putString("classID",classList.getInfo().get(0).getClassID());
        fragment.setArguments(data);
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.answer_activity_fragment,fragment);
        transaction.commit();
        */
    }

    @Override
    public void pushDataToInternet() {

    }

    @Override
    public void reflashView(CurrentClass currentClass) {
        fragment=new MainFragment(getListener(),currentClass);
        Bundle id=new Bundle();
        id.putString("token",TOKEN);
        fragment.setArguments(id);

        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.answer_activity_fragment,fragment);
        transaction.commit();
    }


    public void notifyTickViewChange()
    {
        View view=recycler.getChildAt(0);
        ((TickView)view.findViewById(R.id.recycler_item_tick)).setChecked(true);
    }

}
