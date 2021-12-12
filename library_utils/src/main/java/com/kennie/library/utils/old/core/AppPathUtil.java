package com.kennie.library.utils.old.core;

import android.os.Build;
import android.os.Environment;

import com.kennie.library.utils.KennieUtilInit;

import java.io.File;

/**
 * @项目名 KennieUtils
 * @类名称 AppPathUtil
 * @类描述 App路径处理类
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/16 23:09
 */
public class AppPathUtil {

    /**
     * getAppDataPath                : 获取APP应用数据路径
     * getAppCachePath               : 获取APP应用缓存路径
     * getAppDbPath                  : 获取APP应用数据库路径
     * getAppDbPath()                : 获取APP应用数据库路径
     * getAppFilesPath               : 获取APP应用文件路径
     * getAppSpPath                  : 获取APP应用SP路径
     * <p>
     * getExternalStoragePath        : 获取外存储路径
     * getExternalPicturesPath       : 获取外存储图片路径
     * getExternalDCIMPath           : 获取外存储相机图片路径
     * getExternalDocumentsPath      : 获取外存储文档路径
     * getExternalDownloadsPath      : 获取外存储下载路径
     * <p>
     * getAppExternalDataPath        : 获取外存储应用数据路径
     * getAppExternalCachePath       : 获取外存储应用缓存路径
     * getAppExternalFilesPath       : 获取外存储应用文件路径
     * getAppExternalPicturesPath    : 获取外存储应用图片路径
     * getAppExternalDCIMPath        : 获取外存储应用相机图片路径
     * getAppExternalDownloadPath    : 获取外存储应用下载路径
     * getAppExternalDocumentsPath   : 获取外存储应用文档路径
     * <p>
     * getAppExternalOBBPath         : 获取外存储应用OBB路径
     */

    private static final char SEPARATOR_CHAR = File.separatorChar;

    public static final String SEPARATOR = File.separator;


    /**
     * 获取APP应用数据路径
     *
     * @return 路径 /data/user/0/package
     */
    public static String getAppDataPath() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return KennieUtilInit.getApp().getApplicationInfo().dataDir;
        }
        return KennieUtilInit.getApp().getDataDir().getAbsolutePath();
    }

    /**
     * 获取APP应用缓存路径
     *
     * @return 路径 /data/user/0/package/cache
     */
    public static String getAppCachePath() {
        return KennieUtilInit.getApp().getCacheDir().getAbsolutePath();
    }

    /**
     * 获取APP应用数据库路径
     *
     * @return 路径 /data/user/0/package/databases
     */
    public static String getAppDbPath() {
        return KennieUtilInit.getApp().getApplicationInfo().dataDir + "/databases";
    }

    /**
     * 获取APP应用数据库路径
     *
     * @return 路径 /data/user/0/package/databases/name
     */
    public static String getAppDbPath(String databaseName) {
        return KennieUtilInit.getApp().getDatabasePath(databaseName).getAbsolutePath();
    }

    /**
     * 获取APP应用文件路径
     *
     * @return 路径 /data/user/0/package/files
     */
    public static String getAppFilesPath() {
        return KennieUtilInit.getApp().getFilesDir().getAbsolutePath();
    }

    /**
     * 获取APP应用SP路径
     *
     * @return 路径 /data/user/0/package/shared_prefs
     */
    public static String getAppSpPath() {
        return KennieUtilInit.getApp().getApplicationInfo().dataDir + "/shared_prefs";
    }

    /**
     * 获取外存储路径
     *
     * @return 路径 /storage/emulated/0
     */
    public static String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取外存储图片路径
     *
     * @return 路径 /storage/emulated/0/Pictures
     */
    public static String getExternalPicturesPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    }

    /**
     * 获取外存储相机图片路径
     *
     * @return 路径 /storage/emulated/0/DCIM
     */
    public static String getExternalDCIMPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
    }

    /**
     * 获取外存储文档路径
     *
     * @return 路径 /storage/emulated/0/Documents
     */
    public static String getExternalDocumentsPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
    }

    /**
     * 获取外存储下载路径
     *
     * @return 路径 /storage/emulated/0/Download
     */
    public static String getExternalDownloadsPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }

    /**
     * 获取外存储应用数据路径
     *
     * @return 路径 /storage/emulated/0/Android/data/package
     */
    public static String getAppExternalDataPath() {
        File externalCacheDir = KennieUtilInit.getApp().getExternalCacheDir();
        if (externalCacheDir == null) return "";
        return externalCacheDir.getParentFile().getAbsolutePath();
    }

    /**
     * 获取外存储应用缓存路径
     *
     * @return 路径 /storage/emulated/0/Android/data/package/cache
     */
    public static String getAppExternalCachePath() {
        return KennieUtilInit.getApp().getExternalCacheDir().getAbsolutePath();
    }

    /**
     * 获取外存储应用文件路径
     *
     * @return 路径 /storage/emulated/0/Android/data/package/files
     */
    public static String getAppExternalFilesPath() {
        return KennieUtilInit.getApp().getExternalFilesDir(null).getAbsolutePath();
    }

    /**
     * 获取外存储应用图片路径
     *
     * @return 路径 /storage/emulated/0/Android/data/package/files/Pictures
     */
    public static String getAppExternalPicturesPath() {
        return KennieUtilInit.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    }

    /**
     * 获取外存储应用相机图片路径
     *
     * @return 路径 /storage/emulated/0/Android/data/package/files/DCIM
     */
    public static String getAppExternalDCIMPath() {
        return KennieUtilInit.getApp().getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath();
    }

    /**
     * 获取外存储应用下载路径
     *
     * @return 路径 /storage/emulated/0/Android/data/package/files/Download
     */
    public static String getAppExternalDownloadPath() {
        return KennieUtilInit.getApp().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }

    /**
     * 获取外存储应用文档路径
     *
     * @return 路径 /storage/emulated/0/Android/data/package/files/Documents
     */
    public static String getAppExternalDocumentsPath() {
        return KennieUtilInit.getApp().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
    }

    /**
     * 获取外存储应用OBB路径
     *
     * @return 路径 /storage/emulated/0/Android/obb/package
     */
    public static String getAppExternalOBBPath() {
        return KennieUtilInit.getApp().getObbDir().getAbsolutePath();
    }


    /**
     * 判断是否有外置存储
     *
     * @return true 是 false 否
     */
    public static boolean isExternalStorage() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 外部公共目录
     *
     * @param type 文件类型 The type of storage directory to return. Should be one of
     *             DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES,
     *             DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS, DIRECTORY_PICTURES,
     *             DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS,
     *             DIRECTORY_DCIM, or DIRECTORY_DOCUMENTS. May not be null.
     * @return 路径
     */
    public static File getExternalStoragePublicDirectory(String type) {
        return Environment.getExternalStoragePublicDirectory(type);
    }

    /**
     * 根据类型获取外部公共目录
     *
     * @param type 文件类型 The type of storage directory to return. Should be one of
     *             DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES,
     *             DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS, DIRECTORY_PICTURES,
     *             DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS,
     *             DIRECTORY_DCIM, or DIRECTORY_DOCUMENTS. May not be null.
     * @return 路径
     */
    public static String getExternalStoragePublicPath(String type) {
        return Environment.getExternalStoragePublicDirectory(type).getAbsolutePath();
    }

    /**
     * 获取外部沙盒缓存目录
     * /storage/emulated/0/Android/data/xxx/cache
     *
     * @return
     */
    public static String getExternalSandBoxCachePath() {
        return KennieUtilInit.getApp().getExternalCacheDir().getAbsolutePath();
    }


    /**
     * 获取外部沙盒文件根目录
     * (/storage/emulated/0/Android/data/xxx/files)
     *
     * @return
     */
    public static String getExternalSandBoxFilesPath() {
        return getExternalSandBoxPath(null);
    }

    /**
     * 根据类型获取外部沙盒文件目录
     *
     * @param type
     * @return
     */
    public static String getExternalSandBoxPath(String type) {
        return KennieUtilInit.getApp().getExternalFilesDir(type).getAbsolutePath();
    }

    /**
     * 获取内部沙盒缓存目录
     * /data/data/xxx/cache
     *
     * @return
     */
    public static String getInternalCachePath() {
        return KennieUtilInit.getApp().getCacheDir().getAbsolutePath();
    }


    /**
     * 获取内部沙盒文件目录
     * /data/data/xxx/files
     *
     * @return
     */
    public static String getInternalFilesPath() {
        return KennieUtilInit.getApp().getFilesDir().getAbsolutePath();
    }

}
