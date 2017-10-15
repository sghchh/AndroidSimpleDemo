package com.example.as.note.note.constant;

/**
 * Created by as on 2017/10/6.
 */

public class C {
    /*
    用于NoteFragment界面两个TextView的点击事件
    对应的常量作为标签，表示点击后加载哪一种Fragment
     */
    public static final int BORROW=1;
    public static final int MATTER=3;

    /*
    当AddMatterFragment界面得到想要添加的数据
    点击“添加”按钮时数据返回MatterFragment界面
    作为MatterFragment的onActivityResult方法中的requestcode
     */
    public static final int ADDMATTERDIALOG=4;

    public static final int ADDBORROWDATADIALOG=5;

    public static final int BORROWDIALOG=6;
}
