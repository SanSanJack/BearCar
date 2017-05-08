package com.example.dllo.blevel.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.blevel.R;
import com.example.dllo.blevel.adapter.DialogListViewAdapter;
import com.example.dllo.blevel.adapter.TablayoutAdapter;
import com.example.dllo.blevel.adapter.poPuWindowListAdapter;
import com.example.dllo.blevel.base.BaseActivity;
import com.example.dllo.blevel.entity.BearCarEntity;
import com.example.dllo.blevel.entity.CarInformationEntity;
import com.example.dllo.blevel.fragment.CoastFragment;
import com.example.dllo.blevel.fragment.ConsumptionFragment;
import com.example.dllo.blevel.fragment.FuelFragment;
import com.example.dllo.blevel.fragment.RankingFragment;
import com.example.dllo.blevel.view.DataBaseTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {


    private TablayoutAdapter adapter;
    private ArrayList<Fragment> fragments;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private HashMap<Double, Double> map;
    private ImageView sign, iconOne,share;
    private DrawerLayout drawerLayout;
    private com.example.dllo.blevel.view.leftPullView leftPullView;
    private boolean IS_CLICk = false;
    private LinearLayout right;
    private LinearLayout left;
    private int position;
    private Context context;
    private TextView rightCircler, crossLiptTv;
    private PopupWindow popuwindow;
    private poPuWindowListAdapter poPuWindowListAdapter;
    private ListView listView, addListView;
    private DialogListViewAdapter dialogListViewAdapter;
    private ImageView addIV;                                //


    @Override
    public int setLayout() {

        return R.layout.activity_main;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initView() {
        iconOne = (ImageView) findViewById(R.id.icon_one);
        sign = (ImageView) findViewById(R.id.jackson);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        share = (ImageView) findViewById(R.id.share_iv);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawlayout);
        rightCircler = (TextView) findViewById(R.id.spinner);
        left = (LinearLayout) findViewById(R.id.left);
        right = (LinearLayout) findViewById(R.id.right);

        poPuWindowListAdapter = new poPuWindowListAdapter(this);

        dialogListViewAdapter = new DialogListViewAdapter(MainActivity.this);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new FuelFragment());
        fragments.add(new RankingFragment());
        fragments.add(new CoastFragment());
        fragments.add(new ConsumptionFragment());
        adapter = new TablayoutAdapter(getSupportFragmentManager(), fragments, this);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabTextColors( "#00FFFF");
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#00FFFF"));
        //自定义的Tablayout中的标题
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabview(i));

        }
        BearCarEntity bearCarEntity = DataBaseTool.getInstance().querySelectedCar();
//        if (bearCarEntity !=null) {
//            rightCircler.setText(bearCarEntity.getName());
//        }else {
//            rightCircler.setText("我的小车");
//        }
        if (DataBaseTool.getInstance().queryCars().size() == 0) {
            BearCarEntity bearCarEntity1 = new BearCarEntity(0);
            bearCarEntity1.setName("我的小车");
            DataBaseTool.getInstance().addCar(bearCarEntity1);
            DataBaseTool.getInstance().changeSelectedCar(bearCarEntity1);
            rightCircler.setText(DataBaseTool.getInstance().querySelectedCar().getName());

        }


    }

    @Override
    protected void setListener() {
        sign.setOnClickListener(this);
        rightCircler.setOnClickListener(this);
        iconOne.setOnClickListener(this);
        share.setOnClickListener(this);

    }

    List<BearCarEntity> carEntities = new ArrayList<>();

    public List<BearCarEntity> setCarEntities() {
        BearCarEntity bearCarEntity = new BearCarEntity(1);
        bearCarEntity.setName("123");
        bearCarEntity.setModel(1);
        bearCarEntity.setSelected(1);
        bearCarEntity.setUuid(1);
        Log.d("MainActivity", "carEntities:" + carEntities);
        carEntities.add(bearCarEntity);
        return carEntities;
    }


    //加载popuwindow的方法
    private void showPoPuWindow() {
        setCarEntities();
        //视图加载
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_popuwindow, null);
        //设置长宽度
        listView = (ListView) contentView.findViewById(R.id.list_popuwindow);
        popuwindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //加载视图
        popuwindow.setContentView(contentView);
        popuwindow.showAsDropDown(rightCircler, 0, -20, Gravity.RIGHT);
        carEntities = DataBaseTool.getInstance().queryCars();
        //让车俩管理在最后一位
        if (carEntities.size() >= 0) {
            BearCarEntity bearCarEntity = new BearCarEntity(50);
            bearCarEntity.setName("车辆管理");
            carEntities.add(bearCarEntity);
            poPuWindowListAdapter.setCarEntities(carEntities);

        }
        Log.d("xxx", "carEntities.size():" + carEntities.size());
        Log.d("MainActivity", "poPuWindowListAdapter:" + poPuWindowListAdapter);
        Log.d("MainActivity", "listView:" + listView);
        listView.setAdapter(poPuWindowListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (id == 50) {
                    dialog();
                    popuwindow.dismiss();
                } else {
                    DataBaseTool.getInstance().changeSelectedCar((int) id);
                    BearCarEntity bearCarEntity = DataBaseTool.getInstance().querySelectedCar();
                    if (bearCarEntity != null) {
                        rightCircler.setText(bearCarEntity.getName());
                        popuwindow.dismiss();
                    }
                }

            }
        });
    }

    //dialog的加载方法
    private void dialog() {
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();//加载的一个布局
        //加载dialog布局
        View customDialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_firstdialog_add, null);
        addListView = (ListView) customDialogView.findViewById(R.id.firstdialog_listview);
        addIV = (ImageView) customDialogView.findViewById(R.id.imageView);
        Button button = (Button) customDialogView.findViewById(R.id.close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                popuwindow.dismiss();
            }
        });
        dialog.setView(customDialogView);
        dialogListViewAdapter.setOnDelClickListener(new DialogListViewAdapter.OnDelClickListener() {
            @Override
            public void onDelClick(BearCarEntity car) {
                customDialog(car);
            }
        });
        dialogListViewAdapter.setDialogListViewAdapter(carEntities);
        addListView.setAdapter(dialogListViewAdapter);
        addListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               Intent intent = new Intent(MainActivity.this,)

            }
        });
        dialog.show();

        addIV.setOnClickListener(this);


    }

    private void customDialog(final BearCarEntity car) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //点击事件
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_NEGATIVE:
                        int id = car.getId();
                        String name = car.getName();
                        DataBaseTool.getInstance().removeCar(id);
                        Toast.makeText(MainActivity.this, "删除:" + String.valueOf(name), Toast.LENGTH_SHORT).show();
                        break;

                    case DialogInterface.BUTTON_NEUTRAL:
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        };

        builder.setIcon(R.mipmap.notice_board_warn)
                .setTitle("删除车辆")
//                .setMessage("删除将不可恢复。\n您确定删除" + myCar.getName() + "，以及它的所有油耗数据吗？")
                .setNegativeButton("确定", onClickListener)
                .setNeutralButton("取消", onClickListener)
                .create()
                .show();

    }

    //侧拉菜单的加载方法
    private void pull() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.jackson:
                pull();
                break;
            case R.id.spinner:
                showPoPuWindow();
                break;
            case R.id.icon_one:
                Intent intent1 = new Intent(MainActivity.this, IconOneActivity.class);
                startActivity(intent1);
                break;
            case R.id.imageView:
                Intent intentCar = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(intentCar);
                break;
            case R.id.share_iv:
        }
    }

//
//    public void request(){
//        Retrofit retrofit =new Retrofit.Builder().baseUrl("http://www.xiaoxiongyouhao.com/")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//
//        BearService service = retrofit.create(BearService.class);
//        Call<CarInformationEntity> brands = service.getBrands();
//        brands.enqueue(new Callback<CarInformationEntity>() {
//            @Override
//            public void onResponse(Call<CarInformationEntity> call, Response<CarInformationEntity> response) {
//             CarInformationEntity body = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<CarInformationEntity> call, Throwable t) {
//
//            }
//        });
//        //根据不同的index获取车系
//        Call<CarInformationEntity>carSeries = service.getSeries();
//    }


}