package com.kennie.library.utils.core;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @项目名 KennieUtils
 * @类名称 DateUtil
 * @类描述 日期时间格式化处理类
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 20:41
 */
public class DateTimeUtil {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_PATTERN_YMD = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    // ==================================get 获取方法* ==================================

    /**
     * 获取当前日期
     *
     * @return 返回String类型 格式(yyyy-MM-dd HH:mm:ss)
     */
    public static String getDate() {
        return new SimpleDateFormat(DEFAULT_DATE_PATTERN, Locale.CHINA).format(new Date(timeMillis()));
    }

    /**
     * 获取当前日期
     *
     * @param pattern 格式如： yyyy-MM-dd HH:mm:ss
     * @return 返回指定模式的String类型
     * @see com.kennie.library.utils.config.DateTimePatternConstants
     */
    public static String getDate(String pattern) {
        return new SimpleDateFormat(pattern, Locale.CHINA).format(new Date(timeMillis()));
    }

    /**
     * 根据间隔年份数，获取当前年前后的日期
     *
     * @param date          日期 格式如 Date
     * @param intervalYears 间隔年份数 如 1 -1
     * @return
     */
    public static Date getDateByIntervalYears(Date date, int intervalYears) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, intervalYears);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 根据间隔月份数，获取当前月前后的日期
     *
     * @param date           日期 格式如 Date
     * @param intervalMonths 间隔月份数 如 1 -1
     * @return 返回日期Date
     */
    public static Date getDateByIntervalMonths(Date date, int intervalMonths) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, intervalMonths);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 获取前一个月份
     *
     * @param pattern 格式如 MM
     * @return 返回指定模式的String类型
     */
    public static String getBeforeMonth(String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        return sdf.format(calendar.getTime());
    }

    /**
     * 根据间隔天数，获取当前日前后的日期
     *
     * @param date         日期 格式如 Date
     * @param intervalDays 间隔天数 如 1 -1
     * @return 返回日期Date
     */
    public static Date getDateByIntervalDays(Date date, int intervalDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, intervalDays);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 获取前一天
     *
     * @param pattern 格式如 yyyy-MM-dd HH:mm:ss
     * @return 返回指定模式的String类型
     */
    public static String getBeforeDate(String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        return sdf.format(calendar.getTime());
    }


    /**
     * 获取两个日期差值
     * 计算这两个日期的差值天数(日期值小的参数在前)
     *
     * @param startDate 前一个日期
     * @param endDate   后一个日期
     * @return 返回两个日期的差值天数(日期值小的参数在前)
     */
    public static int getIntervalDays(Date startDate, Date endDate) {
        long start_date = startDate.getTime();
        long end_date = endDate.getTime();
        long interval = end_date - start_date;
        return (int) (interval / (1000 * 60 * 60 * 24));
    }

    /**
     * 获取两个日期差值
     * 计算这两个日期的差值分钟数(日期值小的参数在前)
     *
     * @param startDate 前一个日期
     * @param endDate   后一个日期
     * @return 返回两个日期的差值分钟数(日期值小的参数在前)
     */
    public static int getIntervalMinutes(Date startDate, Date endDate) {
        long start_date = startDate.getTime();
        long end_date = endDate.getTime();
        long interval = end_date - start_date;
        return (int) (interval / (1000 * 60));
    }

    // ==================================format 格式化方法* ==================================

    /**
     * 格式化日期
     *
     * @param time    时间戳 如：System.currentTimeMillis()
     * @param pattern 日期模式 如：yyyy-MM-dd
     * @return 返回指定模式的String类型
     */
    public static String formatDate(long time, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(time);
    }


    /**
     * 格式化日期
     *
     * @param date    日期 如：Date
     * @param pattern 日期模式 如：yyyy-MM-dd
     * @return 返回指定模式的String类型
     */
    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(date);
    }

    // ==================================parse 解析方法* ==================================

    /**
     * 转换日期（将一个日期字符串转化成另一种日期模式的字符串）
     *
     * @param date_str       需要格式化的日期str 如：2021-11-11
     * @param target_pattern 目标日期模式
     * @return 返回指定模式的String类型
     */
    public static String parseDate(String date_str, String target_pattern) {
        String format = DateTimeUtil.getPattern(date_str);
        if (format == null) {
            return null;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
            Date target_date = simpleDateFormat.parse(date_str);
            simpleDateFormat = new SimpleDateFormat(target_pattern, Locale.CHINA);
            return simpleDateFormat.format(target_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // ==================================other 其它方法* ==================================

    /**
     * 获取当前未格式化时间戳
     *
     * @return currentTimeMillis
     */
    public static Long timeMillis() {
        return System.currentTimeMillis();
    }


    /**
     * 根据某年的第n周，获取本周的起止时间
     *
     * @param year 年份
     * @param week 周
     * @return 返回Map<String, String>类型
     */
    public static Map<String, String> weekToDayFormatter(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        //设置该年份的开始日期：第一个月的第一天
        calendar.set(year, 0, 1);
        //计算出第一周还剩几天：+1是因为1号是1天
        int dayOfWeek = 7 - calendar.get(Calendar.DAY_OF_WEEK) + 1;
        //周数减去第一周再减去要得到的周
        week = week - 2;
        //计算起止日期
        calendar.add(Calendar.DAY_OF_YEAR, week * 7 + dayOfWeek);

        String startDay = new SimpleDateFormat(DEFAULT_DATE_PATTERN_YMD, Locale.CHINA).format(DateTimeUtil.getDateByIntervalDays(calendar.getTime(), 1));
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        String endDay = new SimpleDateFormat(DEFAULT_DATE_PATTERN_YMD, Locale.CHINA).format(calendar.getTime());

        HashMap<String, String> map = new HashMap<>();
        map.put("startDay", startDay);
        map.put("endDay", endDay);
        return map;

    }


    /**
     * 获取日期模式
     *
     * @param date_str 日期str
     * @return 返回日期模式的String类型
     */
    public static String getPattern(String date_str) {
        if (!TextUtils.isEmpty(date_str)) {
            if (date_str.matches("[1-9][0-9][0-9][0-9][-][0-9][0-9][-][0-9][0-9]")) {
                return "yyyy-MM-dd";
            }
            if (date_str.matches("[1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
                return "yyyyMMdd";
            }
            if (date_str.matches("[1-9][0-9][0-9][0-9][-][0-9][0-9]")) {
                return "yyyy-MM";
            }
            if (date_str.matches("[1-9][0-9][0-9][0-9][0-9][0-9]")) {
                return "yyyyMM";
            }
            if (date_str.matches("[1-9][0-9][0-9][0-9]")) {
                return "yyyy";
            }
            if (date_str.matches("[1-9][0-9][0-9][0-9][-][0-9][0-9][-][0-9][0-9][\\s][0-9][0-9][:][0-9][0-9][:][0-9][0-9]")) {
                return "yyyy-MM-dd HH:mm:ss";
            }
            if (date_str.matches("[1-9][0-9][0-9][0-9][-][0-9][0-9][-][0-9][0-9][\\s][0-9][0-9][-][0-9][0-9][-][0-9][0-9]")) {
                return "yyyy-MM-dd HH-mm-ss";
            }
        }
        return null;
    }
}
