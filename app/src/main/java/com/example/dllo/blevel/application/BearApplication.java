package com.example.dllo.blevel.application;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by dllo on 17/4/17.
 */

public class BearApplication extends Application {

    // 自己定义的Application要想使用需要到Manifest文件中进行声明

    // AS中黄色阴影代表警告,该处提示的是 不要将Context类设置为静态的,这是一个内存泄露
    // 但是由于我们传递的是一个Application,所以没关系
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        // 对Context进行赋值
        sContext = this;
        UMShareAPI.get(this);
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    // 向外界提供一个getContext方法
    public static Context getContext() {
        return sContext;
    }
}
