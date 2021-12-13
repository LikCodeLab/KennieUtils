package com.kennie.library.utils.ZOLD;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @项目名 KennieUtils
 * @类名称 RegexUtils
 * @类描述 正则表达式
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 21:08
 */
public class RegexUtils {

    /**
     * 匹配手机号码 ^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$
     *
     * @param num
     * @return
     */
    public static boolean matcherNum(String num) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /**
     * 匹配银行帐号
     **/
    public static boolean matcherBank(String num) {
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /***
     *
     * @param input : 银行卡号,例如"6225880137706868"
     * @return
     */
    public static String formBankCard(String input) {
        String result = input.replaceAll("([\\d]{4})(?=\\d)", "$1 ");
        return result;
    }

    /**
     * 匹配Email
     *
     * @param mail
     * @return
     */
    public static boolean matcherMail(String mail) {
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(mail);
        return m.matches();
    }

    /**
     * 验证身份证号是否符合规则
     *
     * @param text 身份证号
     * @return
     */
    public static boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

    /**
     * 判断字符串是否包含中文
     *
     * @param str str
     * @return boolean
     */
    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
