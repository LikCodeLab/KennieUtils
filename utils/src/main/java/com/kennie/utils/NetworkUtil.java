package com.kennie.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Author：Kennie
 * Project：KennieUtils
 * Class：SPUtils
 * Date：2021/12/12 23:15
 * Desc：网络状态工具类
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 *
 * <p>
 * </p>
 */
public class NetworkUtil {

    private String TAG = NetworkUtil.class.getSimpleName();

    public enum NetType {
        None(1),
        Mobile(2),
        Wifi(4),
        Other(8);

        NetType(int value) {
            this.value = value;
        }

        public int value;
    }

    public enum NetWorkType {
        UnKnown(-1),
        Wifi(1),
        Net2G(2),
        Net3G(3),
        Net4G(4);

        NetWorkType(int value) {
            this.value = value;
        }

        public int value;
    }

    /**
     * 检测网络是否连接（此时可传输数据）
     *
     * @return boolean 不管wifi，还是mobile net，只有当前在连接状态（可有效传输数据）才返回true,反之false。
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = getConnectivityManager(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network nw = connectivityManager.getActiveNetwork();
            if (nw == null) return false;
            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
            return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
        } else {
            NetworkInfo net = connectivityManager.getActiveNetworkInfo();
            return net != null && net.isConnected();
        }
    }


    /**
     * 打印当前各种网络状态
     *
     * @return boolean
     */
    public static boolean printNetworkInfo(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo in = connectivity.getActiveNetworkInfo();
            LogUtil.i("getActiveNetworkInfo: " + in);
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    // if (info[i].getType() == ConnectivityManager.TYPE_WIFI) {
                    LogUtil.i("NetworkInfo[" + i + "]isAvailable : " + info[i].isAvailable());
                    LogUtil.i("NetworkInfo[" + i + "]isConnected : " + info[i].isConnected());
                    LogUtil.i("NetworkInfo[" + i + "]isConnectedOrConnecting : " + info[i].isConnectedOrConnecting());
                    LogUtil.i("NetworkInfo[" + i + "]: " + info[i]);
                    // }
                }
                LogUtil.i("\n");
            } else {
                LogUtil.i("getAllNetworkInfo is null");
            }
        }
        return false;
    }

    /**
     * 获取ConnectivityManager
     */
    private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * 获取TelephonyManager
     */
    private static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

}
