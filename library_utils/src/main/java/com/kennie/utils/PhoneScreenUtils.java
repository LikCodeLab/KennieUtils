package com.kennie.utils;


import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * @项目名 KennieUtils
 * @类名称 PhoneScreenUtils
 * @类描述 手机屏幕管理类
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/13 0:03
 *
 * <p>
 * --获取状态栏高度px                                      {@link #getStatusBarHeight()}
 * --获取ActionBar高度px                                         {@link #getActionBarHeight()}
 * --获取导航栏高度px                                         {@link #getNavigationBarHeight()}
 * --获取屏幕宽度                                         {@link #getScreenWidth()}
 * --获取屏幕高度                                         {@link #getScreenHeight()}
 * --获取屏幕密度                                         {@link #getScreenDensity()}
 * --dp转换为px                                         {@link #dp2px(float dp)}
 * --sp转换为px                                         {@link #sp2px(float sp)}
 *
 * </p>
 */
public class PhoneScreenUtils {


    /**
     * 获取状态栏高度px
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        int statusHeight = 0;
        int resourceId = KennieUtilInit.getAppContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusHeight = KennieUtilInit.getAppContext().getResources().getDimensionPixelSize(resourceId);
        }
        return statusHeight;
    }


    /**
     * 获取ActionBar高度px
     *
     * @return ActionBar高度
     */
    public static int getActionBarHeight() {
        int height = 0;
        TypedValue tv = new TypedValue();
        if (KennieUtilInit.getApp().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            height = TypedValue.complexToDimensionPixelSize(tv.data, KennieUtilInit.getAppContext().getResources().getDisplayMetrics());
        }
        return height;
    }


    /**
     * 获取导航栏高度px
     *
     * @return 导航栏高度
     */
    public static int getNavigationBarHeight() {
        String field = "";
        if (KennieUtilInit.getApp().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            field = "navigation_bar_height";
        } else {
            field = "navigation_bar_height_landscape";
        }
        int resourceId = KennieUtilInit.getAppContext().getResources().getIdentifier(field, "dimen", "android");
        if (resourceId > 0) {
            return KennieUtilInit.getAppContext().getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    /**
     * 获取屏幕宽度
     *
     * @return 屏幕宽度
     */
    public static int getScreenWidth() {
        return KennieUtilInit.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }


    /**
     * 获取屏幕高度
     *
     * @return 屏幕高度
     */
    public static int getScreenHeight() {
        return KennieUtilInit.getAppContext().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕密度
     *
     * @return
     */
    public static float getScreenDensity() {
        return KennieUtilInit.getAppContext().getResources().getDisplayMetrics().density;
    }


    /**
     * dp转换为px
     *
     * @param dp
     * @return
     */
    public static int dp2px(float dp) {
        return dp2px(dp, TypedValue.COMPLEX_UNIT_DIP);
    }


    /**
     * sp转换为px
     *
     * @param sp
     * @return
     */
    public static int sp2px(float sp) {
        return dp2px(sp, TypedValue.COMPLEX_UNIT_SP);
    }


    private static int dp2px(float value, int unit) {
        DisplayMetrics displayMetrics = KennieUtilInit.getAppContext().getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(unit, value, displayMetrics);
    }


    /**
     * px转换为dp
     */
    public static float px2dp(int px) {
        return px / getScreenDensity();
    }
}
