package com.kennie.example.utils;

import android.app.Application;
import android.content.Context;

import com.kennie.library.utils.IApp;
import com.kennie.library.utils.KennieUtilsApp;

/**
 * @项目名 KennieUtils
 * @类名称 MyApp
 * @类描述
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 21:41
 */
public class MyApp extends Application implements IApp {

    @Override
    public void onCreate() {
        super.onCreate();
        KennieUtilsApp.init(this);
    }

    @Override
    public Context getAppContext() {
        return this;
    }
}
