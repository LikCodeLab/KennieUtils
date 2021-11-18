package com.kennie.library.utils.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @项目名 KennieUtils
 * @类名称 DateUtil
 * @类描述 日期 处理类
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 20:41
 */
public class DateUtil {

    private static final SimpleDateFormat DATE_FORMAT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);


    /**
     * 获取当前日期与时间(yyyy-MM-dd HH:mm:ss)
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime() {
        return DATE_FORMAT_DATETIME.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @return yyyy-MM-dd
     */
    public static String getDate() {
        return DATE_FORMAT_DATE.format(new Date(System.currentTimeMillis()));
    }


    /**
     * 获取当前时间(HH:mm:ss)
     *
     * @return HH:mm:ss
     */
    public static String getTime() {
        return DATE_FORMAT_TIME.format(new Date(System.currentTimeMillis()));
    }


    /**
     * 获取当前未格式化时间
     *
     * @return currentTimeMillis
     */
    public static Long getTimeMillis() {
        return System.currentTimeMillis();
    }


    /**
     * 根据 日期格式 获取当前日期
     *
     * @param pattern 日期格式（yyyy-MM-dd）
     * @return 格式化日期
     */
    public static String formatDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 根据 时间戳 日期格式 获取当前日期
     *
     * @param time    时间戳 System.currentTimeMillis()
     * @param pattern 日期格式（yyyy-MM-dd）
     * @return 格式化日期
     */
    public static String formatDate(long time, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(time);

    }


    /**
     * 根据 日期 日期格式 获取当前日期
     *
     * @param date    日期 Date
     * @param pattern 日期格式（yyyy-MM-dd）
     * @return 格式化日期
     */
    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(date);
    }


}
