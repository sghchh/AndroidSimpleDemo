package com.example.as.note;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.as.note.note.constant.C;
import com.example.as.note.note.fragments.BorrowFragment;
import com.example.as.note.note.fragments.MatterFragment;
import com.example.as.note.note.fragments.NoteFragment;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements NoteFragment.SwitchFragmentListener{

    private FragmentManager manager=getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init()
    {
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.wifi_fragment,new NoteFragment());
        transaction.commit();

        Realm.init(this);
    }

    @Override
    public void onSwitchFragment(int which) {
        switch (which)
        {
            case C.MATTER:
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.wifi_fragment,new MatterFragment());
                transaction.commit();
                break;
            case C.BORROW:
                FragmentTransaction transaction1=manager.beginTransaction();
                transaction1.replace(R.id.wifi_fragment,new BorrowFragment());
                transaction1.commit();
                break;
        }
    }
}
