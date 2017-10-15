package com.example.as.note.note.model;


import com.example.as.note.note.model.objects.BorrowData;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by as on 2017/10/6.
 */

public class BorrowManager extends RealmManager {
    private Realm borrowRealm;
    @Override
    public void initRealm() {
        if(borrowRealm==null)
        {
            RealmConfiguration configuration=new RealmConfiguration.Builder().name("borrow.realm").build();
            borrowRealm=Realm.getInstance(configuration);
        }
    }

    /**
     * 得到打包了BorrowData数据库中的bookName数据，作为BorrowFragment中ListView的数据源
     * @return
     */
    public List<String> getBorrowList()
    {
        List<String> list=new ArrayList<>();
        RealmResults<BorrowData> realmResults=borrowRealm.where(BorrowData.class).findAll();
        for(int i=0;i<realmResults.size();i++)
        {
            list.add(realmResults.get(i).getBookName());
        }
        return list;
    }

    /**
     * 得到特定位置的BorrowData数据
     * 点击BorrowFragment中的listview的item时弹出对话框展示详情是被调用
     * @param position 返回的数据的位置
     * @return 得到的BorrowData数据
     */
    public BorrowData getBorrowDataAt(int position)
    {
        if(borrowRealm==null)
            return null;
        return borrowRealm.where(BorrowData.class).findAll().get(position);
    }

    /**
     * 往数据库中添加一个新的元素
     * 在AddBorrowDataDialog中点击“借阅”按钮时被调用
     * @param name 新添加的数据的name值
     * @param bookName 新添加的数据的bookName值
     * @param date 新添加的数据的date的值
     */
    public void addBorrowData(final String name, final String bookName, final String date)
    {
        if(borrowRealm!=null)
        {
            borrowRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    BorrowData data=realm.createObject(BorrowData.class);
                    data.setName(name);
                    data.setBookName(bookName);
                    data.setDate(date);
                }
            });
        }
    }

    /**
     * 根据搜索框中的名字，找到这个人借的所有书
     * @param name
     * @return
     */
    public List<String> getSearchResult(final String name)
    {
        final List<String> list=new ArrayList<>();
        borrowRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<BorrowData> realmResults=realm.where(BorrowData.class).equalTo("name",name).findAll();
                for(int i=0;i<realmResults.size();i++)
                {
                    list.add(realmResults.get(i).getBookName());
                }
            }
        });
        return list;
    }

    /**
     * 删除还书的项
     * @param name
     * @param bookname
     */
    public void deleteBorrowData(final String name, final String bookname)
    {
        borrowRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<BorrowData> datas=realm.where(BorrowData.class).equalTo("name",name).equalTo("bookName",bookname).findAll();
                datas.deleteAllFromRealm();
            }
        });
    }
}
