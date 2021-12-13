package com.kennie.library.utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;

/**
 * @项目名 KennieUtils
 * @类名称 IntentUtilsCompat
 * @类描述 Intent启动意图工具类
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/12 22:05
 *
 * <p>
 * --启动指定包名APP                                {@link #launchApp(String packageName)}
 * </p>
 */
public class IntentUtilsCompat {


    /**
     * 启动APP(根据包名)
     *
     * @param packageName 包名
     * @return {@code true}: 启动成功<br>{@code false}: 启动失败
     */
    public static boolean launchApp(String packageName) {
        try {
            KennieUtilInit.getApp().startActivity(KennieUtilInit.getAppContext().getPackageManager().getLaunchIntentForPackage(packageName));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 跳转到相关详情页(根据actionType)
     *
     * @param actionType – The type of action setting to return. Should be one of
     *                   Settings.ACTION_SETTINGS(系统设置), Settings.ACTION_WIFI_SETTINGS(无线网络),
     *                   Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS(应用管理),
     *                   or Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS(开发者选项).
     */
    public static boolean gotoSettings(String actionType) {
        try {
            Intent intent = new Intent(actionType);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            KennieUtilInit.getApp().startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 跳转到App应用详情页
     */
    public static void gotoAppDetailSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + KennieUtilInit.getApp().getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        KennieUtilInit.getApp().startActivity(intent);
    }


    /**
     * 跳转应用通知设置页
     */
    @SuppressLint({"ObsoleteSdkInt", "InlinedApi"})
    public static void gotoAppNotificationSetting() {
        try {
            Intent intent = new Intent();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // 适用于API26,即8.0（含8.0)以上可以使用
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, KennieUtilInit.getAppContext().getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                KennieUtilInit.getApp().startActivity(intent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // 适用于API21-25,即5.0-7.1之间版本可以使用
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, KennieUtilInit.getAppContext().getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, KennieUtilInit.getAppContext().getApplicationInfo().uid);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                KennieUtilInit.getApp().startActivity(intent);
            } else {
                // 其他情况跳转到App应用详情页
                gotoAppDetailSettings();
            }

        } catch (Exception e) {
            e.printStackTrace();
            gotoAppDetailSettings();
        }
    }


    /**
     * 跳转通知渠道设置页
     *
     * @param channelId 渠道ID
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void gotoChannelSetting(String channelId) {
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, KennieUtilInit.getAppContext().getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
        KennieUtilInit.getApp().startActivity(intent);
    }

}
