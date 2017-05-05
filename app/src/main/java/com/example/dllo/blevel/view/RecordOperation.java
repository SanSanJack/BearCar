package com.example.dllo.blevel.view;

import com.example.dllo.blevel.entity.MoneyEntity;
import com.example.dllo.blevel.entity.RecordsEntity;

import java.util.List;

/**
 * Created by dllo on 17/4/17.
 */

public interface RecordOperation  {
    void addRecords(RecordsEntity recordsEntity);
    void removeRecords(int id);
    void updateRecords(RecordsEntity recordsEntity);
    List<RecordsEntity> querySelectedRecords();
    List<RecordsEntity> querySelectedYearRecords();
    List<RecordsEntity> querySelectedHalfTearRecords();
    List<RecordsEntity> querySelectedThreeMonthsRecords();
    List<RecordsEntity> queryMonthMoney();
    List<MoneyEntity> queryYearMoney();

}
