package com.example.dllo.blevel.entity;

/**
 * If the code is not wrong its author is JokerCats.
 */

public class CarDetailEntity {
    //第四个接口的内容，和前三个有区别
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

    public CarDetailEntity setCar_type(String car_type) {
        this.car_type = car_type;
        return this;
    }

    public int getChexi() {
        return chexi;
    }

    public CarDetailEntity setChexi(int chexi) {
        this.chexi = chexi;
        return this;
    }

    public String getCheXiName() {
        return cheXiName;
    }

    public CarDetailEntity setCheXiName(String cheXiName) {
        this.cheXiName = cheXiName;
        return this;
    }

    public float getCompressionRate() {
        return compressionRate;
    }

    public CarDetailEntity setCompressionRate(float compressionRate) {
        this.compressionRate = compressionRate;
        return this;
    }

    public double getCsptRangeMax() {
        return csptRangeMax;
    }

    public CarDetailEntity setCsptRangeMax(double csptRangeMax) {
        this.csptRangeMax = csptRangeMax;
        return this;
    }

    public float getCsptRangeMin() {
        return csptRangeMin;
    }

    public CarDetailEntity setCsptRangeMin(float csptRangeMin) {
        this.csptRangeMin = csptRangeMin;
        return this;
    }

    public double getDisplacement() {
        return displacement;
    }

    public CarDetailEntity setDisplacement(double displacement) {
        this.displacement = displacement;
        return this;
    }

    public String getEngine() {
        return engine;
    }

    public CarDetailEntity setEngine(String engine) {
        this.engine = engine;
        return this;
    }

    public String getEngineInflow() {
        return engineInflow;
    }

    public CarDetailEntity setEngineInflow(String engineInflow) {
        this.engineInflow = engineInflow;
        return this;
    }

    public String getEngineType() {
        return engineType;
    }

    public CarDetailEntity setEngineType(String engineType) {
        this.engineType = engineType;
        return this;
    }

    public String getEnvGrade() {
        return envGrade;
    }

    public CarDetailEntity setEnvGrade(String envGrade) {
        this.envGrade = envGrade;
        return this;
    }

    public double getFactoryPrice() {
        return factoryPrice;
    }

    public CarDetailEntity setFactoryPrice(double factoryPrice) {
        this.factoryPrice = factoryPrice;
        return this;
    }

    public String getFuelType() {
        return fuelType;
    }

    public CarDetailEntity setFuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public String getGasType() {
        return gasType;
    }

    public CarDetailEntity setGasType(String gasType) {
        this.gasType = gasType;
        return this;
    }

    public String getGearbox() {
        return gearbox;
    }

    public CarDetailEntity setGearbox(String gearbox) {
        this.gearbox = gearbox;
        return this;
    }

    public int getId() {
        return id;
    }

    public CarDetailEntity setId(int id) {
        this.id = id;
        return this;
    }

    public float getIs_auto() {
        return is_auto;
    }

    public CarDetailEntity setIs_auto(float is_auto) {
        this.is_auto = is_auto;
        return this;
    }

    public float getIs_turbo() {
        return is_turbo;
    }

    public CarDetailEntity setIs_turbo(float is_turbo) {
        this.is_turbo = is_turbo;
        return this;
    }

    public float getMaintIntervalDistance() {
        return maintIntervalDistance;
    }

    public CarDetailEntity setMaintIntervalDistance(float maintIntervalDistance) {
        this.maintIntervalDistance = maintIntervalDistance;
        return this;
    }

    public String getName() {
        return name;
    }

    public CarDetailEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getPinpai() {
        return pinpai;
    }

    public CarDetailEntity setPinpai(int pinpai) {
        this.pinpai = pinpai;
        return this;
    }

    public String getPinPaiName() {
        return pinPaiName;
    }

    public CarDetailEntity setPinPaiName(String pinPaiName) {
        this.pinPaiName = pinPaiName;
        return this;
    }

    public double getRefConsumption() {
        return refConsumption;
    }

    public CarDetailEntity setRefConsumption(double refConsumption) {
        this.refConsumption = refConsumption;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public CarDetailEntity setStatus(int status) {
        this.status = status;
        return this;
    }

    public float getTank() {
        return tank;
    }

    public CarDetailEntity setTank(float tank) {
        this.tank = tank;
        return this;
    }

    public float getWeight() {
        return weight;
    }

    public CarDetailEntity setWeight(float weight) {
        this.weight = weight;
        return this;
    }
}
