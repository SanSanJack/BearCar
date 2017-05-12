package com.example.dllo.blevel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.blevel.entity.TempEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYL on 2017/4/30.
 */

public class ChoiceAdapter extends BaseAdapter {
    private List<TempEntity> data;
    private LayoutInflater mInflater;

    private boolean isWhite = false;
    public ChoiceAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        data = new ArrayList<>();
    }
    public void addAll(List<TempEntity> data) {
        // 防止调用次数过多文件内容过大,所以要先进行一次清空
        this.data.clear();
        this.data.addAll(data);
        // 当数据发生变化时候通知适配器进行UI的刷新
        notifyDataSetChanged();

    }

//    public void clear() {
//        this.data.clear();
//        notifyDataSetChanged();
//    }
    @Override
    public int getCount() {
      return data.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        if (position==0){
            TempEntity tempEntity = new TempEntity();
            tempEntity.setName("未知");
            return tempEntity;
        }
        return data.get(position-1);
    }
    public ChoiceAdapter setWhite(boolean white) {
        isWhite = white;
        notifyDataSetChanged();
        return this;
    }
    @Override
    public long getItemId(int position) {
        if (position == 0) return 0;
        //当前位置的index；
        return data.get(position - 1).getIndex();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取Item要的元素
        if (convertView==null){
            convertView = mInflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        TextView textView = (TextView) convertView;
        TempEntity tempEntity = (TempEntity) getItem(position);
        if (isWhite) {
            textView.setTextColor(Color.WHITE);
        } else {
            textView.setTextColor(Color.BLACK);
        }
        textView.setText(tempEntity.getName());
        return convertView;
    }

    /**
     *   这个方法确定了spinner下拉方式；
     * @param position
     * @param convertView //
     * @param parent
     * @return
     */
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        TextView textView = (TextView) convertView;
        TempEntity temp = (TempEntity) getItem(position);
        textView.setText(temp.getName());

        return convertView;
    }
}

