package com.kennie.library.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresApi;

import java.io.File;

/**
 * @项目名 KennieUtils
 * @类名称 PhoneDeviceCompat
 * @类描述 手机设备管理类
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/13 0:03
 *
 * <p>
 * --判断设备是否是手机                                      {@link #isPhone()}
 * --判断是否是平板                                         {@link #isPad()}
 * --判断是否是模拟器                                       {@link #isEmulator()}
 * --判断设备是否rooted                                    {@link #isRooted()}
 * --判断设备ADB是否可用                                    {@link #isAdbEnabled()}
 * --判断是否有sim卡                                       {@link #isSim()}
 * --获取设备厂商                                          {@link #getManufacturer()}
 * --获取设备型号                                          {@link #getModel()}
 * --获取设备系统版本名称                                   {@link #getSDKVersionName()}
 * --获取设备系统版本码                                     {@link #getSDKVersionCode()}
 * --获取设备AndroidID                                    {@link #getAndroidID()}
 * </p>
 */
public class PhoneDeviceCompat {

    private PhoneDeviceCompat() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 判断设备是否是手机
     *
     * @return {@code true}: yes <br>{@code false}: no
     */
    public static boolean isPhone() {
        TelephonyManager tm = (TelephonyManager) KennieUtilInit.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }


    /**
     * 判断是否是平板
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isPad() {
        return (KennieUtilInit.getAppContext().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    /**
     * 判断是否是模拟器
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isEmulator() {
        boolean checkProperty = Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.toLowerCase().contains("vbox")
                || Build.FINGERPRINT.toLowerCase().contains("test-keys")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
        if (checkProperty) return true;

        String operatorName = "";
        TelephonyManager tm = (TelephonyManager) KennieUtilInit.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            String name = tm.getNetworkOperatorName();
            if (name != null) {
                operatorName = name;
            }
        }
        boolean checkOperatorName = operatorName.equalsIgnoreCase("android");
        if (checkOperatorName) return true;

        String url = "tel:" + "123456";
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        intent.setAction(Intent.ACTION_DIAL);
        boolean checkDial = intent.resolveActivity(KennieUtilInit.getAppContext().getPackageManager()) == null;
        if (checkDial) return true;

//        boolean checkDebuggerConnected = Debug.isDebuggerConnected();
//        if (checkDebuggerConnected) return true;

        return false;
    }


    /**
     * 判断设备是否rooted
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/",
                "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/",
                "/system/sbin/", "/usr/bin/", "/vendor/bin/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断设备ADB是否可用
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAdbEnabled() {
        return Settings.Secure.getInt(
                KennieUtilInit.getAppContext().getContentResolver(),
                Settings.Global.ADB_ENABLED, 0
        ) > 0;
    }


    /**
     * 是否有sim卡 即设备是否可以拨打电话等
     */
    public static boolean isSim() {
        TelephonyManager telephonyManager = (TelephonyManager) KennieUtilInit.getApp().getSystemService(Context.TELEPHONY_SERVICE);
        boolean result = true;
        switch (telephonyManager.getSimState()) {
            case TelephonyManager.SIM_STATE_ABSENT:
            case TelephonyManager.SIM_STATE_UNKNOWN:
                result = false;
                break;
        }
        return result;
    }


    /**
     * 获取设备厂商
     * <p>e.g. Xiaomi</p>
     *
     * @return the manufacturer of the product/hardware
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备型号
     * <p>e.g. MI2SC</p>
     *
     * @return the model of device
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }


    /**
     * 获取设备系统版本名称
     *
     * @return the version name of device's system
     */
    public static String getSDKVersionName() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取设备系统版本码
     *
     * @return version code of device's system
     */
    public static int getSDKVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备AndroidID
     *
     * @return the android id of device
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID() {
        String id = Settings.Secure.getString(
                KennieUtilInit.getAppContext().getContentResolver(),
                Settings.Secure.ANDROID_ID
        );
        if ("9774d56d682e549c".equals(id)) return "";
        return id == null ? "" : id;
    }

}
