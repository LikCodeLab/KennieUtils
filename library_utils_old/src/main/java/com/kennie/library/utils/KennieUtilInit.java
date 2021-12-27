package com.kennie.library.utils;

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
public final class KennieUtilInit {


    //全局上下文
    private static Application sApp;

    private KennieUtilInit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * @param app
     */
    public static void init(final Application app) {
        if (app == null) {
            Log.e("KennieUtilInit", "Application is null");
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
        throw new NullPointerException("should be initialized KennieUtilInit in application.");
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
        throw new NullPointerException("should be initialized KennieUtilInit in application.");
    }

}
