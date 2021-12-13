package com.kennie.library.utils.ZOLD;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * @项目名 KennieUtils
 * @类名称 PhoneDisplayUtils
 * @类描述 手机屏幕处理类
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 20:57
 */
public class PhoneDisplayUtils {

    /**
     * dp2px
     */
    public static int dp2px(Context ctx, float dp) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    /**
     * px2dp
     */
    public static float px2dp(Context ctx, int px) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return px / density;
    }


    /**
     * 获取状态栏高度 px
     */
    public static int getStatusBarHeight(Context ctx) {
        Resources resources = ctx.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 获取ActionBar高度 px
     */
    public static int getActionBarHeight(Context ctx) {
        TypedValue tv = new TypedValue();
        if (ctx.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, ctx.getResources().getDisplayMetrics());
        }
        return 0;
    }

    /**
     * 获取导航栏高度 px
     */
    public static int getNavBarHeight(Context ctx) {
        Resources res = ctx.getResources();
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId != 0) {
            return res.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }
}
