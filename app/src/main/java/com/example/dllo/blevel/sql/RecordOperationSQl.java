package com.example.dllo.blevel.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dllo.blevel.entity.MoneyEntity;
import com.example.dllo.blevel.entity.RecordsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/4/17.
 */

public class RecordOperationSQl implements RecordOperation {
    private CarSQLiteHelper mhelper;
    //构造方法传递CarSQLiteHelper
    public RecordOperationSQl(CarSQLiteHelper mhelper){
            this.mhelper=mhelper;
    }

    @Override
    public void addRecords(RecordsEntity recordsEntity) {

        //获取数据库对象
        SQLiteDatabase db = mhelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        //
        if (recordsEntity.getId() != 0) {
            values.put(CarSQLiteHelper.RECORD_ID, recordsEntity.getId());
        }
            values.put(CarSQLiteHelper.RECORD_DATE, recordsEntity.getDate());
            values.put(CarSQLiteHelper.RECORD_ODOMETER, recordsEntity.getOdometer());
            values.put(CarSQLiteHelper.RECORD_PRICE, recordsEntity.getPrice());
            values.put(CarSQLiteHelper.RECORD_PRICE, recordsEntity.getType());
            values.put(CarSQLiteHelper.RECORD_GASSUP, recordsEntity.getGassup());
            values.put(CarSQLiteHelper.RECORD_REMARK, recordsEntity.getRemark());
            values.put(CarSQLiteHelper.RECORD_CARID, recordsEntity.getCarld());
            values.put(CarSQLiteHelper.RECORD_FORGET, recordsEntity.getForget());
            values.put(CarSQLiteHelper.RECORD_LIGHT_ON, recordsEntity.getLightOn());
            values.put(CarSQLiteHelper.RECORD_STATION_ID, recordsEntity.getStationld());
            db.insert(CarSQLiteHelper.RECORD_TBL, null, values);
            db.close();
        }




        //记录表的移除
    @Override
    public void removeRecords(int id) {
        SQLiteDatabase db = mhelper.getReadableDatabase();
        String whereClause = CarSQLiteHelper.RECORD_ID +" ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.delete(CarSQLiteHelper.RECORD_TBL,whereClause,whereArgs);
        db.close();
    }
        //记录表的改正
    @Override
    public void updateRecords(RecordsEntity recordsEntity) {
        updateRecords(openDatabase(), true, recordsEntity);
    }

    private void updateRecords(SQLiteDatabase db, boolean isClose, RecordsEntity recordsEntity) {
        ContentValues values = new ContentValues();
        values.put(CarSQLiteHelper.RECORD_ID,recordsEntity.getId());
        values.put(CarSQLiteHelper.RECORD_DATE, recordsEntity.getDate());
        values.put(CarSQLiteHelper.RECORD_ODOMETER, recordsEntity.getOdometer());
        values.put(CarSQLiteHelper.RECORD_PRICE, recordsEntity.getPrice());
        values.put(CarSQLiteHelper.RECORD_PRICE, recordsEntity.getType());
        values.put(CarSQLiteHelper.RECORD_GASSUP, recordsEntity.getGassup());
        values.put(CarSQLiteHelper.RECORD_REMARK, recordsEntity.getRemark());
        values.put(CarSQLiteHelper.RECORD_CARID, recordsEntity.getCarld());
        values.put(CarSQLiteHelper.RECORD_FORGET, recordsEntity.getForget());
        values.put(CarSQLiteHelper.RECORD_LIGHT_ON, recordsEntity.getLightOn());
        values.put(CarSQLiteHelper.RECORD_STATION_ID, recordsEntity.getStationld());
        String whereClause = CarSQLiteHelper.RECORD_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(recordsEntity.getId())};
        db.update(CarSQLiteHelper.RECORD_TBL, values, whereClause, whereArgs);
        if (isClose) {
            closeDatebase(db);
        }
    }

    private void closeDatebase(SQLiteDatabase db){
        if (db != null){
            db.close();
        }
    }


    @Override
    public List<RecordsEntity> querySelectedRecords() {
        List<RecordsEntity> recordsEntities = new ArrayList<>();
        SQLiteDatabase db = mhelper.getWritableDatabase();
        String sql = "SELECT A.*\n" +
                "FROM records_tbl as " +
                "" +
                ", cars_tbl AS B\n" +
                "where\n" +
                "A.carId = B.\"_id\"\n" +
                "AND\n" +
                "B.selected = 1;";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            int indexId = cursor.getColumnIndex(CarSQLiteHelper._ID);
            int indexDate = cursor.getColumnIndex(CarSQLiteHelper.RECORD_DATE);
            int indesOdometer = cursor.getColumnIndex(CarSQLiteHelper.RECORD_ODOMETER);
            int indexPrice = cursor.getColumnIndex(CarSQLiteHelper.RECORD_PRICE);
            int indexType = cursor.getColumnIndex(CarSQLiteHelper.RECORD_TYPE);
            int indexGassup = cursor.getColumnIndex(CarSQLiteHelper.RECORD_GASSUP);
            int indexRemark = cursor.getColumnIndex(CarSQLiteHelper.RECORD_REMARK);
            int indexCarld = cursor.getColumnIndex(CarSQLiteHelper.RECORD_CARID);
            int indexForget = cursor.getColumnIndex(CarSQLiteHelper.RECORD_FORGET);
            int indexLightOn = cursor.getColumnIndex(CarSQLiteHelper.RECORD_LIGHT_ON);
            int indexStationld = cursor.getColumnIndex(CarSQLiteHelper.RECORD_STATION_ID);
            do {
                int id = cursor.getInt(indexId);
                long date = cursor.getLong(indexDate);
                int odometer = cursor.getInt(indesOdometer);
                double price = cursor.getDouble(indexPrice);
                int type = cursor.getInt(indexType);
                int gassup = cursor.getInt(indexGassup);
                String remark = cursor.getString(indexRemark);
                int carld = cursor.getInt(indexCarld);
                int forget = cursor.getInt(indexForget);
                int lightOn = cursor.getInt(indexLightOn);
                int stationld = cursor.getInt(indexStationld);
                RecordsEntity record = new RecordsEntity(id);
                record.setDate(date);
                record.setCarld(carld);
                record.setForget(forget);
                record.setGassup(gassup);
                record.setLightOn(lightOn);
                record.setOdometer(odometer);
                record.setPrice(price);
                record.setRemark(remark);
                record.setStationld(stationld);
                record.setType(type);
                recordsEntities.add(record);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return recordsEntities;

    }

    @Override
    public List<RecordsEntity> querySelectedYearRecords() {
        List<RecordsEntity> recordsEntities = new ArrayList<>();
        SQLiteDatabase db = mhelper.getWritableDatabase();
        String sql = "SELECT A.date, A.odometer, A.price, A.yuan, A.carId, datetime(A.date / 1000, 'unixepoch')\n" +
                "from records_tbl as A, cars_tbl as B\n" +
                "WHERE A.date > (\n" +
                "    strftime('%s', datetime('now', '-12 month')) * 1000\n" +
                ")\n" +
                "AND\n" +
                "A.carId = B.\"_id\"\n" +
                "AND\n" +
                "B.selected = 1;";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            int indexId = cursor.getColumnIndex(CarSQLiteHelper._ID);
            int indexDate = cursor.getColumnIndex(CarSQLiteHelper.RECORD_DATE);
            int indesOdometer = cursor.getColumnIndex(CarSQLiteHelper.RECORD_ODOMETER);
            int indexPrice = cursor.getColumnIndex(CarSQLiteHelper.RECORD_PRICE);
            int indexType = cursor.getColumnIndex(CarSQLiteHelper.RECORD_TYPE);
            int indexGassup = cursor.getColumnIndex(CarSQLiteHelper.RECORD_GASSUP);
            int indexRemark = cursor.getColumnIndex(CarSQLiteHelper.RECORD_REMARK);
            int indexCarld = cursor.getColumnIndex(CarSQLiteHelper.RECORD_CARID);
            int indexForget = cursor.getColumnIndex(CarSQLiteHelper.RECORD_FORGET);
            int indexLightOn = cursor.getColumnIndex(CarSQLiteHelper.RECORD_LIGHT_ON);
            int indexStationld = cursor.getColumnIndex(CarSQLiteHelper.RECORD_STATION_ID);
            do {
                int id = cursor.getInt(indexId);
                long date = cursor.getLong(indexDate);
                int odometer = cursor.getInt(indesOdometer);
                double price = cursor.getDouble(indexPrice);
                int type = cursor.getInt(indexType);
                int gassup = cursor.getInt(indexGassup);
                String remark = cursor.getString(indexRemark);
                int carld = cursor.getInt(indexCarld);
                int forget = cursor.getInt(indexForget);
                int lightOn = cursor.getInt(indexLightOn);
                int stationld = cursor.getInt(indexStationld);
                RecordsEntity record = new RecordsEntity(id);
                record.setDate(date);
                record.setCarld(carld);
                record.setForget(forget);
                record.setGassup(gassup);
                record.setLightOn(lightOn);
                record.setOdometer(odometer);
                record.setPrice(price);
                record.setRemark(remark);
                record.setStationld(stationld);
                record.setType(type);
                recordsEntities.add(record);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return recordsEntities;
    }

    @Override
    public List<RecordsEntity> querySelectedHalfTearRecords() {
        List<RecordsEntity> recordsEntities = new ArrayList<>();
        SQLiteDatabase db = mhelper.getWritableDatabase();
        String sql = "SELECT A.date, A.odometer, A.price, A.yuan, A.carId, datetime(A.date / 1000, 'unixepoch')\n" +
                "from records_tbl as A, cars_tbl as B\n" +
                "WHERE A.date > (\n" +
                "    strftime('%s', datetime('now', '-6 month')) * 1000\n" +
                ")\n" +
                "AND\n" +
                "A.carId = B.\"_id\"\n" +
                "AND\n" +
                "B.selected = 1;";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            int indexId = cursor.getColumnIndex(CarSQLiteHelper._ID);
            int indexDate = cursor.getColumnIndex(CarSQLiteHelper.RECORD_DATE);
            int indesOdometer = cursor.getColumnIndex(CarSQLiteHelper.RECORD_ODOMETER);
            int indexPrice = cursor.getColumnIndex(CarSQLiteHelper.RECORD_PRICE);
            int indexType = cursor.getColumnIndex(CarSQLiteHelper.RECORD_TYPE);
            int indexGassup = cursor.getColumnIndex(CarSQLiteHelper.RECORD_GASSUP);
            int indexRemark = cursor.getColumnIndex(CarSQLiteHelper.RECORD_REMARK);
            int indexCarld = cursor.getColumnIndex(CarSQLiteHelper.RECORD_CARID);
            int indexForget = cursor.getColumnIndex(CarSQLiteHelper.RECORD_FORGET);
            int indexLightOn = cursor.getColumnIndex(CarSQLiteHelper.RECORD_LIGHT_ON);
            int indexStationld = cursor.getColumnIndex(CarSQLiteHelper.RECORD_STATION_ID);
            do {
                int id = cursor.getInt(indexId);
                long date = cursor.getLong(indexDate);
                int odometer = cursor.getInt(indesOdometer);
                double price = cursor.getDouble(indexPrice);
                int type = cursor.getInt(indexType);
                int gassup = cursor.getInt(indexGassup);
                String remark = cursor.getString(indexRemark);
                int carld = cursor.getInt(indexCarld);
                int forget = cursor.getInt(indexForget);
                int lightOn = cursor.getInt(indexLightOn);
                int stationld = cursor.getInt(indexStationld);
                RecordsEntity record = new RecordsEntity(id);
                record.setDate(date);
                record.setCarld(carld);
                record.setForget(forget);
                record.setGassup(gassup);
                record.setLightOn(lightOn);
                record.setOdometer(odometer);
                record.setPrice(price);
                record.setRemark(remark);
                record.setStationld(stationld);
                record.setType(type);
                recordsEntities.add(record);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return recordsEntities;

    }

    @Override
    public List<RecordsEntity> querySelectedThreeMonthsRecords() {
        return null;
    }

    @Override
    public List<RecordsEntity> queryMonthMoney() {
        List<RecordsEntity> recordsEntities = new ArrayList<>();
        SQLiteDatabase db = mhelper.getWritableDatabase();
        String sql = "SELECT A.date, A.odometer, A.price, A.yuan, A.carId, datetime(A.date / 1000, 'unixepoch')\n" +
                "from records_tbl as A, cars_tbl as B\n" +
                "WHERE A.date > (\n" +
                "    strftime('%s', datetime('now', '-3 month')) * 1000\n" +
                ")\n" +
                "AND\n" +
                "A.carId = B.\"_id\"\n" +
                "AND\n" +
                "B.selected = 1;";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            int indexId = cursor.getColumnIndex(CarSQLiteHelper._ID);
            int indexDate = cursor.getColumnIndex(CarSQLiteHelper.RECORD_DATE);
            int indesOdometer = cursor.getColumnIndex(CarSQLiteHelper.RECORD_ODOMETER);
            int indexPrice = cursor.getColumnIndex(CarSQLiteHelper.RECORD_PRICE);
            int indexType = cursor.getColumnIndex(CarSQLiteHelper.RECORD_TYPE);
            int indexGassup = cursor.getColumnIndex(CarSQLiteHelper.RECORD_GASSUP);
            int indexRemark = cursor.getColumnIndex(CarSQLiteHelper.RECORD_REMARK);
            int indexCarld = cursor.getColumnIndex(CarSQLiteHelper.RECORD_CARID);
            int indexForget = cursor.getColumnIndex(CarSQLiteHelper.RECORD_FORGET);
            int indexLightOn = cursor.getColumnIndex(CarSQLiteHelper.RECORD_LIGHT_ON);
            int indexStationld = cursor.getColumnIndex(CarSQLiteHelper.RECORD_STATION_ID);
            do {
                int id = cursor.getInt(indexId);
                long date = cursor.getLong(indexDate);
                int odometer = cursor.getInt(indesOdometer);
                double price = cursor.getDouble(indexPrice);
                int type = cursor.getInt(indexType);
                int gassup = cursor.getInt(indexGassup);
                String remark = cursor.getString(indexRemark);
                int carld = cursor.getInt(indexCarld);
                int forget = cursor.getInt(indexForget);
                int lightOn = cursor.getInt(indexLightOn);
                int stationld = cursor.getInt(indexStationld);
                RecordsEntity record = new RecordsEntity(id);
                record.setDate(date);
                record.setCarld(carld);
                record.setForget(forget);
                record.setGassup(gassup);
                record.setLightOn(lightOn);
                record.setOdometer(odometer);
                record.setPrice(price);
                record.setRemark(remark);
                record.setStationld(stationld);
                record.setType(type);
                recordsEntities.add(record);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();

        return recordsEntities;
    }

    @Override
    public List<MoneyEntity> queryYearMoney() {
        List<MoneyEntity> moneyEntities = new ArrayList<>();
        SQLiteDatabase db = mhelper.getWritableDatabase();
        String sql = "select" +
                " strftime('%Y',datetime(A.date / 1000, 'unixepoch'),'localtime') time," +
                " sum(A.yuan) money" +
                " from records_tbl as A, cars_tbl as B" +
                " where A" +
                ".carId = B._id and B.selected = 1" +
                " GROUP BY time" +
                " ORDER BY time;";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            int indexTime = cursor.getColumnIndex("time");
            int indexMoney = cursor.getColumnIndex("money");
            do {
                String time = cursor.getString(indexTime);
                float money = cursor.getFloat(indexMoney);
                MoneyEntity moneyEntity = new MoneyEntity();
                moneyEntity.setTime(time);
                moneyEntity.setMoney(money);
                moneyEntities.add(moneyEntity);

            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return moneyEntities;
    }

    private SQLiteDatabase openDatabase() {
        return mhelper.getWritableDatabase() ;
    }


}
