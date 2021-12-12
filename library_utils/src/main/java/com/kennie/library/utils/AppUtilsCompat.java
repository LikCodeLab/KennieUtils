package com.kennie.library.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.List;

/**
 * @项目名 KennieUtils
 * @类名称 AppUtilsCompat
 * @类描述 App相关工具类
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/12/11 10:48
 *
 * <p>
 * --获取App名称                                {@link #getAppName()}
 * --获取App包名                                {@link #getAppPackage()}
 * --获取App版本名称                             {@link #getAppVersionName()}
 * --获取App版本号                               {@link #getAppVersionCode()}
 * --获取App图标                                {@link #getAppIcon()}
 * --获取App安装原始路径                         {@link #getAppInstallPath()}
 * --获取App原始安装文件(APK)                    {@link #getAppSourceFile()}
 * --判断APP是否安装                            {@link #isAppInstalled()}
 * --判断APP是否安装                            {@link #isAppInstalled(String packageName)}
 * --判断APP是否为Debug模式                      {@link #isAppDebugMode()}
 * --判断App是否处于前台                         {@link #isAppForeground()}
 * --安装APP（兼容Android7.0及以上版本）          {@link #installApk(File file, String authority)}
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
    public static String getAppVersionName() {
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
    public static int getAppVersionCode() {
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
    public static Drawable getAppIcon() {
        PackageManager pm = KennieUtilInit.getAppContext().getPackageManager();
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(KennieUtilInit.getAppContext().getPackageName(), 0);
            return applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * APP安装原始路径
     *
     * @return the application's app path
     */
    public static String getAppInstallPath() {
        try {
            ApplicationInfo applicationInfo = KennieUtilInit.getAppContext().getPackageManager().getApplicationInfo(KennieUtilInit.getAppContext().getPackageName(), 0);
            return applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * APP原始安装文件(APK)
     *
     * @return the application's app file.apk
     */
    public static File getAppSourceFile() {
        String sourceDir = getAppInstallPath();
        if (sourceDir != null) {
            return new File(sourceDir);
        }
        return null;
    }


    /**
     * 判断APP应用是否安装
     *
     * @return 是否成功 true|false
     */
    public static boolean isAppInstalled() {
        return isAppInstalled(KennieUtilInit.getAppContext().getPackageName());
    }


    /**
     *
     *
     * @return
     */
    /**
     * 判断APP应用是否安装
     *
     * @param packageName 包名
     * @return 是否成功 true|false
     */
    public static boolean isAppInstalled(String packageName) {
        PackageManager pm = KennieUtilInit.getAppContext().getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 判断APP是否为Debug模式
     *
     * @return 是否成功 true|false
     */
    public static boolean isAppDebugMode() {
        try {
            String packageName = KennieUtilInit.getAppContext().getPackageName();
            PackageInfo packageInfo = KennieUtilInit.getAppContext().getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if (packageInfo != null) {
                ApplicationInfo info = packageInfo.applicationInfo;
                return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断App是否处于前台
     *
     * @return 是否成功 true|false
     */
    public static boolean isAppForeground() {
        ActivityManager am = (ActivityManager) KennieUtilInit.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) return false;
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) return false;
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (aInfo.processName.equals(KennieUtilInit.getApp().getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 安装APP（兼容Android7.0及以上版本）
     *
     * @param file      App文件路径
     * @param authority The authority of a FileProvider defined in a <provider> element in your app's manifest.
     */
    public static void installApk(File file, String authority) {
        if (file == null || !file.exists()) {
            return;
        }
        try {
            PackageManager pm = KennieUtilInit.getApp().getPackageManager();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (!pm.canRequestPackageInstalls()) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + KennieUtilInit.getApp().getPackageName()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    KennieUtilInit.getApp().startActivity(intent);
                    return;
                }
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                uri = FileProvider.getUriForFile(KennieUtilInit.getApp(), authority, file);
            } else {
                uri = Uri.fromFile(file);
            }
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            KennieUtilInit.getApp().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
