package com.kennie.library.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.kennie.library.KennieUtilsInit;

/**
 * @项目名 KennieUtils
 * @类名称 AppUtils
 * @类描述 App管理类
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 20:54
 */
public class AppUtils {


    /**
     * 获取APP名称
     *
     * @return APP名称
     */
    public static String getAppName() {
        return getAppName(KennieUtilsInit.getsApp());
    }

    /**
     * 获取APP名称
     *
     * @param context 上下文
     * @return APP名称
     */
    private static String getAppName(Context context) {
        return getAppName(context, context.getPackageName());
    }

    /**
     * 获取APP名称
     *
     * @param context     上下文
     * @param packageName 包名
     * @return APP名称
     */
    private static String getAppName(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        String appName = null;
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            appName = String.valueOf(pm.getApplicationLabel(applicationInfo));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }

    /**
     * 获取APP版本名
     */
    public static String getAppVersionName() {
        try {
            PackageManager packageManager = KennieUtilsInit.getsApp().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(KennieUtilsInit.getsApp().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取APP版本号
     */
    public static int getAppVersionCode() {
        try {
            PackageManager packageManager = KennieUtilsInit.getsApp().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(KennieUtilsInit.getsApp().getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
