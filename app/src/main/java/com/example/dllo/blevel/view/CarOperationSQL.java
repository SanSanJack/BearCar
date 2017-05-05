package com.example.dllo.blevel.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dllo.blevel.entity.BearCarEntity;

import java.util.ArrayList;
import java.util.List;


class CarOperationSQL implements TableCarOperation {

    private CarSQLiteHelper mHelper;
    private String mSql;

   public CarOperationSQL(CarSQLiteHelper helper) {
       Log.d("CarOperationSQL", "CarOperationSQL: "+helper);
        mHelper = helper;
    }

    // TODO: 对代码性能的优化
    // 创建数据库对象
    private SQLiteDatabase openDataBase() {
        return mHelper.getWritableDatabase();
    }

    // 将数据库对象关闭
    private void closeDataBase(SQLiteDatabase db) {
        // 如果数据库中有数据,代表数据库未关闭
//        if (db != null) {
//            // 关闭数据库
//            db.close();
//        }
    }

    @Override
    public void addCar(BearCarEntity car) {
        // 获取数据库对象
        SQLiteDatabase db = openDataBase();
        // 整理SQL语句String
        if (car.getId() == 0) {
            mSql = "insert into cars_tbl (name, selected, model, uuid) values ('" + car.getName() + "', " + car.getSelected(1) + ", " + car.getModel() + ", " + car.getUuid() + ");";
        }else{
              mSql = "insert into cars_tbl values (" +car.getId() + ", '" + car.getName() + "', " + car.getSelected(1) + ", " + car.getModel() + ", " + car.getUuid() + ");";
        }
        db.execSQL(mSql);
        // 当数据库使用完后需要进行关闭
//        closeDataBase(db);
    }

    /**
     * 删除思路:当前选中的是哪辆车,查询ID并将该辆车删除
     *
     * @param id 管理列表中车辆的ID
     */
    @Override
    public void removeCar(int id) {
        // 获取数据库对象
        SQLiteDatabase db = openDataBase();
        // 整理SQL语句
        String str = "delete from cars_tbl where _id = " + id + ";";
        // 执行SQL语句
        db.execSQL(str);
        // 关闭数据库
//        closeDataBase(db);
    }

    /**
     * 更改思路:由于更改车辆信息在车辆管理中操作,与是否被选中无关,因此唯一能判断的标准只有ID
     *
     * @param car 车辆表的实体类(可通过get方法获取车辆的信息)
     */
    @Override
    public void updateCar(BearCarEntity car) {
        updateCar(openDataBase(), true, car);
    }

    private void updateCar(SQLiteDatabase db, boolean isClose, BearCarEntity car) {
        // 整理SQL语句
        String str = "update cars_tbl set name = '" + car.getName() + "', selected = " + car.getSelected(1) + ", model = " + car.getModel()
                + ", uuid = " + car.getUuid() + " where _id = " + car.getId() + ";";
        // 执行SQL语句
        db.execSQL(str);

//        if (isClose) {
//            // 关闭数据库
//            closeDataBase(db);
//        }

    }

    @Override
    public List<BearCarEntity> queryCars() {
        //括号里的值是初始化的长度
        // 因为该方法需要返回一个集合,所以每次查询都需要声明一个新的集合
        // 创建集合的时候可以在括号中填入一个int类型的数据,用来定义这个集合的初始容量
        List<BearCarEntity> cars = new ArrayList<>(5);
        // 获取数据库对象
        SQLiteDatabase db = openDataBase();

        // 整理SQL语句
        String str = "select * from cars_tbl ";
        // 执行SQL语句
        Cursor cursor = db.rawQuery(str, null);
        // 遍历cursor,循环添加数据
        if (cursor != null && cursor.moveToFirst()) {

            // 每次循环都需要获取一遍字段,这自然是不可取的,所以我们可以讲获取字段的过程放在循环外面
            int indexId = cursor.getColumnIndex(CarSQLiteHelper._ID);
            int indexName = cursor.getColumnIndex(CarSQLiteHelper.NAME);
            int indexSelected = cursor.getColumnIndex(CarSQLiteHelper.SELECTED);
            int indexModel = cursor.getColumnIndex(CarSQLiteHelper.MODEL);
            int indexUuid = cursor.getColumnIndex(CarSQLiteHelper.UUID);

            do {
                // 查询自然要先从数据库中进行获取
                int id = cursor.getInt(indexId);
                String name = cursor.getString(indexName);
                int selected = cursor.getInt(indexSelected);
                int model = cursor.getInt(indexModel);
                int uuid = cursor.getInt(indexUuid);

                // 有多少辆车就有多少个新的实体类
                BearCarEntity carIdEntity = new BearCarEntity(id);
                carIdEntity.setName(name);
                carIdEntity.setSelected(selected);
                carIdEntity.setModel(model);
                carIdEntity.setUuid(uuid);

                // 将有数据的实体类添加到集合中
                cars.add(carIdEntity);
            } while (cursor.moveToNext());
        }
        // 关闭数据库
//        closeDataBase(db);
        // 将集合返回
        return cars;
    }

    /**
     * 该方法的目的是查询出当前被选中的车辆信息,因此不用返回一个数据集合
     * 判断的依据为Selected字段(selected = 1)
     */
    @Override
    public BearCarEntity querySelectedCar() {
        // 该方法在用户查询时使用,第二个参数为true,代表当查询完后会关闭数据库
        return querySelectedCar(openDataBase(), true);
    }

    /**
     * 该方法给changeSelectedCar方法使用,目的是减少数据库的开关
     * 且因为该方法是给内部使用 所有权限应设置为private(遵循数据库封装的原则)
     *
     * @param db      数据库对象
     * @param isClose 数据库是否关闭
     */

    private BearCarEntity querySelectedCar(SQLiteDatabase db, boolean isClose) {

        // 整理SQL语句
        String str = "select * from cars_tbl where selected = 1;";
        // 执行SQL语句
        Cursor cursor = db.rawQuery(str, null);
        // 遍历cursor,循环添加数据
        if (cursor != null && cursor.moveToFirst()) {

            // 每次循环都需要获取一遍字段,这自然是不可取的,所以我们可以讲获取字段的过程放在循环外面
            int indexId = cursor.getColumnIndex(CarSQLiteHelper._ID);
            int indexName = cursor.getColumnIndex(CarSQLiteHelper.NAME);
            int indexSelected = cursor.getColumnIndex(CarSQLiteHelper.SELECTED);
            int indexModel = cursor.getColumnIndex(CarSQLiteHelper.MODEL);
            int indexUuid = cursor.getColumnIndex(CarSQLiteHelper.UUID);

            do {
                // 查询自然要先从数据库中进行获取
                int id = cursor.getInt(indexId);
                String name = cursor.getString(indexName);
                int selected = cursor.getInt(indexSelected);
                int model = cursor.getInt(indexModel);
                int uuid = cursor.getInt(indexUuid);

                // 有多少辆车就有多少个新的实体类
                BearCarEntity carIdEntity = new BearCarEntity(id);
                carIdEntity.setName(name);
                carIdEntity.setSelected(selected);
                carIdEntity.setModel(model);
                carIdEntity.setUuid(uuid);

                // 因为被选中的只能有一辆车,所以可以之间将实体类返回
                return carIdEntity;
            } while (cursor.moveToNext());
        }

//        if (isClose) {
//            // 关闭数据库
//            closeDataBase(db);
//        }

        // 如果没有被选中的车辆,那么此次查询只能返回空
        return null;
    }

    /**
     * 该方法的作用是将车辆设置为选中状态
     *
     * @param id 判断依据为想要设置为被选中车辆的ID
     */
    @Override
    public void changeSelectedCar(int id) {
        // 获取数据库对象
        SQLiteDatabase db = openDataBase();

        // 首先要先将当前为选中状态的车辆设置为未选中
        BearCarEntity currentSelectedCar = querySelectedCar();
        // 整理SQL语句(该语句的作用是将当前选中的车辆设置为未选中状态)
        if (currentSelectedCar !=null){
            String cancelStr = "update cars_tbl set selected = 0 where _id = " + currentSelectedCar.getId() + ";";
            db.execSQL(cancelStr);
        }

        // 执行SQL语句

        // 整理SQL语句(该语句的作用是将传入的id的车辆设置为选中状态)
        String setStr = "update cars_tbl set selected = 1 where _id = " + id + ";";
        // 执行SQL语句
        db.execSQL(setStr);
        // 关闭数据库
//        closeDataBase(db);

    }

    @Override
    public void changeSelectedCar(BearCarEntity newSelectedCar) {
        // 获取数据库对象
        SQLiteDatabase db = openDataBase();
        // 首先要先将当前为选中状态的车辆设置为未选中
        BearCarEntity currentSelectedCar = querySelectedCar(db, false);
        currentSelectedCar.setSelected(0);
        // 设置完后需要更新一次数据
        updateCar(db, false, currentSelectedCar);

        // 将新传递进来的车辆设置为选中状态
        newSelectedCar.setSelected(1);
        // 设置完后更新一次数据
        updateCar(db, false, newSelectedCar);

        // 关闭数据库
        closeDataBase(db);
    }


}
