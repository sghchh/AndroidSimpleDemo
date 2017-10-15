package com.example.as.note.note.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.as.note.R;
import com.example.as.note.note.constant.C;


/**
 * Created by as on 2017/10/6.
 */

public class NoteFragment extends Fragment implements View.OnClickListener{
    private TextView borrow,matter;


    /**
     * 定义一个接口，Activity实现这个接口
     * 点击当前Fragment中的不同的TextView，切换到对应的Fragment
     */
    public interface SwitchFragmentListener{
        void onSwitchFragment(int which);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.note_fragment_note_page,container,false);
        borrow=(TextView)view.findViewById(R.id.note_fragment_note_page_borrow);
        matter=(TextView)view.findViewById(R.id.note_fragment_note_page_matter);

        setListener();
        return view;
    }

    /*
        为所有的文本框设置点击监听器
         */
    private void setListener()
    {
        borrow.setOnClickListener(this);
        matter.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.note_fragment_note_page_borrow:
                ((SwitchFragmentListener)getActivity()).onSwitchFragment(C.BORROW);
                break;
            case R.id.note_fragment_note_page_matter:
                ((SwitchFragmentListener)getActivity()).onSwitchFragment(C.MATTER);
                break;
        }
    }
}
