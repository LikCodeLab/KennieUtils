package com.kennie.library.utils.config;

/**
 * @项目名 KennieUtils
 * @类名称 DatePatternConstants
 * @类描述 日期模式常量
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/26 1:10
 */
public class DatePatternConstants {

    // ==================================yyyy-MM-dd HH:mm:ss 相关Pattern==================================
    /**
     * yyyy-MM-dd HH:mm:ss 比如：2020-05-23 17:06:30
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd-HH:mm:ss 比如：2020-05-23-17:06:30
     */
    public static final String YYYY_MM_DD__HH_MM_SS = "yyyy-MM-dd-HH:mm:ss";

    /**
     * yyyyMMddHHmmss 比如：20200523170630
     */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * yyyy/MM/dd HH:mm:ss 比如：2020/05/23 17:06:30
     */
    public static final String YYYY_MM_DD_HH_MM_SS_EN = "yyyy/MM/dd HH:mm:ss";

    /**
     * yyyy年MM月dd日 HH:mm:ss 比如：2020年05月23日 17:06:30
     */
    public static final String YYYY_MM_DD_HH_MM_SS_CN = "yyyy年MM月dd日 HH:mm:ss";

    /**
     * yyyy年MM月dd日 HH时mm分ss秒 比如：2020年05月23日 17时06分30秒
     */
    public static final String YYYY_MM_DD_HH_MM_SS_CN_ALL = "yyyy年MM月dd日 HH时mm分ss秒";


    /**
     * yyyy-MM-dd HH:mm 比如：2020-05-23 17:06
     */
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * yyyyMMddHHmm 比如：202005231706
     */
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";

    /**
     * yyyy/MM/dd HH:mm 比如：2020/05/23 17:06
     */
    public static final String YYYY_MM_DD_HH_MM_EN = "yyyy/MM/dd HH:mm";


    /**
     * MM-dd HH:mm:ss 比如：05-23 17:06:30
     */
    public static final String MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";

    /**
     * MM月dd日 HH:mm:ss 比如：05月23日 17:06:30
     */
    public static final String MM_DD_HH_MM_SS_CN = "MM月dd日 HH:mm:ss";

    /**
     * MM-dd HH:mm 比如：05-23 17:06
     */
    public static final String MM_DD_HH_MM = "MM-dd HH:mm";

    /**
     * MM月dd日 HH:mm 比如：05月23日 17:06
     */
    public static final String MM_DD_HH_MM_CN = "MM月dd日 HH:mm";


    /**
     * yyyy年MM月dd日 hh:mm:ss a 比如：2020年05月23日 05:06:30 下午  如果需要 显示PM 需要设置 Locale.ENGLISH
     */
    public static final String YYYY_MM_DD_HH_MM_SS_A_CN = "yyyy年MM月dd日 hh:mm:ss a";

    /**
     * yyyy年MM月dd日 hh时mm分ss秒 a 比如：2020年05月23日 17时06分30秒 下午  如果需要 显示PM 需要设置 Locale.ENGLISH
     */
    public static final String YYYY_MM_DD_HH_MM_SS_A_CN_ALL = "yyyy年MM月dd日 hh时mm分ss秒 a";


    // ==================================yyyy-MM-dd相关Pattern==================================

    /**
     * yyyy-MM-dd 比如：  2020-05-23
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * yyyyMMdd  比如：  20200523
     */
    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * yyyy/MM/dd  比如：  2020/05/23
     */
    public static final String YYYY_MM_DD_EN = "yyyy/MM/dd";

    /**
     * yyyy年MM月dd日  比如： 2020年05月23日
     */
    public static final String YYYY_MM_DD_CN = "yyyy年MM月dd日";

    /**
     * yyyy.MM.dd  比如：2020.05.23
     */
    public static final String YYYY_MM_DD_POINT = "yyyy.MM.dd";

    /**
     * yy/MM/dd  比如：20/05/23
     */
    public static final String YY_MM_DD_EN = "yy/MM/dd";

    /**
     * MM/dd/yy  比如：05/23/20
     */
    public static final String MM_DD_YY_EN = "MM/dd/yy";

    /**
     * yyyy-MM-dd E  比如：2020-05-23 星期六
     */
    public static final String YYYY_MM_DD_E = "yyyy-MM-dd E";

    /**
     * yyyy  比如：2020
     */
    public static final String YYYY = "yyyy";

    /**
     * yyyy-MM  比如：2020-05
     */
    public static final String YYYY_MM = "yyyy-MM";

    /**
     * yyyyMM  比如：202005
     */
    public static final String YYYYMM = "yyyyMM";

    /**
     * yyyy/MM  比如：2020/05
     */
    public static final String YYYY_MM_EN = "yyyy/MM";

    /**
     * yyyy年MM月  比如：2020年05月
     */
    public static final String YYYY_MM_CN = "yyyy年MM月";

    /**
     * MM-dd  比如：05-23
     */
    public static final String MM_DD = "MM-dd";

    /**
     * MMdd  比如：0523
     */
    public static final String MMDD = "MMdd";

    /**
     * MM/dd  比如：05/23
     */
    public static final String MM_DD_EN = "MM/dd";

    /**
     * MM月dd日  比如：05月23日
     */
    public static final String MM_DD_CN = "MM月dd日";


    // ==================================HH:mm:ss 相关Pattern==================================

    /**
     * HH:mm:ss  比如：17:26:30
     */
    public static String HH_MM_SS = "HH:mm:ss";

    /**
     * HHmmss  比如：170630
     */
    public static String HHMMSS = "HHmmss";

    /**
     * HH时mm分ss秒  比如：17时06分30秒
     */
    public static String HH_MM_SS_CN = "HH时mm分ss秒";

    /**
     * HH:mm  比如：17:06
     */
    public static String HH_MM = "HH:mm";

    /**
     * HH时mm分 比如：17时06分
     */
    public static String HH_MM_CN = "HH时mm分";

    /**
     * hh:mm a 比如：05:06 下午 如果需要 显示PM 需要设置 Locale.ENGLISH
     */
    public static String HH_MM_A = "hh:mm a";
}
