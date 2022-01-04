package com.kennie.library.utils.helper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import java.lang.ref.SoftReference;

/**
 * @项目名 KennieUtils
 * @类名称 HandlerMainHelper
 * @类描述 单例形式的Handler, 主线程
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/11 10:48
 */
public class HandlerMainHelper {

    Handler mainHandler;
    private static volatile HandlerMainHelper instance = null;

    private HandlerMainHelper(Looper looper) {
        mainHandler = new Handler(looper);
    }

    public static HandlerMainHelper get() {
        //双重校验DCL单例模式
        if (instance == null) {
            //同步代码块
            synchronized (HandlerMainHelper.class) {
                if (instance == null) {
                    //创建一个新的实例
                    instance = new HandlerMainHelper(Looper.getMainLooper());
                }
            }
        }
        //返回一个实例
        return instance;
    }

    public void posts(Runnable runnable) {
        mainHandler.post(new SoftRunnable(runnable));
    }

    public void postDelayedSoft(Runnable runnable, long delayMillis) {
        mainHandler.postDelayed(new SoftRunnable(runnable), delayMillis);
    }

    public void postDelayedSoft(Runnable runnable, Object token, long delayMillis) {
        Message message = Message.obtain(get().mainHandler, new SoftRunnable(runnable));
        message.obj = token;
        get().mainHandler.sendMessageDelayed(message, delayMillis);
    }


    public static void post(Runnable runnable) {
        get().mainHandler.post(new SoftRunnable(runnable));
    }

    public static void postDelayed(Runnable runnable, long delayMillis) {
        get().mainHandler.postDelayed(new SoftRunnable(runnable), delayMillis);
    }

    public static void postDelayed(Runnable runnable, Object token, long delayMillis) {
        Message message = Message.obtain(get().mainHandler, new SoftRunnable(runnable));
        message.obj = token;
        get().mainHandler.sendMessageDelayed(message, delayMillis);
    }

    /**
     * 解决回调内存泄露
     */
    public static class SoftRunnable implements Runnable {
        SoftReference<Runnable> runnable;

        public SoftRunnable(Runnable runnable) {
            this.runnable = new SoftReference<Runnable>(runnable);
        }

        @Override
        public void run() {
            if (runnable != null && runnable.get() != null) {
                runnable.get().run();
            }
        }
    }

    /**
     * 是否是主线程
     */
    public static boolean isMain() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
