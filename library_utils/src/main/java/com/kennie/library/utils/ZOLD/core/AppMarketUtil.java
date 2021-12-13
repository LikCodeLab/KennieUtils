package com.kennie.library.utils.ZOLD.core;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import com.kennie.library.utils.KennieUtilInit;

import java.util.List;

/**
 * @项目名 KennieUtils
 * @类名称 AppMarketUtil
 * @类描述 App应用市场
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 20:55
 */
public class AppMarketUtil {

    public static final String MARKET_DATA = "market://details";
    public static final String MARKET_GOOGLE_PLAY = "com.android.vending";
    public static final String MARKET_TENCENT = "com.tencent.android.qqdownloader";
    public static final String MARKET_SAMSUNG = "com.sec.android.app.samsungapps";
    public static final String MARKET_HUAWEI = "com.huawei.appmarket";
    public static final String MARKET_XIAOMI = "com.xiaomi.market";
    public static final String MARKET_OPPO = "com.oppo.market";
    public static final String MARKET_VIVO = "com.bbk.appstore";


    /**
     * 判断应用市场是否存在
     *
     * @return
     */
    public static boolean isMarketAvailable() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(MARKET_DATA));
        List<ResolveInfo> list = KennieUtilInit.getApp().getPackageManager().queryIntentActivities(intent, 0);
        return list != null && list.size() > 0;
    }

    /**
     * 判断当前应用是否从Google Play下载
     *
     * @return
     */
    public static boolean isInstalledFromGooglePlay() {
        try {
            String installer = KennieUtilInit.getApp().getPackageManager().getInstallerPackageName(KennieUtilInit.getApp().getPackageName());
            return installer != null && installer.equals(MARKET_GOOGLE_PLAY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 跳转到GooglePlay商店
     *
     * @param appPackageName
     */
    public static void gotoGooglePlay(String appPackageName) {
        gotoMarket(appPackageName, MARKET_GOOGLE_PLAY);
    }

    /**
     * @param appPackageName
     */
    public static void gotoMarket(String appPackageName) {
        gotoMarket(appPackageName, null);
    }

    /**
     * @param appPackageName
     * @param marketPackageName
     */
    private static void gotoMarket(String appPackageName, String marketPackageName) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(String.format("%s?id=%s", MARKET_DATA, appPackageName)));
            if (!TextUtils.isEmpty(marketPackageName)) {
                intent.setPackage(marketPackageName);
                if (MARKET_SAMSUNG.equalsIgnoreCase(marketPackageName)) {
                    intent.setData(Uri.parse(String.format("samsungapps://ProductDetail/%s", appPackageName)));
                }
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            KennieUtilInit.getApp().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
