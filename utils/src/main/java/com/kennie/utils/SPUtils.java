package com.kennie.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.kennie.utils.config.UtilInit;


/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：SPUtils
 * Date：2021/12/12 23:15
 * Desc：SharedPreferences封装工具类
 *
 * <p>
 * --存储String类型的值                              {@link #putString(String key, String value)}
 * --获取String类型的值                              {@link #getString(String key, String defValue)}
 * --存储Int类型的值                                 {@link #putInt(String key, int value)}
 * --获取Int类型的值                                 {@link #getInt(String key, int defValue)}
 * --存储Long类型的值                                {@link #putLong(String key, long value)}
 * --获取Long类型的值                                {@link #getLong(String key, long defValue)}
 * --存储Long类型的值                                {@link #putBoolean(String key, boolean value)}
 * --获取Long类型的值                                {@link #getBoolean(String key, boolean defValue)}
 * --判断key是否存在                                 {@link #contains(String key)}
 * --移除单个key                                    {@link #remove(String key_name)}
 * --移除单个key                                    {@link #remove(String file_name, String key_name)}
 * --清空全部key-value                              {@link #clear()}
 * --清空全部key-value                              {@link #clear(String file_name)}
 * </p>
 */
public class SPUtils {


    // ================================== putString/getString ==================================
    public static void putString(String key_name, String value) {
        getPreferences().edit().putString(key_name, value).apply();
    }

    public static void putString(String name, String key_name, String value) {
        getPreferences(name).edit().putString(key_name, value).apply();
    }

    public static boolean putStringForResult(String key_name, String value) {
        return getPreferences().edit().putString(key_name, value).commit();
    }

    public static boolean putStringForResult(String name, String key_name, String value) {
        return getPreferences(name).edit().putString(key_name, value).commit();
    }

    public static String getString(String key_name) {
        return getPreferences().getString(key_name, "");
    }

    public static String getString(String key_name, String defaultValue) {
        return getPreferences().getString(key_name, defaultValue);
    }

    public static String getString(String name, String key_name, String defaultValue) {
        return getPreferences(name).getString(key_name, defaultValue);
    }

    // ================================== putInt/getInt ==================================
    public static void putInt(String key_name, int value) {
        getPreferences().edit().putInt(key_name, value).apply();
    }

    public static void putInt(String name, String key_name, int value) {
        getPreferences(name).edit().putInt(key_name, value).apply();
    }

    public static boolean putIntForResult(String key_name, int value) {
        return getPreferences().edit().putInt(key_name, value).commit();
    }

    public static boolean putIntForResult(String name, String key_name, int value) {
        return getPreferences(name).edit().putInt(key_name, value).commit();
    }

    public static int getInt(String key_name) {
        return getPreferences().getInt(key_name, 0);
    }

    public static int getInt(String key_name, int defaultValue) {
        return getPreferences().getInt(key_name, defaultValue);
    }

    public static int getInt(String name, String key_name, int defaultValue) {
        return getPreferences(name).getInt(key_name, defaultValue);
    }

    // ================================== putLong/getLong ==================================
    public static void putLong(String key_name, long value) {
        getPreferences().edit().putLong(key_name, value).apply();
    }

    public static void putLong(String name, String key_name, long value) {
        getPreferences(name).edit().putLong(key_name, value).apply();
    }

    public static boolean putLongForResult(String key_name, long value) {
        return getPreferences().edit().putLong(key_name, value).commit();
    }

    public static boolean putLongForResult(String name, String key_name, long value) {
        return getPreferences(name).edit().putLong(key_name, value).commit();
    }

    public static long getLong(String key_name) {
        return getPreferences().getLong(key_name, 0L);
    }

    public static long getLong(String key_name, long defaultValue) {
        return getPreferences().getLong(key_name, defaultValue);
    }

    public static long getLong(String name, String key_name, long defaultValue) {
        return getPreferences(name).getLong(key_name, defaultValue);
    }

    // ================================== putFloat/getFloat ==================================
    public static void putFloat(String key_name, float value) {
        getPreferences().edit().putFloat(key_name, value).apply();
    }

    public static void putFloat(String name, String key_name, float value) {
        getPreferences(name).edit().putFloat(key_name, value).apply();
    }

    public static boolean putFloatForResult(String key_name, float value) {
        return getPreferences().edit().putFloat(key_name, value).commit();
    }

    public static boolean putFloatForResult(String name, String key_name, float value) {
        return getPreferences(name).edit().putFloat(key_name, value).commit();
    }

    public static float getFloat(String key_name) {
        return getPreferences().getFloat(key_name, 0f);
    }

    public static float getFloat(String key_name, float defaultValue) {
        return getPreferences().getFloat(key_name, defaultValue);
    }

    public static float getFloat(String name, String key_name, float defaultValue) {
        return getPreferences(name).getFloat(key_name, defaultValue);
    }


    // ================================== putBoolean/getBoolean ==================================
    public static void putBoolean(String key_name, boolean value) {
        getPreferences().edit().putBoolean(key_name, value).apply();
    }

    public static void putBoolean(String name, String key_name, boolean value) {
        getPreferences(name).edit().putBoolean(key_name, value).apply();
    }

    public static boolean putBooleanForResult(String key_name, boolean value) {
        return getPreferences().edit().putBoolean(key_name, value).commit();
    }

    public static boolean putBooleanForResult(String name, String key_name, boolean value) {
        return getPreferences(name).edit().putBoolean(key_name, value).commit();
    }


    public static boolean getBoolean(String key_name) {
        return getPreferences().getBoolean(key_name, false);
    }

    /**
     * 获取Boolean类型的值
     *
     * @param key_name key
     * @param defValue 默认值
     * @return
     */
    public static boolean getBoolean(String key_name, boolean defValue) {
        return getPreferences().getBoolean(key_name, defValue);
    }

    /**
     * 获取Boolean类型的值
     *
     * @param file_name
     * @param key_name  key
     * @param defValue  默认值
     * @return
     */
    public static boolean getBoolean(String file_name, String key_name, boolean defValue) {
        return getPreferences(file_name).getBoolean(key_name, defValue);
    }


    /**
     * 判断key是否存在
     *
     * @param key_name 指定key名称
     * @return
     */
    public static boolean contains(String key_name) {
        return getPreferences().contains(key_name);
    }

    /**
     * 判断key是否存在
     *
     * @param file_name
     * @param key_name  指定key名称
     * @return
     */
    public static boolean contains(String file_name, String key_name) {
        return getPreferences(file_name).contains(key_name);
    }

    /**
     * 移除单个key
     *
     * @param key_name 指定key名称
     */
    public static void remove(String key_name) {
        getPreferences().edit().remove(key_name).apply();
    }

    /**
     * 移除单个key
     *
     * @param file_name data/data/<packagename>/shared_prefs下的文件名
     * @param key_name  指定key名称
     */
    public static void remove(String file_name, String key_name) {
        getPreferences(file_name).edit().remove(key_name).apply();
    }


    /**
     * 清空全部key-value
     */
    public static void clear() {
        Editor editor = getPreferences().edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 清空全部key-value
     *
     * @param file_name data/data/<packagename>/shared_prefs下的文件名
     */
    public static void clear(String file_name) {
        Editor editor = getPreferences(file_name).edit();
        editor.clear();
        editor.apply();
    }


    /**
     * 获取SharedPreferences实例对象（默认）
     *
     * @return
     */
    private static SharedPreferences getPreferences() {
        return getPreferences(null);
    }

    /**
     * 获取SharedPreferences实例对象（默认）
     *
     * @param file_name data/data/<packagename>/shared_prefs下的文件名
     * @return 返回实例对象
     */
    private static SharedPreferences getPreferences(String file_name) {
        if (TextUtils.isEmpty(file_name)) {
            return PreferenceManager.getDefaultSharedPreferences(UtilInit.getAppContext());
        } else {
            return UtilInit.getAppContext().getSharedPreferences(file_name, Context.MODE_PRIVATE);
        }
    }

}
