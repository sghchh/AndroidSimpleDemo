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

public class AddBorrowDataDialog extends DialogFragment {
    private EditText name,bookname,date;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.note_fragment_borrow_dialog_add,null);

        name=(EditText)view.findViewById(R.id.note_fragment_borrow_dialog_add_name);
        bookname=(EditText)view.findViewById(R.id.note_fragment_borrow_dialog_add_bookname);
        date=(EditText)view.findViewById(R.id.note_fragment_borrow_dialog_add_date);

        builder.setView(view)
                .setPositiveButton("借阅", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*
                        先判断内容是不是有空
                         */
                        if(name.getText()==null||name.getText().toString().equals(""))
                        {
                            Toast.makeText(getActivity(),"请填写借书人姓名",Toast.LENGTH_LONG).show();
                        }
                        else if(bookname.getText()==null||bookname.getText().toString().equals(""))
                        {
                            Toast.makeText(getActivity(),"请填写所借书籍名称",Toast.LENGTH_LONG).show();
                        }
                        else if (date.getText()==null||date.getText().toString().equals(""))
                        {
                            Toast.makeText(getActivity(),"预计归还时间不能为空",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent intent=new Intent();

                            /*
                            为Intent添加数据包
                             */
                            Bundle bundle=new Bundle();
                            bundle.putString("name",name.getText().toString());
                            bundle.putString("bookname",bookname.getText().toString());
                            bundle.putString("date",date.getText().toString());

                            intent.putExtras(bundle);

                            getTargetFragment().onActivityResult(C.ADDBORROWDATADIALOG, Activity.RESULT_OK,intent);

                            FragmentTransaction transaction=getFragmentManager().beginTransaction();
                            transaction.remove(AddBorrowDataDialog.this);
                            transaction.commit();
                        }
                    }
                })
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FragmentTransaction transaction=getFragmentManager().beginTransaction();
                        transaction.remove(AddBorrowDataDialog.this);
                        transaction.commit();
                    }
                });
        return builder.create();
    }
}
