package com.kennie.library.utils.old.helper;

import android.os.SystemClock;

/**
 * @项目名 KennieUtils
 * @类名称 DoubleClickHelper
 * @类描述 双击判断工具类
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 1:19
 */
public class DoubleClickHelper {

    /**
     * 数组的长度为2代表只记录双击操作
     */
    private static final long[] TIME_ARRAY = new long[2];

    /**
     * 是否在短时间内进行了双击操作
     */
    public static boolean isOnDoubleClick() {
        // 默认间隔时长
        return isOnDoubleClick(1500);
    }

    /**
     * 是否在短时间内进行了双击操作
     */
    public static boolean isOnDoubleClick(int time) {
        System.arraycopy(TIME_ARRAY, 1, TIME_ARRAY, 0, TIME_ARRAY.length - 1);
        TIME_ARRAY[TIME_ARRAY.length - 1] = SystemClock.uptimeMillis();
        return TIME_ARRAY[0] >= (SystemClock.uptimeMillis() - time);
    }
}
