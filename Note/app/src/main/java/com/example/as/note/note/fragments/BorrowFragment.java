package com.example.as.note.note.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.as.note.R;
import com.example.as.note.note.constant.C;
import com.example.as.note.note.fragments.dialog.AddBorrowDataDialog;
import com.example.as.note.note.fragments.dialog.BorrowDialog;
import com.example.as.note.note.model.BorrowManager;
import com.example.as.note.note.model.objects.BorrowData;

import java.util.List;

/**
 * Created by as on 2017/10/6.
 */

public class BorrowFragment extends Fragment implements AdapterView.OnItemClickListener{

    private Toolbar toolbar;
    private ListView listView;
    private BorrowManager manager=new BorrowManager();
    private BaseAdapter adapter;
    private List<String> list;
    private int delete;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.note_fragment_borrow_page,container,false);

        //初始化数据库链接
        manager.initRealm();

        /*
        初始化Toolbar
         */
        toolbar=(Toolbar)view.findViewById(R.id.note_fragment_borrow_page_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list=manager.getBorrowList();
        /*
        初始化ListView
         */
        listView=(ListView)view.findViewById(R.id.note_fragment_borrow_page_listview);

        adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int i) {
                return list.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view==null)
                {
                    view=getActivity().getLayoutInflater().inflate(R.layout.note_fragment_borrow_list_item,null);

                    BorrowHolder holder=new BorrowHolder();
                    holder.textView=(TextView)view.findViewById(R.id.note_fragment_borrow_list_item_text);

                    view.setTag(holder);
                }

                ((BorrowHolder)view.getTag()).textView.setText(list.get(i));
                return view;
            }
        };

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        delete=i;
        BorrowDialog dialog=new BorrowDialog();
        Bundle bundle=new Bundle();
        BorrowData data=manager.getBorrowDataAt(i);
        bundle.putString("name",data.getName());
        bundle.putString("bookname",data.getBookName());
        bundle.putString("date",data.getDate());
        dialog.setTargetFragment(BorrowFragment.this, C.BORROWDIALOG);
        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(),null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.note_borrow_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.wifi_fragment,new NoteFragment());
                transaction.commit();
                break;
            case R.id.menu_borrow_add:
                AddBorrowDataDialog dataDialog=new AddBorrowDataDialog();
                dataDialog.setTargetFragment(this, C.ADDBORROWDATADIALOG);
                dataDialog.show(getFragmentManager(),null);
                break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==C.ADDBORROWDATADIALOG)
        {
            Bundle bundle=data.getExtras();
            manager.addBorrowData(bundle.getString("name"),bundle.getString("bookname"),bundle.getString("date"));
            list.add(bundle.getString("bookname"));
            adapter.notifyDataSetChanged();
        }
        if(requestCode==C.BORROWDIALOG)
        {
            list.remove(delete);
            adapter.notifyDataSetChanged();
        }
    }

    class BorrowHolder{
        TextView textView;
    }
}
