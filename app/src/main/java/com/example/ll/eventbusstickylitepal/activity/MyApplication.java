package com.example.ll.eventbusstickylitepal.activity;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Description : 初始化litpal
 * Copyright   : (c) 2016
 * Author      : Goorwl
 * Email       : goorwl@163.com
 * Date        : 2017/1/4 20:59
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
