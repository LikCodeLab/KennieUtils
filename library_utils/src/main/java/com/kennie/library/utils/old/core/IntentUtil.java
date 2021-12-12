package com.kennie.library.utils.old.core;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.kennie.library.utils.KennieUtilInit;

/**
 * @项目名 KennieUtils
 * @类名称 IntentUtil
 * @类描述
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 22:36
 */
public class IntentUtil {

    /**
     * 跳转到指定应用
     *
     * @param packageName
     * @return
     */
    public static boolean launchApp(String packageName) {
        try {
            KennieUtilInit.getApp().startActivity(KennieUtilInit.getApp().getPackageManager().getLaunchIntentForPackage(packageName));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 跳转到应用详情
     */
    public static void gotoAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + KennieUtilInit.getApp().getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        KennieUtilInit.getApp().startActivity(intent);
    }

    public static void gotoApplicationSettings() {
        Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        KennieUtilInit.getApp().startActivity(intent);
    }

    /**
     * 跳转到应用详情
     */
    public static void gotoWifiSettings() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        KennieUtilInit.getApp().startActivity(intent);
    }

    /**
     * 跳转到系统设置
     */
    public static void gotoSettings() {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        KennieUtilInit.getApp().startActivity(intent);
    }

    /**
     * 跳转到开发者选项
     */
    public static void gotoDevelopmentSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        KennieUtilInit.getApp().startActivity(intent);
    }
}
