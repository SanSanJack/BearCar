package com.example.dllo.blevel.entity;

/**
 * Created by dllo on 17/4/17.
 */


public class BearCarEntity {

    private String name;
    // 该属性设置为常量,只有在初始化的时候对其进行一次赋值()俩种方法,一是通过构造方法传值.二是直接初始化赋值
    private final int _id;
    private int selected;
    private int model;
    private int uuid;

    @Override
    public String toString() {
        return "CarEntity{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", selected=" + selected +
                '}';
    }

    // 在创建实体类的时候就为该实体类的ID赋上初始值
    public BearCarEntity(int _id) {
        this._id = _id;
    }

    public int getId() {
        return _id;
    }

    public int getModel() {
        return model;
    }

    public BearCarEntity setModel(int model) {
        this.model = model;
        return this;
    }

    public String getName() {
        return name;
    }

    public BearCarEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getSelected(int i) {
        return selected;
    }

    public BearCarEntity setSelected(int selected) {
        this.selected = selected;
        return this;
    }

    public int getUuid() {
        return uuid;
    }

    public BearCarEntity setUuid(int uuid) {
        this.uuid = uuid;
        return this;
    }
}


