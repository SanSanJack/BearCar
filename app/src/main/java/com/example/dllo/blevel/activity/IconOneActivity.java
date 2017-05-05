package com.example.dllo.blevel.activity;

import android.icu.text.SimpleDateFormat;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.blevel.R;
import com.example.dllo.blevel.base.BaseActivity;

import java.util.Calendar;
import java.util.Date;

/**
 * 坐下角第一个按钮的Activity
 * Created by dllo on 17/4/26.
 */

public class IconOneActivity extends BaseActivity {
    private TextView dateTv, timeTV;

    @Override
    public int setLayout() {
        return R.layout.item_add_record;
    }

    @Override
    public void initView() {
        dateTv = (TextView) findViewById(R.id.date_tv);
        timeTV = (TextView) findViewById(R.id.time_tv);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initData() {
        //显示日期和时间
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int mHour = c.get(Calendar.HOUR);
        int mMinuts = c.get(Calendar.MINUTE);
        String strTime = mHour + "." + mMinuts;
        String str = year + "." + month + "." + day;
        dateTv.setText(str);
        timeTV.setText(strTime);

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
