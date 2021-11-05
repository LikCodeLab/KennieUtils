package com.kennie.library.utils;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.UUID;

/**
 * @项目名 KennieUtils
 * @类名称 StringUtils
 * @类描述 字符串处理工具
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 20:28
 */

public class StringUtils {

    /**
     * 将指定字符串的某些字符替换成* 例如130****6368
     *
     * @param source     原字符
     * @param startIndex 开始索引
     * @param count      个数
     * @return String
     */
    public static String getSafeText(String source, int startIndex, int count) {
        if (TextUtils.isEmpty(source)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            if (i > (startIndex - 1) && i < source.length() - count) {
                sb.append("*");
            } else {
                sb.append(source.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 银行卡字符串添加空格
     *
     * @param source String
     * @return String
     */
    public static String getBlankString(String source) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i != 0 && i % 4 == 0) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(source.charAt(i));
        }
        return stringBuilder.toString();
    }


    /**
     * 银行卡字符串添加空格并且前四位后三位显示 中间*号
     *
     * @param source source
     * @return String
     */
    public static String getBlankSymbolString(String source) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i != 0 && i % 4 == 0) {
                stringBuilder.append(" ");
            }
            if (i >= 4 && i < source.length() - 3) {
                stringBuilder.append("*");
            } else {
                stringBuilder.append(source.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 保留两位小数
     *
     * @param num Double
     * @return 两位小数
     */
    public static String getNumberFormat(Double num) {
        return new DecimalFormat("0.00").format(num == null ? 0 : num);
    }

    /**
     * 生成一个uuid字符串，不带短杠
     */
    public static String randomUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 补零
     *
     * @param src       原字符串
     * @param targetLen 目标长度
     * @param head      补前面还是后面
     */
    public static String fillZero(String src, int targetLen, boolean head) {
        if (src == null) return null;
        StringBuilder sb = new StringBuilder(src);
        while (sb.length() % targetLen != 0) {
            if (head) {
                sb.insert(0, "0");
            } else {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    /**
     * 数字转16进制字符串，不足2位自动补零
     */
    public static String toHex(int num) {
        return fillZero(Integer.toHexString(num), 2, true);
    }

    /**
     * 数字转16进制字符串，不足2位自动补零
     */
    public static String toHex(long num) {
        return fillZero(Long.toHexString(num), 2, true);
    }

    /**
     * 数字转2进制字符串，不足8位自动补零
     */
    public static String toBinary(int num) {
        return fillZero(Integer.toBinaryString(num), 8, true);
    }

    /**
     * 数字转2进制字符串，不足8位自动补零
     */
    public static String toBinary(long num) {
        return fillZero(Long.toBinaryString(num), 8, true);
    }

    /**
     * byte数组转换成16进制字符串
     *
     * @return 如果bytes为null则返回null，如果bytes长度为0返回""，其他返回正常转换的字符串
     */
    public static String toHex(byte[] bytes) {
        return toHex(bytes, " ");
    }

    /**
     * byte数组转换成16进制字符串
     *
     * @param separator 用来分隔的字符串
     * @return 如果bytes为null则返回null，如果bytes长度为0返回""，其他返回正常转换的字符串
     */
    public static String toHex(byte[] bytes, String separator) {
        if (bytes == null) {
            return null;
        } else if (bytes.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte aSrc : bytes) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
            if (!TextUtils.isEmpty(separator)) {
                sb.append(separator);
            }
        }
        String s = sb.toString().toUpperCase(Locale.ENGLISH);
        if (!TextUtils.isEmpty(separator)) {
            s = s.substring(0, s.length() - separator.length());
        }
        return s;
    }

    /**
     * byte数组转换成2进制字符串
     *
     * @return 如果bytes为null则返回null，如果bytes长度为0返回""，其他返回正常转换的字符串
     */
    public static String toBinary(byte[] bytes) {
        return toBinary(bytes, " ");
    }

    /**
     * byte数组转换成2进制字符串
     *
     * @param separator 用来分隔的字符串
     * @return 如果bytes为null则返回null，如果bytes长度为0返回""，其他返回正常转换的字符串
     */
    public static String toBinary(byte[] bytes, String separator) {
        if (bytes == null) {
            return null;
        } else if (bytes.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte aSrc : bytes) {
            int v = aSrc & 0xFF;
            String hv = Integer.toBinaryString(v);
            int loop = 8 - hv.length();
            for (int i = 0; i < loop; i++) {
                sb.append(0);
            }
            sb.append(hv);
            if (!TextUtils.isEmpty(separator)) {
                sb.append(separator);
            }
        }
        String s = sb.toString();
        if (!TextUtils.isEmpty(separator)) {
            s = s.substring(0, s.length() - separator.length());
        }
        return s;
    }

    /**
     * 去掉小数点后多余的0
     */
    public static String subZeroAndDot(String number) {
        if (number.indexOf(".") > 0) {
            return new BigDecimal(number).stripTrailingZeros().toPlainString();
        }
        return number;
    }

    /**
     * 格式00:00:00
     *
     * @param duration 时长，单位：秒
     */
    @NonNull
    public static String toDuration(int duration) {
        return toDuration(duration, null);
    }

    /**
     * 将时长转换成指定格式的字符串
     *
     * @param duration 时长，单位：秒
     */
    @NonNull
    public static String toDuration(int duration, String format) {
        if (format != null) {
            return String.format(Locale.ENGLISH, format, duration / 3600, duration % 3600 / 60, duration % 60);
        } else {
            return String.format(Locale.ENGLISH, "%02d:%02d:%02d", duration / 3600, duration % 3600 / 60, duration % 60);
        }
    }

    /**
     * 进16进制字符串转换成字节数组
     *
     * @param hexStr    16进制的字符串
     * @param separator 字符串字节间的分隔符
     */
    public static byte[] toByteArray(String hexStr, String separator) {
        String s = hexStr.replaceAll(separator, "");
        if (s.length() % 2 != 0) {
            s = "0" + s;
        }
        byte[] bytes = new byte[s.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }
}
