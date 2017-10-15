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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.as.note.R;
import com.example.as.note.note.constant.C;
import com.example.as.note.note.fragments.dialog.AddMatterDialog;
import com.example.as.note.note.fragments.dialog.MatterDialog;
import com.example.as.note.note.model.MatterManager;
import com.example.as.note.note.model.objects.Matter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by as on 2017/10/6.
 */

public class MatterFragment extends Fragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    private ListView listView;
    private Toolbar toolbar;
    private MatterManager matterManager=new MatterManager();
    private List<String> list;
    private BaseAdapter adapter;
    //用于动态控制menuitem的显示和隐藏
    private Menu menu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置显示菜单项
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.note_fragment_matter_page,container,false);

        //初始化数据库链接
        matterManager.initRealm();

        /*
        初始化Toolbar
         */
        toolbar=(Toolbar)view.findViewById(R.id.note_fragment_matter_page_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //初始化ListView
        listView=(ListView) view.findViewById(R.id.note_fragment_matter_page_listview);

        list=matterManager.getMatterList();

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
                    view=getActivity().getLayoutInflater().inflate(R.layout.note_fragment_matter_list_item,null);

                    /*
                    初始化MatterViewHolder
                     */
                    MatterViewHolder holder=new MatterViewHolder();
                    holder.textView=(TextView)view.findViewById(R.id.note_fragment_matter_list_item_text);
                    holder.checkBox=(CheckBox)view.findViewById(R.id.note_fragment_matter_list_item_box);
                    view.setTag(holder);
                }
                ((MatterViewHolder)view.getTag()).textView.setText(list.get(i));
                ((MatterViewHolder)view.getTag()).checkBox.setVisibility(View.INVISIBLE);
                return view;
            }
        };

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.note_menu,menu);
        this.menu=menu;
        menu.findItem(R.id.menu_matter_delete).setVisible(false);
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
            case R.id.menu_matter_add:
                AddMatterDialog dialog=new AddMatterDialog();
                dialog.setTargetFragment(this, C.ADDMATTERDIALOG);
                dialog.show(getFragmentManager(),null);
                break;
            case R.id.menu_matter_delete:
                matterManager.deleteSeletedMatterDatas(getWhereItemSelected());
                deleteSeletedDatas(getWhereItemSelected());
                resetCkeckBoxState();
                item.setVisible(false);
                break;
        }
        return true;
    }

    //实现fragment之间的通信
    //实现在MatterFragment中点击menuitem项的加号时弹出对话框AddMatterFragment
    //后者传递给他新添加的matter的信息
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== C.ADDMATTERDIALOG)
        {
            Bundle bundle=data.getExtras();
            matterManager.addMatter(bundle.getString("title"),bundle.getString("content"));
            list.add(bundle.getString("title"));
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 得到CheckBox被选中的item的position，并把他们记录在数组中
     * @return
     */
    public List<Integer> getWhereItemSelected()
    {
        List<Integer> l=new ArrayList<>();
        for (int j=0;j<list.size();j++)
        {

            //通过遍历元素，是原来隐藏的CheckBox显示出来
            View v=listView.getChildAt(j);
            MatterViewHolder holder=(MatterViewHolder) v.getTag();
            if(holder.checkBox.isChecked())
                l.add(j);
        }
        return l;
    }

    /**
     * 删除CheckBox被选中的item对应的数据
     * @param l
     */
    private void deleteSeletedDatas(List<Integer> l)
    {
        for(int i=0;i<l.size();i++)
        {
            list.remove(l.get(i)-i);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 点击完删除按钮后，将所有CheckBox的状态复原为未选中的状态
     */
    private void resetCkeckBoxState()
    {
        for (int i=0;i<list.size();i++)
        {
            CheckBox checkBox=((MatterViewHolder)listView.getChildAt(i).getTag()).checkBox;
            checkBox.setVisibility(View.INVISIBLE);
            checkBox.setChecked(false);
        }
    }

    /*
    listView中item的点击监听
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MatterDialog dialog=new MatterDialog();
        Bundle bundle=new Bundle();
        Matter matter=matterManager.getMatterAt(i);
        bundle.putString("title",matter.getTitle());
        bundle.putString("content",matter.getMatter());
        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(),null);
    }

    /*
    listview长按监听
    将每一个item的CheckBox显示出来
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        for (int j=0;j<list.size();j++)
        {
            //通过遍历元素，是原来隐藏的CheckBox显示出来
            View v=listView.getChildAt(j);
            MatterViewHolder holder=(MatterViewHolder) v.getTag();
            holder.checkBox.setVisibility(View.VISIBLE);
        }

        //是“删除”item显示出来
        menu.findItem(R.id.menu_matter_delete).setVisible(true);
        return true;
    }

    /**
     * 在baseadapt中实现复用
     */
    class MatterViewHolder{
        CheckBox checkBox;
        TextView textView;
    }
}

