package com.kennie.utils;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：StringUtils
 * Date：2021/12/12 23:15
 * Desc：数据处理类
 *
 * <p>
 * --*********                                      {@link #}
 * </p>
 */
public class DataUtils {

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
     * 将手机号中间4位换成****
     *
     * @param phone 手机号
     * @return 替换后的手机号
     */
    public static String formatPhoneNumber(String phone) {
        if (phone == null) return "";
        try {
            StringBuilder sb = new StringBuilder(phone.trim());
            return sb.replace(3, 7, "****").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return phone;
        }
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

    /***
     * 格式化银行卡号
     * @param input : 银行卡号,例如"6225880137706868"
     * @return
     */
    public static String formatBankCardNo(String input) {
        return input.replaceAll("([\\d]{4})(?=\\d)", "$1 ");
    }

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
                Integer cell1Int = Integer.parseInt(cell1);
                Integer cell2Int = Integer.parseInt(cell2);
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

    /**
     * 从字符串提取手机号码
     *
     * @param sParam 目标字符串
     * @return 手机号
     */
    public static String getPhoneFromStr(String sParam) {
        if (TextUtils.isEmpty(sParam)) return "";
        Pattern pattern = Pattern.compile("(1|861)(3|5|7|8)\\d{9}$*");
        Matcher matcher = pattern.matcher(sParam);
        StringBuilder bf = new StringBuilder();
        while (matcher.find()) {
            bf.append(matcher.group()).append(",");
        }
        int len = bf.length();
        if (len > 0) {
            bf.deleteCharAt(len - 1);
        }
        return bf.toString();
    }

}
