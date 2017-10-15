package com.example.as.note.note.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.as.note.R;


/**
 * Created by as on 2017/10/6.
 */

public class MatterDialog extends DialogFragment {
    private TextView title,content;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        //加载布局
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.note_fragment_matter_dialog_show,null);

        //得到MatterFragment传过来的数据
        Bundle data=getArguments();

        //初始化title文本框，并且为它设置内容
        title=(TextView)view.findViewById(R.id.note_fragment_matter_dialog_show_title);
        title.setText(data.getString("title"));

        //初始化content文本框，并未它设置内容
        content=(TextView)view.findViewById(R.id.note_fragment_matter_dialog_show_content);
        content.setText(data.getString("content"));

        builder.setView(view)
                .setNeutralButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FragmentTransaction transaction=getFragmentManager().beginTransaction();
                        transaction.remove(MatterDialog.this);
                        transaction.commit();
                    }
                });
        return builder.create();
    }
}
