package com.example.dllo.blevel.sql;

import com.example.dllo.blevel.entity.BearCarEntity;
import com.example.dllo.blevel.application.BearApplication;

import java.util.List;

/**
 * Created by dllo on 17/4/17.
 */

public class DataBaseTool implements TableCarOperation {

    // 声明一个数据库帮助类
    private CarSQLiteHelper mHelper;
    // 创建一个接口对象
    private TableCarOperation mCarOperation;

    // 当前选用的方式为: 静态内部类
    private static class SingletonHolder {
        private static final DataBaseTool INSTANCE = new DataBaseTool();
    }

    public static DataBaseTool getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private DataBaseTool() {
        // 创建对象的时候需要传入一个Context值
        mHelper = new CarSQLiteHelper(BearApplication.getContext());
        mCarOperation = new CarOperationSQL(mHelper);
    }

    @Override
    public void addCar(BearCarEntity car) {
        mCarOperation.addCar(car);
    }

    @Override
    public void removeCar(int id) {
        mCarOperation.removeCar(id);
    }

    @Override
    public void updateCar(BearCarEntity car) {
        mCarOperation.updateCar(car);
    }

    @Override
    public List<BearCarEntity> queryCars() {
        return mCarOperation.queryCars();
    }

    @Override
    public BearCarEntity querySelectedCar() {
        return mCarOperation.querySelectedCar();
    }

    @Override
    public void changeSelectedCar(int id) {
        mCarOperation.changeSelectedCar(id);
    }

    @Override
    public void changeSelectedCar(BearCarEntity newSelectedCar) {
        mCarOperation.changeSelectedCar(newSelectedCar);
    }
}
