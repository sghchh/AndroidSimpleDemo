package com.example.as.note.note.fragments.dialog;

import android.app.Activity;
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
import com.example.as.note.note.constant.C;
import com.example.as.note.note.model.BorrowManager;


/**
 * Created by as on 2017/10/6.
 */

public class BorrowDialog extends DialogFragment {
    private TextView name,bookname,date;
    private BorrowManager manager=new BorrowManager();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        manager.initRealm();
        //加载布局
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.note_fragment_borrow_dialog_show,null);

        //得到Bundle中的数据
        final Bundle bundle=getArguments();

        //初始化name文本框，并为其设置内容
        name=(TextView)view.findViewById(R.id.note_fragment_borrow_dialog_show_name);
        name.setText(bundle.getString("name"));

        //初始化bookname文本框，并为其设置内容
        bookname=(TextView)view.findViewById(R.id.note_fragment_borrow_dialog_show_bookname);
        bookname.setText(bundle.getString("bookname"));

        //初始化date文本框，并为其设置内容
        date=(TextView)view.findViewById(R.id.note_fragment_borrow_dialog_show_date);
        date.setText(bundle.getString("date"));

        builder.setView(view)
                .setNeutralButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FragmentTransaction transaction=getFragmentManager().beginTransaction();
                        transaction.remove(BorrowDialog.this);
                        transaction.commit();
                    }
                })
        .setPositiveButton("还书", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                manager.deleteBorrowData(bundle.getString("name"),bundle.getString("bookname"));
                getTargetFragment().onActivityResult(C.BORROWDIALOG, Activity.RESULT_OK,null);
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.remove(BorrowDialog.this);
                transaction.commit();
            }
        });
        return builder.create();
    }
}
