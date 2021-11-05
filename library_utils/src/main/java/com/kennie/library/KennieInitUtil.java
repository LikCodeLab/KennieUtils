package com.kennie.library;

import android.app.Application;
import android.content.Context;

/**
 * @项目名 KennieUtils
 * @类名称 KennieInitUtil
 * @类描述 工具模块 初始化基类
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 21:37
 */
public final class KennieInitUtil {

    private static final String TAG = KennieInitUtil.class.getSimpleName();

    //全局上下文
    static Context sApp;
    //static boolean isOpenLogRecord;
    private static volatile KennieInitUtil sInstance;

    /**
     * 单例模式
     *
     * @return
     */
    public static KennieInitUtil instance() {
        if (sInstance == null) {
            synchronized (KennieInitUtil.class) {
                if (sInstance == null) {
                    sInstance = new KennieInitUtil();
                }
            }
        }
        return sInstance;
    }

    /**
     * 初始化上下文，注册interface
     *
     * @param application 全局上下文
     */
    public void create(Application application) {
        KennieInitUtil.sApp = application.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (sApp != null) {
            return sApp;
        }
        throw new NullPointerException("should be initialized in application");
    }

}
