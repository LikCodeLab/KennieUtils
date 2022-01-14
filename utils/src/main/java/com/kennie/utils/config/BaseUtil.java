package com.kennie.utils.config;


public class BaseUtil {
    private static BaseUtil baseUtil;

    public static BaseUtil getInstance() {
        synchronized (BaseUtil.class) {
            if (baseUtil == null) {
                baseUtil = new BaseUtil();
            }
            return baseUtil;
        }
    }
}
