package com.example.dllo.blevel.entity;

/**
 * Created by dllo on 17/4/17.
 */


    public class RecordsEntity {
        private int id;
        private long date;
        private int odometer;
        private double price;
        private int type;
        private int gassup;
        private String remark;
        private int carld;
        private int forget;
        private int lightOn;
        private int stationld;

        public RecordsEntity(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public int getOdometer() {
            return odometer;
        }

        public void setOdometer(int odometer) {
            this.odometer = odometer;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getGassup() {
            return gassup;
        }

        public void setGassup(int gassup) {
            this.gassup = gassup;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getCarld() {
            return carld;
        }

        public void setCarld(int carld) {
            this.carld = carld;
        }

        public int getForget() {
            return forget;
        }

        public void setForget(int forget) {
            this.forget = forget;
        }

        public int getLightOn() {
            return lightOn;
        }

        public void setLightOn(int lightOn) {
            this.lightOn = lightOn;
        }

        public int getStationld() {
            return stationld;
        }

        public void setStationld(int stationld) {
            this.stationld = stationld;
        }
    }

