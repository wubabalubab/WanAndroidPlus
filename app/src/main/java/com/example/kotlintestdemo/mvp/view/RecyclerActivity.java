package com.example.kotlintestdemo.mvp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.adapter.swpeAdapter;
import com.example.kotlintestdemo.base.BaseActivity;
import com.example.kotlintestdemo.mvp.view.fragments.BlankFragment;

import java.util.ArrayList;

public class RecyclerActivity extends BaseActivity {

    RecyclerView rvMain;

   swpeAdapter swpeAdapter;
   ArrayList<String> mList;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    BlankFragment blankFragment;

    @Override
    public int layoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    public void initView() {
        mList=new ArrayList<>();
     mList.add("sssss");
     mList.add("sssss2");
     mList.add("sssss3");
     mList.add("sssss4");
     swpeAdapter = new swpeAdapter(this,mList);
     rvMain =findViewById(R.id.rv_recycleractivity);
     rvMain.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
     rvMain.setAdapter(swpeAdapter);

     fragmentManager=getSupportFragmentManager();
     fragmentTransaction=fragmentManager.beginTransaction();
     fragmentTransaction.add(R.id.framelayout,BlankFragment.newInstance("text",mList));
     fragmentTransaction.commit();
    }
}
