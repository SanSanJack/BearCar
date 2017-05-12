package com.example.dllo.blevel.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.blevel.R;
import com.example.dllo.blevel.adapter.ChoiceAdapter;
import com.example.dllo.blevel.base.BaseActivity;
import com.example.dllo.blevel.entity.BearCarEntity;
import com.example.dllo.blevel.entity.CarDetail;
import com.example.dllo.blevel.entity.CarInformationEntity;
import com.example.dllo.blevel.entity.TempEntity;
import com.example.dllo.blevel.tool.HttpManager;
import com.example.dllo.blevel.sql.DataBaseTool;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dllo on 17/4/26.
 */

public class UpdateActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    //有关Retrofit的三个网络请求spinner
    private Spinner brandSpinner;
    private Spinner seriesSpinner;
    private Spinner modelSpinner;


    //品牌车系，车型等的适配器
    private ChoiceAdapter brandAdapter;
    private ChoiceAdapter seriesAdapter;
    private ChoiceAdapter modelAdapter;
    private ImageView isDelte;
    private ImageView isSave;
    private EditText getCarName;
    private TextView leftTv;
    private TextView rightTv;
    private int brandId;
    private int seriesId;
    private int cheXiId;

    @Override
    public int setLayout() {
        return R.layout.activity_update;
    }
    @Override
    public void initView() {
        isSave = (ImageView) findViewById(R.id.activityUpdate_ivSave);
        isDelte = (ImageView) findViewById(R.id.activityUpdate_ivDelete);
        getCarName = (EditText) findViewById(R.id.activityUpdate_etNickName);
         brandSpinner =  (Spinner) findViewById(R.id.activityUpdate_spinnerBrand);
      seriesSpinner = (Spinner) findViewById(R.id.activityUpdate_spinnerName);
     modelSpinner = (Spinner) findViewById(R.id.activityUpdate_spinnerStyle);
        leftTv = (TextView) findViewById(R.id.activityUpdate_btnLeft);
        rightTv = (TextView) findViewById(R.id.activityUpdate_btnRight);
    }
    @Override
    protected void initData() {
        brandAdapter = new ChoiceAdapter(this);
        seriesAdapter = new ChoiceAdapter(this);
        modelAdapter = new ChoiceAdapter(this);
        brandSpinner.setAdapter(brandAdapter);
        seriesSpinner.setAdapter(seriesAdapter);
        modelSpinner.setAdapter(modelAdapter);
        // 控制是否显示删除图标的判断
        if (TextUtils.isEmpty(getCarName.getText().toString().trim())) {
            isDelte.setVisibility(View.INVISIBLE);
        } else {
            isDelte.setVisibility(View.VISIBLE);
        }

        // 检查资料是否填写完整的判断
        if (TextUtils.isEmpty(getCarName.getText().toString().trim()) ||
                brandSpinner.getSelectedItemId() == 0 ||
                seriesSpinner.getSelectedItemId() == 0 ||
                modelSpinner.getSelectedItemId() == 0) {
            Toast.makeText(this, "车型资料不完整", Toast.LENGTH_SHORT).show();
        }
        isSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将车存到数据库
                BearCarEntity car = new BearCarEntity(0);
                car.setName(getCarName.getText().toString());
                car.setModel(cheXiId);
                car.getSelected(1);
                DataBaseTool.getInstance().addCar(car);
                setResult(202);
                finish();
            }
        });
        //所有车牌的网络请求
        // 将数据转换为Obserable类型
        HttpManager.getInstance()
                .getService()
                .getBrand()
                // 首先将CarInformation转换成Observable类型
                .flatMap(new Function<CarInformationEntity, ObservableSource<TempEntity>>() {
                    @Override
                    public ObservableSource<TempEntity> apply(@NonNull CarInformationEntity carInformation) throws Exception {
                        // 先将两个集合取出来
                        List<Integer> indexs = carInformation.getIdxes();
                        List<String> names = carInformation.getNames();
                        // 在return的时候对这两个集合进行压缩
                        return Observable.zip(Observable.fromIterable(indexs), Observable.fromIterable(names), new BiFunction<Integer, String, TempEntity>() {
                            @Override
                            public TempEntity apply(@NonNull Integer integer, @NonNull String s) throws Exception {
                                // 创建实体类对象,并对其中的元素进行赋值
                                TempEntity temp = new TempEntity();
                                temp.setIndex(integer);
                                temp.setName(s);
                                return temp;
                            }
                        });
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TempEntity>>() {
                    @Override
                    public void accept(@NonNull final List<TempEntity> tempForQueries) throws Exception {
                        // 将请求下来的集合添加到适配器中
                       brandAdapter.addAll(tempForQueries);
                     brandSpinner.setOnItemSelectedListener(UpdateActivity.this);
                    }
                });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.activityUpdate_spinnerBrand:
                brandId = (int) id;
           //     brandAdapter.clear();
                if (brandId == 0) {
                    brandAdapter.setWhite(true);
                    seriesAdapter.setWhite(false);
                    modelSpinner.setEnabled(false);
                    return;
                } else {
                    modelSpinner.setEnabled(true);
                }
                // 开始汽车系别的网络请求
                getCarSeries();
                break;
            case R.id.activityUpdate_spinnerName:
                seriesId = (int) id;
                if (seriesId==0){
                    seriesAdapter.setWhite(true);
                   brandAdapter.setWhite(false);
                    modelAdapter.setWhite(false);
                    return;
                }else{
                    modelSpinner.setEnabled(true);
                }
                getCarType();
                break;
            case R.id.activityUpdate_spinnerStyle:
                cheXiId = (int) id;
                if (seriesId==0){
                    leftTv.setVisibility(View.INVISIBLE);
                    rightTv.setVisibility(View.INVISIBLE);
                    return;
                }else {
                    leftTv.setVisibility(View.VISIBLE);
                    rightTv.setVisibility(View.VISIBLE);
                    getCarDetail();
                    break;
                }
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //当没选中的时候应该做的事情
    }
    private void getCarType() {
        HttpManager.getInstance().getService().getCarType(brandId, seriesId)
                .flatMap(new Function<CarInformationEntity, ObservableSource<TempEntity>>() {
                    @Override
                    public ObservableSource<TempEntity> apply(@NonNull CarInformationEntity carInformation) throws Exception {
                        // 先将两个集合取出来
                        List<Integer> indexs = carInformation.getIdxes();
                        List<String> names = carInformation.getNames();
                        // 在return的时候对这两个集合进行压缩
                        return Observable.zip(Observable.fromIterable(indexs), Observable.fromIterable(names), new BiFunction<Integer, String, TempEntity>() {
                            @Override
                            public TempEntity apply(@NonNull Integer integer, @NonNull String s) throws Exception {
                                // 创建实体类对象,并对其中的元素进行赋值
                                TempEntity temp = new TempEntity();
                                temp.setIndex(integer);
                                temp.setName(s);
                                return temp;
                            }
                        });
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TempEntity>>() {
                    @Override
                    public void accept(@NonNull List<TempEntity> tempForQueries) throws Exception {
                        modelAdapter.addAll(tempForQueries);
                        modelAdapter.setWhite(true);
                        modelSpinner.setOnItemSelectedListener(UpdateActivity.this);
                    }
                });
    }

    private void getCarSeries() {
        HttpManager.getInstance().getService().getCarSeries(brandId).flatMap(new Function<CarInformationEntity, ObservableSource<TempEntity>>() {
            @Override
            public ObservableSource<TempEntity> apply(@NonNull CarInformationEntity carInformationEntity) throws Exception {
                List<Integer> indexs = carInformationEntity.getIdxes();
                List<String> names = carInformationEntity.getNames();
                // 在return的时候对这两个集合进行压缩
                return Observable.zip(Observable.fromIterable(indexs), Observable.fromIterable(names), new BiFunction<Integer, String, TempEntity>() {
                    @Override
                    public TempEntity apply(@NonNull Integer integer, @NonNull String s) throws Exception {
                        // 创建实体类对象,并对其中的元素进行赋值
                        TempEntity temp = new TempEntity();
                        temp.setIndex(integer);
                        temp.setName(s);
                        return temp;
                    }
                });
            }
        })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TempEntity>>() {
                    @Override
                    public void accept(@NonNull List<TempEntity> tempForQueries) throws Exception {
                        seriesAdapter.addAll(tempForQueries);
                        seriesSpinner.setOnItemSelectedListener(UpdateActivity.this);
                    }
                });
    }
    private void getCarDetail() {
        HttpManager.getInstance().getService().getCarDetail(cheXiId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarDetail>() {
                    @Override
                    public void accept(@NonNull CarDetail carInformationEntity) throws Exception {
                        if (carInformationEntity !=null) {
                            leftTv.setText(carInformationEntity.getEngine());
                            Log.d("xxx", carInformationEntity.getEngine());
                            rightTv.setText(carInformationEntity.getGearbox());
                        }
                    }
                });

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }

}
