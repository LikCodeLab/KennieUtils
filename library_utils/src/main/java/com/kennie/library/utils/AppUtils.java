package com.kennie.library.utils;

import android.content.Context;
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
