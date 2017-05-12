package com.example.dllo.blevel.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.blevel.R;
import com.example.dllo.blevel.entity.BearCarEntity;
import com.example.dllo.blevel.interfacep.ViewOnclick;

import java.util.List;

/**
 * Created by WYL on 2017/4/25.
 */

public class DialogListViewAdapter extends BaseAdapter implements ViewOnclick{
    //    private Context context;
    private List<BearCarEntity> carEntities;
    private LayoutInflater mInflater;
    private OnDelClickListener mOnDelClickListener;
    //内部接口
    public interface OnDelClickListener{
        void onDelClick(BearCarEntity car);
    }
    //构造方法
    public void setOnDelClickListener(OnDelClickListener onDelClickListener) {
        mOnDelClickListener = onDelClickListener;
    }


    public DialogListViewAdapter(Context context ) {
//        this.context = context;
        mInflater = LayoutInflater.from(context);
        notifyDataSetInvalidated();
    }

//便于删除
    public List<BearCarEntity> getData(){
        return  carEntities;
    }

    public void setDialogListViewAdapter(List<BearCarEntity> carEntities) {
        this.carEntities = carEntities;
        notifyDataSetChanged();
        

    }

    @Override

    public int getCount() {
        return carEntities == null ? 0:carEntities.size();
    }

    @Override
    public Object getItem(int position) {
        //为listview绑定一个实体类
        return carEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        //为listview的每一行绑定一个id，
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            Log.d("DialogListViewAdapter", "convertView:" + convertView);
            convertView = mInflater.inflate(R.layout.item_dialog_car, parent, false);
//            Log.d("DialogListViewAdapter", "context:" + context);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.iv.setTag(carEntities.get(position));
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnDelClickListener != null){
                    mOnDelClickListener.onDelClick((BearCarEntity) v.getTag());
                }
            }
        });
        holder.tv.setText(carEntities.get(position).getName());
//        holder.iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO 需要理解
//                mOnDelClickListener.onDelClick(v,position);
//            }
//        });
        return convertView;
    }
    @Override
    public void click(View view, int position) {
    }
    class ViewHolder {
        TextView tv;
        ImageView iv; //删除
        private ViewHolder(View itemView) {
            tv = (TextView) itemView.findViewById(R.id.tv_listview_add);
            iv = (ImageView) itemView.findViewById(R.id.delte);
        }
    }
}
