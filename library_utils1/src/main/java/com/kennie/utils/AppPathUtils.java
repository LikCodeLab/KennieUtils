package com.kennie.utils;


import android.os.Build;
import android.os.Environment;

import java.io.File;

/**
 * @项目名 AppPathUtils
 * @类名称 AppPathUtilsCompat
 * @类描述 App路径相关工具类
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/11 10:48
 *
 * <p>
 * ==================================App 获取App内置相关方法* ==================================
 * --获取APP应用数据路径                                  {@link #getAppDataPath()}
 * --获取APP应用缓存路径                                  {@link #getAppCachePath()}
 * --获取APP应用文件路径                                  {@link #getAppFilesPath()}
 * --获取APP应用SP路径                                   {@link #getAppSpPath()}
 * --获取APP应用数据库路径                                {@link #getAppDbPath()}
 * --获取APP应用数据库路径                                {@link #getAppDbPath(String databaseName)}
 * ==================================AppExternal 获取App外置相关方法* ==================================
 * --获取外存储应用数据路径                                {@link #getAppExternalDataPath()}
 * --获取外存储应用缓存路径                                {@link #getAppExternalCachePath()}
 * --获取App内存储公共文件目录路径                          {@link #getAppExternalPublicPath()}
 * --获取App内存储公共文件目录路径(不同类别)                 {@link #getAppExternalPublicPath(String type)}
 * --获取外存储应用OBB路径                                 {@link #getAppExternalOBBPath()}
 * ==================================ExternalStorage 获取外置存储相关方法* ==================================
 * --获取外存储公共目录路径                                 {@link #getExternalStoragePath()}
 * --获取外存储公共文件目录路径(不同类别)                     {@link #getExternalStoragePublicPath(String type)}
 * ==================================Other 其它方法* ==================================
 * </p>
 */
public class AppPathUtils {

    // ==================================App 获取App内置相关方法* ==================================

    /**
     * 获取APP应用数据路径
     *
     * @return 路径 /data/user/0/package
     */
    public static String getAppDataPath() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return KennieUtilInit.getAppContext().getApplicationInfo().dataDir;
        }
        return KennieUtilInit.getAppContext().getDataDir().getAbsolutePath();
    }

    /**
     * 获取APP应用缓存路径
     *
     * @return 路径 /data/user/0/package/cache
     */
    public static String getAppCachePath() {
        return KennieUtilInit.getAppContext().getCacheDir().getAbsolutePath();
    }

    /**
     * 获取APP应用文件路径
     *
     * @return 路径 /data/user/0/package/files
     */
    public static String getAppFilesPath() {
        return KennieUtilInit.getAppContext().getFilesDir().getAbsolutePath();
    }

    /**
     * 获取APP应用SP路径
     *
     * @return 路径 /data/user/0/package/shared_prefs
     */
    public static String getAppSpPath() {
        return KennieUtilInit.getAppContext().getApplicationInfo().dataDir + File.separator + "shared_prefs";
    }

    /**
     * 获取APP应用数据库路径
     *
     * @return 路径 /data/user/0/package/databases
     */
    public static String getAppDbPath() {
        return KennieUtilInit.getAppContext().getApplicationInfo().dataDir + File.separator + "databases";
    }

    /**
     * 获取APP应用数据库路径
     *
     * @return 路径 /data/user/0/package/databases/name
     */
    public static String getAppDbPath(String databaseName) {
        return KennieUtilInit.getAppContext().getDatabasePath(databaseName).getAbsolutePath();
    }

    // ==================================AppExternal 获取App外置相关方法* ==================================

    /**
     * 获取外存储应用数据路径
     *
     * @return 路径 /storage/emulated/0/Android/data/package
     */
    public static String getAppExternalDataPath() {
        File externalCacheDir = KennieUtilInit.getAppContext().getExternalCacheDir();
        if (externalCacheDir == null) return "";
        return externalCacheDir.getParentFile().getAbsolutePath();
    }

    /**
     * 获取外存储应用缓存路径
     *
     * @return 路径 /storage/emulated/0/Android/data/package/cache
     */
    public static String getAppExternalCachePath() {
        return KennieUtilInit.getAppContext().getExternalCacheDir().getAbsolutePath();
    }

    /**
     * 获取App内存储公共文件目录路径
     *
     * @return 路径 /storage/emulated/0/Android/data/package/files
     */
    public static String getAppExternalPublicPath() {
        return KennieUtilInit.getAppContext().getExternalFilesDir(null).getAbsolutePath();
    }

    /**
     * 获取App内存储公共文件目录路径(不同类别)
     *
     * @param type – The type of storage directory to return. Should be one of
     *             DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES,
     *             DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS, DIRECTORY_PICTURES,
     *             DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS, DIRECTORY_DCIM,
     *             or DIRECTORY_DOCUMENTS. May not be null.
     * @return /storage/emulated/0/Android/data/package/files/DIRECTORY_PICTURES
     */
    public static String getAppExternalPublicPath(String type) {
        return KennieUtilInit.getAppContext().getExternalFilesDir(type).getAbsolutePath();
    }

    /**
     * 获取外存储应用OBB路径
     *
     * @return 路径 /storage/emulated/0/Android/obb/package
     */
    public static String getAppExternalOBBPath() {
        return KennieUtilInit.getAppContext().getObbDir().getAbsolutePath();
    }
    // ==================================ExternalStorage 获取外置存储相关方法* ==================================

    /**
     * 获取外存储公共目录路径
     *
     * @return 路径 /storage/emulated/0
     */
    public static String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取外存储公共文件目录路径(不同类别)
     *
     * @return 路径 /storage/emulated/0/DIRECTORY_MUSIC
     * @see Environment
     */
    /**
     * 获取外存储路径(不同类别)
     *
     * @param type – The type of storage directory to return. Should be one of
     *             DIRECTORY_MUSIC, DIRECTORY_PODCASTS, DIRECTORY_RINGTONES,
     *             DIRECTORY_ALARMS, DIRECTORY_NOTIFICATIONS, DIRECTORY_PICTURES,
     *             DIRECTORY_MOVIES, DIRECTORY_DOWNLOADS, DIRECTORY_DCIM,
     *             or DIRECTORY_DOCUMENTS. May not be null.
     * @return /storage/emulated/0/DIRECTORY_MUSIC Returns the File path for the directory. Note that this directory
     * may not yet exist, so you must make sure it exists before using it such as with File.mkdirs().
     */
    public static String getExternalStoragePublicPath(String type) {
        return Environment.getExternalStoragePublicDirectory(type).getAbsolutePath();
    }

    // ==================================other 其它方法* ==================================

    /**
     * 判断是否有外置存储
     *
     * @return true 是 false 否
     */
    public static boolean isExternalStorage() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
