package com.example.dllo.blevel.fragment;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.blevel.view.LinearSelfView;
import com.example.dllo.blevel.R;
import com.example.dllo.blevel.base.BaseFragment;
import com.example.dllo.blevel.entity.CrossLipEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 油费页面
 */
public class FuelFragment extends BaseFragment {
    private List<CrossLipEntity> crossLips;
    private TextView crossLiptTv;
    private ImageView lastTv, nextTv;
    private LinearSelfView selfView;
    private HashMap<Double, Double> map;
    private ArrayList<Double> horizontal;

    @Override
    public int setLayout() {
        return R.layout.fragment_fuel;
    }

    @Override
    public void initView(View view) {
        crossLiptTv = (TextView) view.findViewById(R.id.cross_tv);
        lastTv = (ImageView) view.findViewById(R.id.last_iv);
        nextTv = (ImageView) view.findViewById(R.id.next_iv);
        selfView = (LinearSelfView) view.findViewById(R.id.selfView);
        crossLips = new ArrayList<>();
        crossLips.add(new CrossLipEntity("油耗统计曲线"));
        crossLips.add(new CrossLipEntity("最近一年油耗统计曲线"));
        crossLips.add(new CrossLipEntity("最近半年油耗统计曲线"));
        crossLips.add(new CrossLipEntity("最近三个月油耗统计曲线"));
        crossLips.add(new CrossLipEntity("同城同车型油耗基准线"));
        //中间的titles；
        //默认为第一个title
        crossLiptTv.setText(crossLips.get(0).getTitles());
        map = new HashMap<>();
//        map.put(2.0, 4.0);
//        map.put(3.4, 4.8);
//        map.put(4.5, 5.7);
//        map.put(5.6, 6.8);
//        map.put(12.0, 4.8);
        Log.d("MainActivity", "朴孝敏:" + map);
//  public void setView(Context context , HashMap<Double, Double> map, int marginB,
//                       int marginLeft, int totalValue, int jvalue, String xStr, String yStr,
//                       boolean isShow) {
        /*
         第三个参数:距离底部的距离
         第四个参数:距离屏幕左侧的距离
         第五个参数:坐标总抽的总长
         第六个总长度:纵坐标刻度的长度
         */

        selfView.setView(getContext(), 10, 50, 15, 1, "月份", "油耗", true);
        Log.d("MainActivity", "白百合:" + selfView);
        Log.d("MainActivity", "宝蓝:" + map);
    }

    int count = 1;
    int sum = 1;

    @Override
    public void initData() {

        setTitles(0);
        ArrayList<Integer> names = new ArrayList();
        names.add(2015);
        names.add(2016);
        names.add(2017);
        HashMap<Double,Double> map = new HashMap<>();
        map.put(2.0, 4.0);
        map.put(3.4, 4.8);
        map.put(4.5, 5.7);
        selfView.setView(getContext(),10, 3, 15, 1, "月份", "油耗", true);
        selfView.setPointMap(map);
        selfView.setHorizontal(names);
        count = 1;
        sum = 4;

        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    setTitles(count);
                    ArrayList<Integer> names = new ArrayList();
                    names.add(2015);
                    names.add(2016);
                    names.add(2017);
                    HashMap<Double,Double> map = new HashMap<>();
                    map.put(2.0, 4.0);
                    map.put(3.4, 4.8);
                    map.put(4.5, 5.7);
                    selfView.setPointMap(map);
                    selfView.setHorizontal(names);
                    count = 1;
                    sum = 4;
                } else if (count == 1) {
                    setTitles(count);
                    int[] xLable = new int[]{5, 6, 7, 8, 9, 10, 11, 12, 2017, 2, 3, 4}; //横坐标刻度以及刻度文字
                    ArrayList<Integer> names = new ArrayList();
                    double x = 1;
                    double y = 2;
                    HashMap<Double,Double> map = new HashMap<>();
                    for (int i = 0; i <xLable.length; i++) {
                        names.add(xLable[i]);
                        map.put(x,y);
                        x++;
                        y++;
                    }
                    selfView.setPointMap(map);
                    selfView.setHorizontal(names);
//                    Log.d("FuelFragment", "map.size():" + map.size());

                    count = 2;
                    sum = 0;
                } else if (count == 2) {
                    setTitles(count);
                    int[] xLable = new int[]{11, 12,2017, 2, 3, 4};
                    ArrayList<Integer> names = new ArrayList();
                    double x = 1;
                    double y = 2;
                    HashMap<Double,Double> map = new HashMap<Double, Double>();
                    for (int i = 0; i <xLable.length; i++) {
                        names.add(xLable[i]);
                        map.put(x,y);
                        x++;
                        y++;
                    }
                    //先传入刻度在传入map值
                    selfView.setPointMap(map);
                    selfView.setHorizontal(names);
                    Log.d("FuelFragment", "names.size():" + names.size());
                    Log.d("FuelFragment", "map.size():" + map.size());
                    count = 3;
                    sum = 1;
                } else if (count == 3) {
                    setTitles(count);
                    int[] num = new int[]{2, 3, 4};
                    double x = 1;
                    double y = 2;
                    ArrayList<Integer> names = new ArrayList();
                    HashMap<Double,Double> map = new HashMap<Double, Double>();
                    for (int i = 0; i <num.length; i++) {
                        names.add(num[i]);
                        map.put(x,y);
                        x++;
                        y++;
                    }
                    count = 4;
                    sum = 2;
                    //先传入刻度在传入map值
                    selfView.setPointMap(map);
                    selfView.setHorizontal(names);
                } else if (count == 4) {
                    setTitles(count);
                    int[] num = new int[]{5, 6, 7, 8, 9, 10, 11, 12, 2017, 2, 3, 4};
                    double x = 1;
                    double y = 2;
                    HashMap<Double,Double> map = new HashMap<Double, Double>();
                    ArrayList<Integer> names = new ArrayList();
                    for (int i = 0; i <num.length; i++) {
                        names.add(num[i]);
                        map.put(x,y);
                        x++;
                        y++;
                    }
                    selfView.setPointMap(map);
                    selfView.setHorizontal(names);
                    Log.d("FuelFragment", "horizontal:" + names);
                    count = 0;
                    sum = 3;
                }
            }

        });
        lastTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sum == 0) {
                    setTitles(sum);
                    sum = 4;
                    count = 1;
                } else if (sum == 4) {
                    setTitles(sum);
                    sum = 3;
                    count = 0;
                } else if (sum == 3) {
                    setTitles(sum);
                    sum = 2;
                    count = 3;
                } else if (sum == 2) {
                    setTitles(sum);
                    sum = 1;
                    count = 4;
                } else if (sum == 1) {
                    setTitles(sum);
                    sum = 0;
                    count = 2;
                }
            }
        });

    }

    private void setTitles(int count) {
        crossLiptTv.setText(crossLips.get(count).getTitles());
    }
}
