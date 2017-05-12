package com.example.dllo.blevel.sql;

import com.example.dllo.blevel.entity.BearCarEntity;

import java.util.List;


/**
 */

public interface TableCarOperation {

    // 因为接口中的方法 默认为 public abstract


    void addCar(BearCarEntity car);


    void removeCar(int id);

    void updateCar(BearCarEntity car);

    List<BearCarEntity> queryCars();

    BearCarEntity querySelectedCar();

    // 更改选中车辆
    void changeSelectedCar(int id);

    // 更改选中车辆 方法二
    void changeSelectedCar(BearCarEntity newSelectedCar);

}
