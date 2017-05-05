package com.example.dllo.blevel.service;

import com.example.dllo.blevel.entity.CarDetail;
import com.example.dllo.blevel.entity.CarDetailEntity;
import com.example.dllo.blevel.entity.CarInformationEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by WYL on 2017/4/30.
 */

//使用如下rxjava对其使用
public interface BearService {

 // 基础的网址
 String BASE_URl = "http://www.xiaoxiongyouhao.com/";

 // 有两种方式,普通的使用Call,RxJava的使用Observe
 @GET("models/pinpai.json")
 Observable<CarInformationEntity> getBrand();

 @GET("models/{index}/che_xi.json")
 Observable<CarInformationEntity> getCarSeries(@Path("index") int index);

 @GET("models/{indexBrand}/che_xing_{indexSeries}.json")
 Observable<CarInformationEntity> getCarType(@Path("indexBrand") int brand, @Path("indexSeries") int series);

 @GET("models/spec.php")
 Observable<CarDetail> getCarDetail(@Query("cheXing") int type);
}

