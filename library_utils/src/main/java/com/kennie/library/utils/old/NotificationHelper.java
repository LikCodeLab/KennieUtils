package com.kennie.library.utils.old;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.kennie.library.utils.KennieUtilInit;
import com.kennie.library.utils.old.core.IntentUtil;
import com.kennie.library.utils.old.entity.NotificationInfo;

/**
 * @项目名 KennieUtils
 * @类名称 NotificationHelper
 * @类描述 通知帮助类
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 23:17
 */
public class NotificationHelper {

    /**
     * 获取 Notification Builder
     *
     * @param notificationInfo
     * @return
     */
    public static NotificationCompat.Builder getNotificationBuilder(NotificationInfo notificationInfo) {
        NotificationManager notificationManager = (NotificationManager) KennieUtilInit.getApp().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(KennieUtilInit.getApp(), notificationInfo.getChannelId());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannelGroup group = new NotificationChannelGroup(notificationInfo.getGroupId(), notificationInfo.getGroupName());
            notificationManager.createNotificationChannelGroup(group);
            @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(notificationInfo.getChannelId(), notificationInfo.getChannelName(), notificationInfo.getImportance());
            channel.setGroup(notificationInfo.getGroupId());
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(notificationInfo.getChannelId());
        }
        return builder;
    }

    /**
     * 判断通知功能是否开启
     *
     * @return true 是  false 否
     */
    public static boolean isNotificationEnabled() {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(KennieUtilInit.getApp());
        return notificationManager.areNotificationsEnabled();
    }

    /**
     * 判断渠道通知是否开启
     *
     * @param channelId
     * @return true 是  false 否
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isChannelsEnabled(String channelId) {
        NotificationManager notificationManager = (NotificationManager) KennieUtilInit.getApp().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelId);
        if (notificationChannel != null && notificationChannel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
            return false;
        }
        return true;
    }


    /**
     * 跳转到渠道设置
     *
     * @param channelId
     */
    public static void gotoChannelSetting(String channelId) {
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, KennieUtilInit.getApp().getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
        KennieUtilInit.getApp().startActivity(intent);
    }

    /**
     * 跳转到通知设置界面
     */
    public static void gotoNotificationSetting() {
        try {
            Intent intent = new Intent();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, KennieUtilInit.getApp().getPackageName());
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra("app_package", KennieUtilInit.getApp().getPackageName());
                intent.putExtra("app_uid", KennieUtilInit.getApp().getApplicationInfo().uid);
            } else {
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + KennieUtilInit.getApp().getPackageName()));
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            KennieUtilInit.getApp().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            IntentUtil.gotoAppSettings();
        }
    }
}
