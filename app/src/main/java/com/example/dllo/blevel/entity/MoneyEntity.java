package com.example.dllo.blevel.entity;

/**
 * Created by dllo on 17/4/17.
 */

public class MoneyEntity {
    private String time;
    private float money;

    @Override
    public String toString() {
        return "MoneyEntity{" +
                "time='" + time + '\'' +
                ", money=" + money +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}


