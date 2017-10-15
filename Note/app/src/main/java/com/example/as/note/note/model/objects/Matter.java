package com.example.as.note.note.model.objects;

import io.realm.RealmObject;

/**
 * matterRealm数据库中存储的数据类型
 * Created by as on 2017/10/6.
 */

public class Matter extends RealmObject{
    private String title;
    private String matter;

    public void setTitle(String title)
    {
        this.title=title;
    }

    public void setMatter(String matter)
    {
        this.matter=matter;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getMatter()
    {
        return this.matter;
    }
}
