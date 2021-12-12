package com.kennie.library.utils.old.core;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @项目名 KennieUtils
 * @类名称 KeyboardUtil
 * @类描述
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 22:39
 */
public class KeyboardUtil {

    /**
     * 显示键盘
     *
     * @param view
     */
    public static void showSoftInput(final View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * 隐藏键盘
     *
     * @param activity
     */
    public static void hideSoftInput(final Activity activity) {
        if (activity != null) {
            hideSoftInput(activity.getCurrentFocus());
        }
    }

    public static void hideSoftInput(final View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
