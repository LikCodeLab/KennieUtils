package com.kennie.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @项目名 KennieUtils
 * @类名称 SpUtils
 * @类描述 配置存储相关工具类
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/12 23:15
 *
 * <p>
 * --存储String类型的值                              {@link #putString(String key, String value)}
 * --获取String类型的值                              {@link #getString(String key, String defValue)}
 * --存储Int类型的值                                 {@link #putInt(String key, int value)}
 * --获取Int类型的值                                 {@link #getInt(String key, int defValue)}
 * --存储Long类型的值                                {@link #putLong(String key, long value)}
 * --获取Long类型的值                                {@link #getLong(String key, long defValue)}
 * --存储Long类型的值                                {@link #putBoolean(String key, boolean value)}
 * --获取Long类型的值                                {@link #getBoolean(String key, Boolean defValue)}
 * --判断key是否存在                                 {@link #contains(String key)}
 * --删除单个key                                    {@link #delete(String key)}
 * --清空全部key                                    {@link #clear()}
 * </p>
 */
public class SpUtils {

    private static final String DEFAULT_FILE_NAME = "app_config";


    /**
     * 获取SharedPreferences实例对象
     */
    private static SharedPreferences getSharedPreferences() {
        return KennieUtilInit.getAppContext().getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 存储String类型的值
     *
     * @param key   key值
     * @param value 要存储的String值
     */
    public static void putString(String key, String value) {
        getSharedPreferences().edit().putString(key, value).apply();
    }

    /**
     * 获取String类型的值
     *
     * @param key      key
     * @param defValue 默认值
     * @return
     */
    public static String getString(String key, String defValue) {
        return getSharedPreferences().getString(key, defValue);
    }

    /**
     * 存储Int类型的值
     *
     * @param key   key
     * @param value 要存储的Int值
     */
    public static void putInt(String key, int value) {
        getSharedPreferences().edit().putInt(key, value).apply();
    }

    /**
     * 获取Int类型的值
     *
     * @param key      key
     * @param defValue 默认值
     * @return
     */
    public static int getInt(String key, int defValue) {
        return getSharedPreferences().getInt(key, defValue);
    }


    /**
     * 存储Long类型的值
     *
     * @param key   key
     * @param value 要存储的long值
     */
    public static void putLong(String key, long value) {
        getSharedPreferences().edit().putLong(key, value).apply();
    }


    /**
     * 获取Long类型的值
     *
     * @param key      key
     * @param defValue 默认值
     * @return
     */
    public static long getLong(String key, long defValue) {
        return getSharedPreferences().getLong(key, defValue);
    }


    /**
     * 存储Boolean类型的值
     *
     * @param key   key
     * @param value 要存储Boolean值
     */
    public static void putBoolean(String key, boolean value) {
        getSharedPreferences().edit().putBoolean(key, value).apply();
    }

    /**
     * 获取Boolean类型的值
     *
     * @param key      key
     * @param defValue 默认值
     * @return
     */
    public static boolean getBoolean(String key, Boolean defValue) {
        return getSharedPreferences().getBoolean(key, defValue);
    }


    /**
     * 判断key是否存在
     *
     * @param key 键值
     * @return {@code true}:存在<br>{@code false}:不存在
     */
    public static boolean contains(String key) {
        return getSharedPreferences().contains(key);
    }

    /**
     * 删除单个key
     *
     * @param key key值
     */
    public static void delete(String key) {
        getSharedPreferences().edit().remove(key).apply();
    }


    /**
     * 清空全部key
     */
    public static void clear() {
        getSharedPreferences().edit().clear().apply();
    }

}
