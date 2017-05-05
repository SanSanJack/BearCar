package com.example.dllo.blevel.view;

import com.example.dllo.blevel.entity.BearCarEntity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by dllo on 17/4/17.
 */

public class Rxjava {

    public void addcar(final BearCarEntity car) {
        Observable.just(car).map(new Function<BearCarEntity, Object>() {
            @Override
            public Object apply(@NonNull BearCarEntity bearCarEntity) throws Exception {
                DataBaseTool.getInstance().addCar(bearCarEntity);
                return null;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();

    }

    //车俩删除
    public void removeCar(final int id) {
        Observable.just(id).map(new Function<Integer, Object>() {
            @Override
            public Object apply(@NonNull Integer integer) throws Exception {
                DataBaseTool.getInstance().removeCar(id);
                return null;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

        //更改
        public void updateCar ( final BearCarEntity car){
            Observable.just(car).map(new Function<BearCarEntity, Object>() {
                @Override
                public Object apply(@NonNull BearCarEntity bearCarEntity) throws Exception {
                    DataBaseTool.getInstance().updateCar(bearCarEntity);
                    return null;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();

        }
        //车辆查询
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R
//        .layout.simple_list_item_1,);

    public void queeryCar(final BearCarEntity car) {
        Observable.just(car).map(new Function<BearCarEntity, Object>() {
            @Override
            public Object apply(@NonNull BearCarEntity bearCarEntity) throws Exception {
                DataBaseTool.getInstance().queryCars();
                return null;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();

    }

    }
