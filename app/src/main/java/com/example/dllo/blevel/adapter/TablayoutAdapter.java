package com.example.dllo.blevel.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.blevel.R;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by dllo on 17/4/18.
 */

public class TablayoutAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private Context context;
    private String[] titles =  {"油耗","排名","油费","费用"};
    //TODO:图片集合
    //构造方法传值.如果不写的话系统会默认一个构造方法
    public TablayoutAdapter(FragmentManager fm, ArrayList<Fragment> fragments, Context context) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
    }

    public TablayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size()>0 ?fragments.size(): 0 ;
    }
    public View getTabview(int  index){

        View tabView =  LayoutInflater.from(context).inflate(R.layout.item_tab,null);
        TextView textView  = (TextView) tabView.findViewById(R.id.tab_tv);
        textView.setText(titles[index]);
        ImageView imageview  = (ImageView) tabView.findViewById(R.id.tab_iv);
//        imageview.setImageResource();
        //TODO:后续要写图片
        return tabView;
    }

}
