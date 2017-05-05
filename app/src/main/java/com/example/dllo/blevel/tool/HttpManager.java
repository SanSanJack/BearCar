package com.example.dllo.blevel.tool;



import com.example.dllo.blevel.service.BearService;

import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WYL on 2017/4/30.
 */

public class HttpManager {

    private Retrofit mRetrofit;
    private BearService mCarServcie;

    private static final AtomicReference<HttpManager> INSTANCE = new AtomicReference<>();

    public static HttpManager getInstance() {

        for (; ; ) {
            HttpManager current = INSTANCE.get();
            if (current != null) {
                return current;
            }

            current = new HttpManager();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }

    private HttpManager() {
        //  创建Retrofit对象
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BearService.BASE_URl)
                // 添加RxJava支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // 添加json解析框架
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mCarServcie = mRetrofit.create(BearService.class);
    }

    public BearService getService(){
        return mCarServcie;
    }
}
