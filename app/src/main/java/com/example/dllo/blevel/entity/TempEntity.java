package com.example.dllo.blevel.entity;

/**
 *
 *
 * 该实体类的目的是将两个集合合并成一个集合,方便我们对数据的操作
 */

public class TempEntity {
    private int index;
    private String name;
    public int getIndex() {
        return index;
    }
    public TempEntity setIndex(int index) {
        this.index = index;
        return this;
    }
    public String getName() {
        return name;
    }
    public TempEntity setName(String name) {
        this.name = name;
        return this;
    }
}
