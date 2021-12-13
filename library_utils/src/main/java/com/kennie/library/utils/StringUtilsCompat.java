package com.kennie.library.utils;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.Locale;

/**
 * @项目名 KennieUtils
 * @类名称 PhoneDeviceCompat
 * @类描述 字符串处理类
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/13 0:03
 *
 * <p>
 * --*********                                      {@link #}
 * </p>
 */
public class StringUtilsCompat {


    // ==================================get 获取方法* ==================================


    // ==================================format 格式化方法* ==================================

    /**
     * 格式化字符串（将指定字符串的某些字符替换成* 例如130****6368）
     *
     * @param sourceStr  原字符串
     * @param startIndex 开始索引
     * @param count      保留个数
     * @return String 130****6368
     */
    public static String formatSafeStr(String sourceStr, int startIndex, int count) {
        if (TextUtils.isEmpty(sourceStr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sourceStr.length(); i++) {
            if (i > (startIndex - 1) && i < sourceStr.length() - count) {
                sb.append("*");
            } else {
                sb.append(sourceStr.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 格式化时长(格式00:00:00)
     *
     * @param duration 时长，单位：秒
     */
    @NonNull
    public static String formatDurationStr(int duration) {
        return formatDurationStr(duration, null);
    }

    /**
     * 将时长转换成指定格式的字符串
     *
     * @param duration 时长，单位：秒
     */
    @NonNull
    public static String formatDurationStr(int duration, String format) {
        if (TextUtils.isEmpty(format)) format = "%02d:%02d:%02d";
        return String.format(Locale.ENGLISH, format, duration / 3600, duration % 3600 / 60, duration % 60);
    }

    // ==================================other 其它方法* ==================================

}
