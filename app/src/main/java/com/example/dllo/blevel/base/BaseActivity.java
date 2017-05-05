package com.example.dllo.blevel.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 17/4/18.
 */

public abstract  class BaseActivity extends AppCompatActivity  implements View.OnClickListener{


    @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        setListener();
        initData();

    }

 //建一个抽象方法,由子类去实现
    public abstract int setLayout();
    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 监听事件
     */
    protected abstract void setListener();


}
