package com.kennie.library.utils.old.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.kennie.library.utils.KennieUtilInit;

import java.util.Set;

/**
 * @项目名 KennieUtils
 * @类名称 SPUtil
 * @类描述 配置存储相关工具类
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 0:54
 */
public class SPUtil {

    /**
     * > - **配置存储相关→[SPUtil.java][SPUtil]**
     * ```
     * init         : 初始化，设置存储文件名
     * contains     : 判断键值是否存在
     * setSetting   : 存储配置
     * getString    : 读取字符串配置
     * getBoolean   : 读取Boolean配置
     * getInt       : 读取int配置
     * getLong      : 读取long配置
     * getFloat     : 读取float配置
     * getStringSet : 读取Set<String>配置
     * remove       : 删除配置
     * clear        : 清空配置
     * ```
     */

    private static final String SP_DEFAULT_FILE = "app_config"; // 存储文件名

    private static String SP_FILE_NAME = SP_DEFAULT_FILE;

    /**
     * 初始化，设置存储文件名
     *
     * @param sharedFileName 存储文件名
     */
    public static void init(String sharedFileName) {
        SP_FILE_NAME = sharedFileName;
    }

    /**
     * 获取SharedPreferences实例对象
     */
    private static SharedPreferences getSharedPreferences() {
        return KennieUtilInit.getApp().getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 判断键值是否存在
     *
     * @param key 键值
     * @return {@code true}:存在<br>{@code false}:不存在
     */
    public static boolean contains(String key) {
        return getSharedPreferences().contains(key);
    }

    /**
     * 存储字符串
     *
     * @param key   键值
     * @param value 字符串
     */
    public static void setSetting(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value).apply();
    }

    /**
     * 读取字符串
     *
     * @param key      键值
     * @param defValue 默认返回字符串
     * @return 字符串
     */
    public static String getString(String key, String defValue) {
        return getSharedPreferences().getString(key, defValue);
    }

    /**
     * 读取字符串
     *
     * @param key 键值
     * @return 字符串
     */
    public static String getString(String key) {
        return getString(key, null);
    }

    /**
     * 存储布尔类型
     *
     * @param key   键值
     * @param value boolean
     */
    public static void setSetting(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(key, value).apply();
    }

    /**
     * 读取布尔类型
     *
     * @param key      键值
     * @param defValue 默认返回值
     * @return boolean
     */
    public static boolean getBoolean(String key, boolean defValue) {
        return getSharedPreferences().getBoolean(key, defValue);
    }

    /**
     * 读取布尔类型
     *
     * @param key 键值
     * @return boolean
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * 存储int
     *
     * @param key   键值
     * @param value int
     */
    public static void setSetting(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(key, value).apply();
    }

    /**
     * 读取int
     *
     * @param key      键值
     * @param defValue 默认返回值
     * @return int
     */
    public static int getInt(String key, int defValue) {
        return getSharedPreferences().getInt(key, defValue);
    }

    /**
     * 读取int
     *
     * @param key 键值
     * @return int
     */
    public static int getInt(Context context, String key) {
        return getInt(key, 0);
    }

    /**
     * 存储long
     *
     * @param key   键值
     * @param value long
     */
    public static void setSetting(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(key, value).apply();
    }

    /**
     * 读取long
     *
     * @param key      键值
     * @param defValue 默认返回值
     * @return long
     */
    public static long getLong(String key, long defValue) {
        return getSharedPreferences().getLong(key, defValue);
    }

    /**
     * 读取long
     *
     * @param key 键值
     * @return long
     */
    public static long getLong(String key) {
        return getLong(key, 0);
    }

    /**
     * 存储float
     *
     * @param key   键值
     * @param value float
     */
    public static void setSetting(String key, float value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putFloat(key, value).apply();
    }

    /**
     * 读取float
     *
     * @param key      键值
     * @param defValue 默认返回值
     * @return float
     */
    public static float getFloat(String key, float defValue) {
        return getSharedPreferences().getFloat(key, defValue);
    }

    /**
     * 读取float
     *
     * @param key 键值
     * @return float
     */
    public static float getFloat(String key) {
        return getSharedPreferences().getFloat(key, 0);
    }

    /**
     * 存储Set<String>
     *
     * @param key   键值
     * @param value Set<String>
     */
    public static void setSetting(String key, Set<String> value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putStringSet(key, value).apply();
    }

    /**
     * 读取Set<String>
     *
     * @param key      键值
     * @param defValue 默认返回值
     * @return Set<String>
     */
    public static Set<String> getStringSet(String key, Set<String> defValue) {
        return getSharedPreferences().getStringSet(key, defValue);
    }

    /**
     * 读取Set<String>
     *
     * @param key 键值
     * @return Set<String>
     */
    public static Set<String> getStringSet(String key) {
        return getStringSet(key, null);
    }

    /**
     * 删除配置
     *
     * @param key 键值
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.remove(key).apply();
    }

    /**
     * 清空配置
     */
    public static void clear() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear().apply();
    }
}
