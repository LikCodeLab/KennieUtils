package com.kennie.utils;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
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
public class StringUtils {


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


    /**
     * 将字母和数字拆分出来
     *
     * @param str
     * @return
     */
    public static List<String> splitCell(String str) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        Boolean isNum = null;
        for (char c : chars) {
            boolean same = true;
            boolean num = c >= '0' && c <= '9';
            if (isNum != null) {
                same = isNum && num;
            }
            isNum = num;
            if (!same) {
                list.add(sb.toString());
                sb.setLength(0);
            }
            sb.append(c);
        }
        //添加最后一个
        list.add(sb.toString());
        return list;
    }

    /**
     * 比较APP版本号大小
     *
     * @param ver1     版本号1
     * @param ver2     版本号2
     * @param shortBig 当较长的版本号包含短的时，true则短的为大，false则短的为小
     * @return 0 相等 -1 小于 1 大于
     */
    public static int compare(String ver1, String ver2, boolean shortBig) {
        //将将版本号字符串以.分割进行比较
        String s1 = ver1.replaceAll(" ", "");
        String s2 = ver2.replaceAll(" ", "");
        if (s1.equals(s2)) {
            return 0;
        } else if (s1.contains(s2)) {
            return shortBig ? -1 : 1;
        } else if (s2.contains(s1)) {
            return shortBig ? 1 : -1;
        }
        String[] vCells1 = s1.split("\\.");
        String[] vCells2 = s2.split("\\.");
        //以长度短的，对分割元素逐个比较
        int len = Math.min(vCells1.length, vCells2.length);
        for (int i = 0; i < len; i++) {
            List<String> cellList1 = splitCell(vCells1[i]);
            List<String> cellList2 = splitCell(vCells2[i]);
            //同样逐个比较
            int len1 = Math.min(cellList1.size(), cellList2.size());
            for (int j = 0; j < len1; j++) {
                String cell1 = cellList1.get(j);
                String cell2 = cellList2.get(j);
                Integer cell1Int = toInt(cell1);
                Integer cell2Int = toInt(cell2);
                int result;
                if (cell1Int != null && cell2Int != null) {
                    result = cell1Int.compareTo(cell2Int);
                } else {
                    result = cell1.compareTo(cell2);
                }
                if (result != 0) {
                    return result;
                }
            }
        }
        return 0;
    }

    public static Integer toInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }
}
