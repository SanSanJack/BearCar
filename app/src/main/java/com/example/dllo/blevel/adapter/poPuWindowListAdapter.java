package com.example.dllo.blevel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.blevel.R;
import com.example.dllo.blevel.entity.BearCarEntity;
import com.example.dllo.blevel.view.DataBaseTool;

import java.util.List;

/**
 * Created by WYL on 2017/4/24.
 */

public class poPuWindowListAdapter extends BaseAdapter {

    private Context context;
    public List<BearCarEntity> carEntities;

    public poPuWindowListAdapter(Context context) {
        this.context = context;
    }

    public void setCarEntities(List<BearCarEntity> carEntities) {
        this.carEntities = carEntities;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return carEntities.size() > 0 ? carEntities.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return carEntities.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return carEntities.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_popuwindowlistadapter, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(carEntities.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        private ViewHolder(View itemView) {
            textView = (TextView) itemView.findViewById(R.id.tv_popuwindow);

        }
    }
}
