package com.example.dllo.blevel.entity;

import java.util.List;

/**
 * Created by dllo on 17/4/28.
 */

public class CarInformationEntity {

    private int total;
    //品牌和车系是第二个，第三个接口里头的，后加上去的；
    private int pinpai;
    private int chexi;
    private List<Integer> idxes;
    private List<String> names;

    public int getChexi() {
        return chexi;
    }

    public CarInformationEntity setChexi(int chexi) {
        this.chexi = chexi;
        return this;
    }

    public int getPinpai() {
        return pinpai;
    }

    public CarInformationEntity setPinpai(int pinpai) {
        this.pinpai = pinpai;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Integer> getIdxes() {
        return idxes;
    }

    public void setIdxes(List<Integer> idxes) {
        this.idxes = idxes;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}

