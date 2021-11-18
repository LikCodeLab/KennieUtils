package com.kennie.library.utils.core;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.kennie.library.utils.KennieUtilsApp;

/**
 * @项目名 KennieUtils
 * @类名称 ScreenUtil
 * @类描述
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 22:45
 */
public class ScreenUtil {


    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        DisplayMetrics displayMetrics = KennieUtilsApp.getApp().getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }


    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        DisplayMetrics displayMetrics = KennieUtilsApp.getApp().getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }


    /**
     * 获取屏幕密度
     *
     * @return
     */
    public static float getScreenDensity() {
        DisplayMetrics displayMetrics = KennieUtilsApp.getApp().getResources().getDisplayMetrics();
        return displayMetrics.density;
    }

    /**
     * 获取屏幕宽度和高度
     *
     * @return
     */
    public static ScreenSize getScreenSize() {
        DisplayMetrics displayMetrics = KennieUtilsApp.getApp().getResources().getDisplayMetrics();
        return new ScreenSize(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }


    /**
     * dp转换为px
     *
     * @param dp
     * @return
     */
    public static int dp2px(float dp) {
        return xp2px(dp, TypedValue.COMPLEX_UNIT_DIP);
    }


    /**
     * sp转换为px
     *
     * @param sp
     * @return
     */
    public static int sp2px(float sp) {
        return xp2px(sp, TypedValue.COMPLEX_UNIT_SP);
    }

    private static int xp2px(float f, int unit) {
        DisplayMetrics displayMetrics = KennieUtilsApp.getApp().getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(unit, f, displayMetrics);

    }

    /**
     * 是否为平板
     *
     * @return true 是 false 否
     */
    public static boolean isPad() {
        Configuration configuration = KennieUtilsApp.getApp().getResources().getConfiguration();
        int x = configuration.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        return x >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        int statusHeight = 0;
        int resourceId = KennieUtilsApp.getApp().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusHeight = KennieUtilsApp.getApp().getResources().getDimensionPixelSize(resourceId);
        }
        return statusHeight;
    }


    /**
     * 获取导航栏高度
     *
     * @return 导航栏高度
     */
    public static int getNavigationBarHeight() {
        String field = "";
        if (KennieUtilsApp.getApp().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            field = "navigation_bar_height";
        } else {
            field = "navigation_bar_height_landscape";
        }
        int resourceId = KennieUtilsApp.getApp().getResources().getIdentifier(field, "dimen", "android");
        if (resourceId > 0) {
            return KennieUtilsApp.getApp().getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }


    /**
     * 获取ActionBar高度
     *
     * @return ActionBar高度
     */
    public static int getActionBarHeight() {
        int height = 0;
        TypedValue tv = new TypedValue();
        if (KennieUtilsApp.getApp().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            height = TypedValue.complexToDimensionPixelSize(tv.data, KennieUtilsApp.getApp().getResources().getDisplayMetrics());
        }
        return height;
    }


    public static class ScreenSize {

        private int width;
        private int height;

        public ScreenSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }


        @Override
        public String toString() {
            return width + "x" + height;
        }

    }
}
