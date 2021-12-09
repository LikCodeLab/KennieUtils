package com.kennie.library.utils.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;

import androidx.core.content.FileProvider;

import com.kennie.library.utils.KennieUtilsApp;

import java.io.File;
import java.util.List;

/**
 * @项目名 KennieUtils
 * @类名称 AppUtil
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 20:54
 * @desc AppUtil相关工具类
 * <p>
 * --获取App名称 {@link #getAppName()}
 * --获取App包名 {@link #getAppPackage()}
 * --获取App版本号 {@link #getAppVersionName()}
 * --获取App版本Code {@link #getAppVersionCode()}
 * --获取App图标 {@link #getAppIcon()}
 * --获取App安装原始路径 {@link #getAppInstallSourcePath()}
 * --获取App原始安装文件(APK) {@link #getAppSourceFile()}
 * --APP是否安装 {@link #isAppInstalled(String packageName)}
 * --判断App是否处于前台 {@link #isAppForeground()}
 * </p>
 */
public class AppUtil {

    private AppUtil() {
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
     * 判断APP应用是否安装
     *
     * @param packageName 包名
     * @return true 已安装 false 未安装
     */
    public static boolean isAppInstalled(String packageName) {
        PackageManager pm = KennieUtilsApp.getApp().getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断APP程序是否为Debug模式
     *
     * @return true
     */
    public static boolean isDebugMode() {
        try {
            String packageName = KennieUtilsApp.getApp().getPackageName();
            PackageInfo packageInfo = KennieUtilsApp.getApp().getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
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
     * 获取App中AndroidManifest.xml中的meta标签数据
     *
     * @param metaName meta标签名称
     * @return meta标签数据
     */
    public static String getAppMetaValue(String metaName) {
        String value = "";
        try {
            ApplicationInfo appInfo = KennieUtilsApp.getApp().getPackageManager().getApplicationInfo(KennieUtilsApp.getApp().getPackageName(), PackageManager.GET_META_DATA);
            value = String.valueOf(appInfo.metaData.get(metaName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * 安装APP，兼容Android7.0及以上版本
     *
     * @param file      App文件路径
     * @param authority The authority of a FileProvider defined in a <provider> element in your app's manifest.
     */
    public static void installApk(File file, String authority) {
        if (file == null || !file.exists()) {
            return;
        }
        try {
            PackageManager pm = KennieUtilsApp.getApp().getPackageManager();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (!pm.canRequestPackageInstalls()) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + KennieUtilsApp.getApp().getPackageName()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    KennieUtilsApp.getApp().startActivity(intent);
                    return;
                }
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                uri = FileProvider.getUriForFile(KennieUtilsApp.getApp(), authority, file);
            } else {
                uri = Uri.fromFile(file);
            }
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            KennieUtilsApp.getApp().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 判断当前进程是否为主进程
     *
     * @return true 是 false 否
     */
    public static boolean isMainProcess() {
        ActivityManager am = ((ActivityManager) KennieUtilsApp.getApp().getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = KennieUtilsApp.getApp().getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断App是否处于前台
     *
     * @return true | false
     */
    public static boolean isAppForeground() {
        ActivityManager am = (ActivityManager) KennieUtilsApp.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) return false;
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) return false;
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (aInfo.processName.equals(KennieUtilsApp.getApp().getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 根据包名获取APP是否正在运行
     *
     * @param packageName 包名
     * @return true | false
     */
    public static boolean isAppRunning(String packageName) {
        boolean isAppRunning = false;
        ActivityManager am = (ActivityManager) KennieUtilsApp.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(packageName) && info.baseActivity.getPackageName().equals(packageName)) {
                isAppRunning = true;
                //find it, break
                break;
            }
        }
        return isAppRunning;
    }
}
