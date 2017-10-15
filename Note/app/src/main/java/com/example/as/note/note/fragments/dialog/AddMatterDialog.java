package com.example.as.note.note.fragments.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.as.note.R;
import com.example.as.note.note.constant.C;

/**
 * Created by as on 2017/10/6.
 */

public class AddMatterDialog extends DialogFragment {
    private EditText title,content;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.note_fragment_matter_dialog_add,null);

        title=(EditText)view.findViewById(R.id.note_fragment_matter_dialog_edit_title);
        content=(EditText)view.findViewById(R.id.note_fragment_matter_dialog_edit_content);

        builder.setView(view).setPositiveButton("添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                /*
                先判断内容是否为空，如果为空，则不保存，并且弹出一个提示框
                 */
                if(title.getText()==null||title.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(),"标题不能为空",Toast.LENGTH_LONG).show();
                } else if (content.getText()==null||content.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(),"内容不能为空",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent=new Intent();

                /*
                为intent添加数据
                 */
                    Bundle data=new Bundle();
                    data.putString("title",title.getText().toString());
                    data.putString("content",content.getText().toString());

                    //把数据包添加到intent中
                    intent.putExtras(data);
                    getTargetFragment().onActivityResult(C.ADDMATTERDIALOG, Activity.RESULT_OK,intent);

                    FragmentTransaction transaction=getFragmentManager().beginTransaction();
                    transaction.remove(AddMatterDialog.this);
                    transaction.commit();
                }

            }
        })
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FragmentTransaction transaction=getFragmentManager().beginTransaction();
                        transaction.remove(AddMatterDialog.this);
                        transaction.commit();
                    }
                });
        return builder.create();
    }
}
