package com.example.dllo.blevel.view;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * If the code is not wrong its author is JokerCats.
 */

class CarSQLiteHelper extends SQLiteOpenHelper {

    // 因为SQLiteOpenHelper是抽象类,所以只能创建一个该对象继承它来使用

    // 被static修饰的会存储在常量区,调用速度快
    private static final String DB_NAME = "oil.db";
    private static final int VERSION = 1;

    // 表名
    private static final String CARS_TBL = "cars_tbl";
    static final String RECORD_TBL = "record";
    // 创建表时需要的字段(这里是字段 因此要求都是String类型)
    static final String NAME = "name";
    static final String SELECTED = "selected";
    static final String MODEL = "model";
    static final String _ID = "_id";
    static final String UUID = "uuid";
    static final String RECORD_ID = "id";
    static final String RECORD_DATE = "date";
    static final String RECORD_ODOMETER = "odometer";
    static final String RECORD_PRICE = "price";
    static final String RECORD_YUAN = "yuan";
    static final String RECORD_TYPE = "type";
    static final String RECORD_GASSUP = "gassup";
    static final String RECORD_REMARK = "remark";
    static final String RECORD_FORGET = "forget";
    static final String RECORD_LIGHT_ON = "lightOn";
    static final String RECORD_STATION_ID = "stationId";
    static final String RECORD_CARID = "carId";

    public CarSQLiteHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }
    // 简化构造方法 只需要填写一个content
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建表语句
        String createCar = "create table " + CARS_TBL + " (" + _ID +
                " integer primary key autoincrement," +
                NAME + " text not null," +
                SELECTED + " integer not null," +
                MODEL + " integer," +
                UUID + " integer);";
        // 执行建表语句
        db.execSQL(createCar);
        db.execSQL("create table if not exists " + RECORD_TBL + " ("
                + RECORD_ID + " integer primary key autoincrement, "
                + RECORD_DATE + " integer, "
                + RECORD_ODOMETER + " integer, "
                + RECORD_PRICE + " real, "
                + RECORD_YUAN + " real, "
                + RECORD_TYPE + " integer, "
                + RECORD_GASSUP + " integer, "
                + RECORD_REMARK + " string, "
                + RECORD_FORGET + " integer, "
                + RECORD_LIGHT_ON + " integer, "
                + RECORD_CARID + " integer, "
                + RECORD_STATION_ID + " integer);");
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
