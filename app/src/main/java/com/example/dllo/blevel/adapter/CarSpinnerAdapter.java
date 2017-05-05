package com.example.dllo.blevel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.blevel.entity.BearCarEntity;

import java.util.List;

/**
 * Created by dllo on 17/4/18.
 */

public class CarSpinnerAdapter extends BaseAdapter {

    private List<BearCarEntity>car;
    private Context context;
    public CarSpinnerAdapter(Context context ,List<BearCarEntity> car) {
        this.car = car;
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return car == null?0 : car.size();
    }

    @Override
    public Object getItem(int position) {
        return car.get(position);
    }

    @Override
    public long getItemId(int position) {
        return car.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView ==null){
            convertView= LayoutInflater.from(context).inflate(android.R
            .layout.activity_list_item,parent,false);
//            TextView textView =
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(android.R
        .layout.simple_list_item_1,parent,false);
        return super.getDropDownView(position, convertView, parent);

    }
}
