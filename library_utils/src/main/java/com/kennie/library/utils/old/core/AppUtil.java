package com.kennie.library.utils.old.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;

import androidx.core.content.FileProvider;

import com.kennie.library.utils.KennieUtilInit;

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




 * --获取App安装原始路径 {@link #getAppInstallSourcePath()}
 * --获取App原始安装文件(APK) {@link #getAppSourceFile()}
 * --APP是否安装 {@link #isAppInstalled(String packageName)}
 * --判断App是否处于前台 {@link #isAppForeground()}
 * </p>
 */
public class AppUtil {




    /**
     * APP安装原始路径
     *
     * @return APP路径
     */
    public static String getAppInstallSourcePath() {
        return getAppInstallSourcePath(KennieUtilInit.getApp());
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
        return getAppSourceFile(KennieUtilInit.getApp());
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
        PackageManager pm = KennieUtilInit.getApp().getPackageManager();
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
            String packageName = KennieUtilInit.getApp().getPackageName();
            PackageInfo packageInfo = KennieUtilInit.getApp().getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
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
            ApplicationInfo appInfo = KennieUtilInit.getApp().getPackageManager().getApplicationInfo(KennieUtilInit.getApp().getPackageName(), PackageManager.GET_META_DATA);
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


    /**
     * 判断当前进程是否为主进程
     *
     * @return true 是 false 否
     */
    public static boolean isMainProcess() {
        ActivityManager am = ((ActivityManager) KennieUtilInit.getApp().getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = KennieUtilInit.getApp().getPackageName();
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
     * 根据包名获取APP是否正在运行
     *
     * @param packageName 包名
     * @return true | false
     */
    public static boolean isAppRunning(String packageName) {
        boolean isAppRunning = false;
        ActivityManager am = (ActivityManager) KennieUtilInit.getApp().getSystemService(Context.ACTIVITY_SERVICE);
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
