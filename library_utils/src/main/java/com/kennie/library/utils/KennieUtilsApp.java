package com.kennie.library.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * @项目名 KennieUtils
 * @类名称 KennieUtilsInit
 * @类描述 工具模块 初始化基类
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/5 21:37
 */
public final class KennieUtilsApp {

    private static final String TAG = KennieUtilsApp.class.getSimpleName();

    //全局上下文
    @SuppressLint("StaticFieldLeak")
    private static Application sApp;

    private KennieUtilsApp() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * @param app
     */
    public static void init(final Application app) {
        if (app == null) {
            Log.e("KennieUtilsInit", "Application is null");
            return;
        }
        if (sApp == null) {
            sApp = app;
            return;
        }
        if (sApp.equals(app)) return;
        sApp = app;
    }


    public static Application getApp() {
        if (sApp != null) return sApp;
        throw new NullPointerException("should be initialized KennieUtilsInit in application.");
    }


    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getAppContext() {
        if (sApp != null) {
            return sApp.getApplicationContext();
        }
        throw new NullPointerException("should be initialized KennieUtilsInit in application.");
    }

}
