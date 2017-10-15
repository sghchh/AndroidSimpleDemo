package com.example.as.note.note.model.objects;

import io.realm.RealmObject;

/**
 * BorrowRealm数据库中存储的数据类型
 * Created by as on 2017/10/6.
 */

public class BorrowData extends RealmObject {

    private String name;
    private String bookName;
    private String date;

    public void setName(String name)
    {
        this.name=name;
    }

    public void setBookName(String bookName)
    {
        this.bookName=bookName;
    }

    public void setDate(String date)
    {
        this.date=date;
    }

    public String getName()
    {
        return this.name;
    }

    public String getBookName()
    {
        return this.bookName;
    }

    public String getDate()
    {
        return this.date;
    }
}
