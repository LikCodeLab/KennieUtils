package com.kennie.library.utils;

import static android.widget.Toast.makeText;

import android.app.Application;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @项目名 KennieUtils
 * @类名称 ToastUtilsCompat
 * @类描述 吐司
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 23:11
 */
public class ToastUtilsCompat {

    private static Application app;

    public static void init(Application application) {
        app = application;
    }


    public static void toast(int resId) {
        if (app != null) {
            makeText(app, resId, Toast.LENGTH_SHORT).show();
        }
    }

    public static void toast(String msg) {
        if (app != null) {
            makeText(app, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void toast(int resId, int duration) {
        if (app != null) {
            makeText(app, resId, duration).show();
        }
    }

    public static void toast(String msg, int duration) {
        if (app != null) {
            makeText(app, msg, duration).show();
        }
    }

    public static void toastCenter(String msg) {
        if (app != null) {
            Toast toast = makeText(app, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public static void toastCenter(int resId) {
        if (app != null) {
            Toast toast = makeText(app, resId, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
