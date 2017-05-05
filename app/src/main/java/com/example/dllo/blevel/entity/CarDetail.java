package com.example.dllo.blevel.entity;

/**
 * Created by WYL on 2017/5/1.
 */

public class CarDetail {

    private int status;
    private int id;
    private int chexi;
    private String cheXiName;
    private int pinpai;
    private String pinPaiName;
    private String name;
    private double displacement;
    private String engine;
    private String gearbox;
    private float tank;
    private double refConsumption;
    private float is_auto;
    private float is_turbo;
    private String car_type;
    private float weight;
    private String engineType;
    private String engineInflow;
    private float compressionRate;
    private String fuelType;
    private String gasType;
    private String envGrade;
    private double factoryPrice;
    private float maintIntervalDistance;
    private float csptRangeMin;
    private double csptRangeMax;

    public String getCar_type() {
        return car_type;
    }

    public CarDetail setCar_type(String car_type) {
        this.car_type = car_type;
        return this;
    }

    public int getChexi() {
        return chexi;
    }

    public CarDetail setChexi(int chexi) {
        this.chexi = chexi;
        return this;
    }

    public String getCheXiName() {
        return cheXiName;
    }

    public CarDetail setCheXiName(String cheXiName) {
        this.cheXiName = cheXiName;
        return this;
    }

    public float getCompressionRate() {
        return compressionRate;
    }

    public CarDetail setCompressionRate(float compressionRate) {
        this.compressionRate = compressionRate;
        return this;
    }

    public double getCsptRangeMax() {
        return csptRangeMax;
    }

    public CarDetail setCsptRangeMax(double csptRangeMax) {
        this.csptRangeMax = csptRangeMax;
        return this;
    }

    public float getCsptRangeMin() {
        return csptRangeMin;
    }

    public CarDetail setCsptRangeMin(float csptRangeMin) {
        this.csptRangeMin = csptRangeMin;
        return this;
    }

    public double getDisplacement() {
        return displacement;
    }

    public CarDetail setDisplacement(double displacement) {
        this.displacement = displacement;
        return this;
    }

    public String getEngine() {
        return engine;
    }

    public CarDetail setEngine(String engine) {
        this.engine = engine;
        return this;
    }

    public String getEngineInflow() {
        return engineInflow;
    }

    public CarDetail setEngineInflow(String engineInflow) {
        this.engineInflow = engineInflow;
        return this;
    }

    public String getEngineType() {
        return engineType;
    }

    public CarDetail setEngineType(String engineType) {
        this.engineType = engineType;
        return this;
    }

    public String getEnvGrade() {
        return envGrade;
    }

    public CarDetail setEnvGrade(String envGrade) {
        this.envGrade = envGrade;
        return this;
    }

    public double getFactoryPrice() {
        return factoryPrice;
    }

    public CarDetail setFactoryPrice(double factoryPrice) {
        this.factoryPrice = factoryPrice;
        return this;
    }

    public String getFuelType() {
        return fuelType;
    }

    public CarDetail setFuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public String getGasType() {
        return gasType;
    }

    public CarDetail setGasType(String gasType) {
        this.gasType = gasType;
        return this;
    }

    public String getGearbox() {
        return gearbox;
    }

    public CarDetail setGearbox(String gearbox) {
        this.gearbox = gearbox;
        return this;
    }

    public int getId() {
        return id;
    }

    public CarDetail setId(int id) {
        this.id = id;
        return this;
    }

    public float getIs_auto() {
        return is_auto;
    }

    public CarDetail setIs_auto(float is_auto) {
        this.is_auto = is_auto;
        return this;
    }

    public float getIs_turbo() {
        return is_turbo;
    }

    public CarDetail setIs_turbo(float is_turbo) {
        this.is_turbo = is_turbo;
        return this;
    }

    public float getMaintIntervalDistance() {
        return maintIntervalDistance;
    }

    public CarDetail setMaintIntervalDistance(float maintIntervalDistance) {
        this.maintIntervalDistance = maintIntervalDistance;
        return this;
    }

    public String getName() {
        return name;
    }

    public CarDetail setName(String name) {
        this.name = name;
        return this;
    }

    public int getPinpai() {
        return pinpai;
    }

    public CarDetail setPinpai(int pinpai) {
        this.pinpai = pinpai;
        return this;
    }

    public String getPinPaiName() {
        return pinPaiName;
    }

    public CarDetail setPinPaiName(String pinPaiName) {
        this.pinPaiName = pinPaiName;
        return this;
    }

    public double getRefConsumption() {
        return refConsumption;
    }

    public CarDetail setRefConsumption(double refConsumption) {
        this.refConsumption = refConsumption;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public CarDetail setStatus(int status) {
        this.status = status;
        return this;
    }

    public float getTank() {
        return tank;
    }

    public CarDetail setTank(float tank) {
        this.tank = tank;
        return this;
    }

    public float getWeight() {
        return weight;
    }

    public CarDetail setWeight(float weight) {
        this.weight = weight;
        return this;
    }
}
