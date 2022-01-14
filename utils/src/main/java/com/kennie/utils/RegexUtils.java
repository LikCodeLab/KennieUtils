package com.kennie.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：StringUtils
 * Date：2021/12/12 23:15
 * Desc：正则处理类
 *
 * <p>
 * --*********                                      {@link #}
 * </p>
 */
public class RegexUtils {

    public final static int TEXT_SHORT = 1; // 数据过短
    public final static int TEXT_LONG = 2; // 数据过长
    public final static int TEXT_ILLEGAL = 3; // 数据非法
    public final static int TEXT_NORMAL = 0; // 数据正常


    /**
     * 验证密码是否有效
     *
     * @param pwd 密码
     * @return 是否有效
     */
    public static int isPwdValid(String pwd) {
        if (pwd.length() < 6) {
            return TEXT_SHORT;
        } else if (pwd.length() > 16) {
            return TEXT_LONG;
        } else if (!pwd.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$")) {
            return TEXT_ILLEGAL;
        } else {
            return TEXT_NORMAL;
        }
    }

    /**
     * 验证是否是手机号
     *
     * @param strTelNo 手机号 新增移动198 166联通 199电信 14*号段
     * @return true 是 false 否
     */
    public static boolean isMobile(String strTelNo) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(166)|(17[0-9])|(18[0-9])|(198)|(199))\\d{8}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(strTelNo);
        return m.matches();
    }


    /**
     * 是否是邮箱
     *
     * @param strEmail 邮箱
     * @return true 是 false 否
     */
    public static boolean isEmail(String strEmail) {
        //String regex = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }


    /**
     * 是否是银行卡号
     *
     * @param strBankNo 银行卡号
     * @return true 是 false 否
     */
    public static boolean isBankCardNo(String strBankNo) {
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(strBankNo);
        return m.matches();
    }


    /**
     * 验证身份证号是否符合规则
     *
     * @param text 身份证号
     * @return true 是 false 否
     */
    public static boolean personIdValidation(String text) {
        String regex0 = "[0-9]{17}x";
        String regex1 = "[0-9]{15}";
        String regex2 = "[0-9]{18}";
        return text.matches(regex0) || text.matches(regex1) || text.matches(regex2);
    }


    /**
     * 判断字符串是否包含中文
     *
     * @param str str 判断的字符串
     * @return true 是 false 否
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str 字符串
     * @return true数字
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }


    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate 字符串
     * @return true 是日期格式
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        return m.matches();
    }
}
