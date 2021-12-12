package com.kennie.library.utils.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;

import com.kennie.library.utils.KennieUtilInit;
import com.kennie.library.utils.entity.NET_TYPE;

/**
 * @项目名 KennieUtils
 * @类名称 NetBroadcastUtils
 * @类描述 网络变化广播
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/13 0:27
 */
public class NetBroadcastUtils extends BroadcastReceiver {

    private static final String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private static final String TAG = NetBroadcastUtils.class.getSimpleName();
    private OnNetChangeListener mOnNetChangeListener;
    private static NetBroadcastUtils netListenerUtils;

    //单例模式
    public static NetBroadcastUtils getInstance() {
        if (netListenerUtils == null) {
            synchronized (NetBroadcastUtils.class) {
                if (netListenerUtils == null) {
                    netListenerUtils = new NetBroadcastUtils();
                }
            }
        }
        return netListenerUtils;
    }

    /**
     * 设置回调
     */
    public void setOnNetChangeListener(OnNetChangeListener onNetChangeListener) {
        mOnNetChangeListener = onNetChangeListener;
    }

    /**
     * 注册广播
     */
    public void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ANDROID_NET_CHANGE_ACTION);
        KennieUtilInit.getAppContext().registerReceiver(this, intentFilter);
    }

    /**
     * 销毁广播
     */
    public void unRegisterReceiver() {
        try {
            KennieUtilInit.getAppContext().unregisterReceiver(this);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "errMeg:" + e.getMessage());
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e(TAG, "action=" + action);
        if (action.equals(ANDROID_NET_CHANGE_ACTION)) {
            if (mOnNetChangeListener != null) {
                //获取当前网络类型
                int type = NetUtils.getNetWorkType();
                //判断网络是否连接正常，是否能够ping通
                boolean isPingSuccessful = NetUtils.ping(1, 2);
                Log.e(TAG, "type=" + type);
                if (type == -1) {//TYPE_NONE
                    mOnNetChangeListener.changed(NET_TYPE.NETWORK_NO, isPingSuccessful);
                } else if (type == ConnectivityManager.TYPE_WIFI) {//1
                    mOnNetChangeListener.changed(NET_TYPE.NETWORK_WIFI, isPingSuccessful);
                } else if (type == NetUtils.NETWORK_2G) {//2
                    mOnNetChangeListener.changed(NET_TYPE.NETWORK_2G, isPingSuccessful);
                } else if (type == NetUtils.NETWORK_3G) {//3
                    mOnNetChangeListener.changed(NET_TYPE.NETWORK_3G, isPingSuccessful);
                } else if (type == NetUtils.NETWORK_4G) {//4
                    mOnNetChangeListener.changed(NET_TYPE.NETWORK_4G, isPingSuccessful);
                } else if (type == ConnectivityManager.TYPE_ETHERNET) {//9
                    mOnNetChangeListener.changed(NET_TYPE.NETWORK_ETH, isPingSuccessful);
                } else {
                    mOnNetChangeListener.changed(NET_TYPE.NETWORK_UNKNOWN, isPingSuccessful);
                }
            }
        }
    }
}
