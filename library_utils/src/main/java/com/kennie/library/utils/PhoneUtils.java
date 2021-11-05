package com.kennie.library.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * @项目名 KennieUtils
 * @类名称 PhoneUtils
 * @类描述 手机管理类
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 20:53
 */
public class PhoneUtils {

    /**
     * 是否有sim卡 即设备是否可以拨打电话等
     */
    public static Boolean hasSim(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        boolean result = true;
        switch (telephonyManager.getSimState()) {
            case TelephonyManager.SIM_STATE_ABSENT:
            case TelephonyManager.SIM_STATE_UNKNOWN:
                result = false;
                break;
        }
        return result;
    }
}
