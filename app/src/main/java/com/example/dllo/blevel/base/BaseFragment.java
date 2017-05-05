package com.example.dllo.blevel.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 17/4/18.
 */

public abstract class BaseFragment extends Fragment {
    protected Context context;

    //抽取布局方法
    //由子类去实现
    public abstract int setLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
//        initData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //初始化控件
    public abstract void initView(View view);

    public abstract void initData();

    //省去context的传递
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
