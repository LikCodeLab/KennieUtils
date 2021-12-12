package com.kennie.library.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

/**
 * @项目名 KennieUtils
 * @类名称 AppUtilsCompat
 * @类描述 App相关工具类
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/12/11 10:48
 *
 * <p>
 * --获取App名称        {@link #getAppName()}
 * --获取App包名        {@link #getAppPackage()}
 * --获取App版本名称    {@link #getAppVersionName()}
 * --获取App版本号      {@link #getAppVersionCode()}
 * --获取App图标       {@link #getAppIcon()}
 * </p>
 */

public class AppUtilsCompat {

    /**
     * 获取APP名称
     *
     * @return the application's app name
     */
    public static String getAppName() {
        PackageManager pm = KennieUtilInit.getAppContext().getPackageManager();
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(KennieUtilInit.getAppContext().getPackageName(), 0);
            return String.valueOf(pm.getApplicationLabel(applicationInfo));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 获取APP包名
     *
     * @return the application's package name
     */
    public static String getAppPackage() {
        return KennieUtilInit.getAppContext().getPackageName();
    }


    /**
     * 获取APP版本名称
     *
     * @return the application's app version name
     */
    private static String getAppVersionName() {
        try {
            PackageManager packageManager = KennieUtilInit.getAppContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(KennieUtilInit.getAppContext().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 获取APP版本号
     *
     * @return the application's app version code
     */
    private static int getAppVersionCode() {
        try {
            PackageManager packageManager = KennieUtilInit.getAppContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(KennieUtilInit.getAppContext().getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 获取APP图标
     *
     * @return the application's app icon
     */
    private static Drawable getAppIcon() {
        PackageManager pm = KennieUtilInit.getAppContext().getPackageManager();
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(KennieUtilInit.getAppContext().getPackageName(), 0);
            return applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
