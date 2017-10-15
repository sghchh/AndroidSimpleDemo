package com.example.as.note.note.model;


import com.example.as.note.note.model.objects.Matter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


/**
 * Created by as on 2017/10/6.
 */

public class MatterManager extends RealmManager{
    private Realm matterRealm;

    /**
     * 重写抽象父类的初始化数据库的方法
     */
    @Override
    public void initRealm() {
        if(matterRealm==null)
        {
            RealmConfiguration configuration=new RealmConfiguration.Builder().name("matter.realm").build();
            matterRealm=Realm.getInstance(configuration);
        }
    }

    /**
     * 得到数据库中Matter元素
     * 并将它们的所有title打包成一个集合返回
     * @return 所有title组成的集合，作为ListView的数据源
     */
     public List<String> getMatterList()
     {
         if(matterRealm==null)
             return null;
         RealmResults<Matter> realmResults=matterRealm.where(Matter.class).findAll();
         List<String> list=new ArrayList<>();
         for(int i=0;i<realmResults.size();i++)
         {
             list.add(realmResults.get(i).getTitle());
         }
         return list;
     }

    /**
     * 得到特定位置的Matter元素
     * 当MatterFragment弹出MatterDialog时用于找到后者显示的信息
     * @param position 要显示的数据的position
     * @return
     */
    public Matter getMatterAt(int position)
    {
        if (matterRealm==null)
            return null;
        return matterRealm.where(Matter.class).findAll().get(position);
    }

    /**
     * 在MatterRealm数据库中添加一个新的数据
     * 在AddMatterDialog中被调用
     * @param title 新元素的title
     * @param content 新元素的content
     */
    public void addMatter(final String title, final String content)
    {
        if(matterRealm!=null)
        {
            matterRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Matter matter=realm.createObject(Matter.class);
                    matter.setTitle(title);
                    matter.setMatter(content);
                }
            });
        }
    }

    /**
     * 根据数组记录的元素是位置删除元素
     * 在执行批量删除的时候被调用
     * @param l 记录了想要删除的元素的位置
     */
    public void deleteSeletedMatterDatas(final List<Integer> l) {
        if (matterRealm != null) {
            matterRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Matter> matters = realm.where(Matter.class).findAll();
                    for (int i = 0; i < l.size(); i++) {
                        matters.deleteFromRealm(l.get(i) - i);
                    }
                }
            });
        }
    }

}
