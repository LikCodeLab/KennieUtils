package com.kennie.library.utils.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.kennie.library.utils.KennieUtilsApp;

import java.io.File;
import java.util.List;

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
     * getAppName                        : 获取 App 名称
     * getAppPackage                     : 获取 App 包名
     * getAppVersionName                 : 获取 App 版本号
     * getAppVersionCode                 : 获取 App 版本Code
     * getAppIcon                        : 获取 App 图标
     * getAppInstallSourcePath           : 获取 App 安装原始路径
     * getAppSourceFile                  : 获取 App 原始安装文件(APK)
     * isAppInstalled                    : APP是否安装
     */

    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 获取APP名称
     *
     * @return APP名称
     */
    public static String getAppName() {
        return getAppName(KennieUtilsApp.getApp());
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
     * 获取APP包名
     *
     * @return the application's package name
     */
    public static String getAppPackage() {
        return KennieUtilsApp.getApp().getPackageName();
    }

    /**
     * 获取APP版本号
     *
     * @return APP版本号
     */
    public static String getAppVersionName() {
        return getAppVersionName(KennieUtilsApp.getApp(), KennieUtilsApp.getApp().getPackageName());
    }

    /**
     * 获取APP版本号
     *
     * @param context     上下文
     * @param packageName 包名
     * @return APP版本号
     */
    private static String getAppVersionName(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取APP版本Code
     *
     * @return APP版本Code
     */
    public static int getAppVersionCode() {
        return getAppVersionCode(KennieUtilsApp.getApp(), KennieUtilsApp.getApp().getPackageName());
    }

    /**
     * 获取APP版本Code
     *
     * @param context     上下文
     * @param packageName 包名
     * @return APP版本Code
     */
    private static int getAppVersionCode(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取APP图标
     *
     * @return APP图标
     */
    public static Drawable getAppIcon() {
        return getAppIcon(KennieUtilsApp.getApp());
    }

    /**
     * 获取APP图标
     *
     * @param context 上下文
     * @return APP图标
     */
    private static Drawable getAppIcon(Context context) {
        return getAppIcon(context, context.getPackageName());
    }

    /**
     * 获取APP图标
     *
     * @param context     上下文
     * @param packageName 包名
     * @return APP图标
     */
    private static Drawable getAppIcon(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        Drawable appIcon = null;
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            appIcon = applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appIcon;
    }

    /**
     * APP安装原始路径
     *
     * @return APP路径
     */
    public static String getAppInstallSourcePath() {
        return getAppInstallSourcePath(KennieUtilsApp.getApp());
    }

    /**
     * APP安装原始路径
     *
     * @param context 上下文
     * @return APP路径
     */
    private static String getAppInstallSourcePath(Context context) {
        return getAppInstallSourcePath(context, context.getPackageName());
    }

    /**
     * APP安装原始路径
     *
     * @param context     上下文
     * @param packageName 包名
     * @return APP路径
     */
    private static String getAppInstallSourcePath(Context context, String packageName) {
        String sourceDir = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            sourceDir = applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return sourceDir;
    }

    /**
     * APP原始安装文件(APK)
     *
     * @return 文件.apk
     */
    public static File getAppSourceFile() {
        return getAppSourceFile(KennieUtilsApp.getApp());
    }

    /**
     * APP原始文件(APK)
     *
     * @param context 上下文
     * @return 文件.apk
     */
    private static File getAppSourceFile(Context context) {
        return getAppSourceFile(context, context.getPackageName());
    }

    /**
     * APP原始安装文件(APK)
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 文件.apk
     */
    private static File getAppSourceFile(Context context, String packageName) {
        String sourceDir = getAppInstallSourcePath(context, packageName);
        if (sourceDir != null) {
            return new File(sourceDir);
        }
        return null;
    }


    /**
     * APP是否安装
     *
     * @param packageName 包名
     * @return true 已安装 false 未安装
     */
    public static boolean isAppInstalled(String packageName) {
        boolean installed = false;
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        List<ApplicationInfo> installedApplications = KennieUtilsApp.getApp().getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo in : installedApplications) {
            if (packageName.equals(in.packageName)) {
                installed = true;
                break;
            } else {
                installed = false;
            }
        }
        return installed;
    }
}
