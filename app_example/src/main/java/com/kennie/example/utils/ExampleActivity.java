package com.kennie.example.utils;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.kennie.library.utils.AppPathUtilsCompat;
import com.kennie.library.utils.AppUtilsCompat;
import com.kennie.library.utils.DateUtilsCompat;
import com.kennie.library.utils.FileUtilsCompat;
import com.kennie.library.utils.IntentUtilsCompat;
import com.kennie.library.utils.PhoneScreenUtilsCompat;
import com.kennie.library.utils.StringUtilsCompat;
import com.kennie.library.utils.config.DatePatternConstants;
import com.kennie.library.utils.helper.HandlerMainHelper;
import com.kennie.library.utils.ZOLD.algorithm.AlgorithmEncryptUtil;
import com.kennie.library.utils.ZOLD.AppMarketUtil;
import com.kennie.library.utils.ZOLD.RomUtil;

import java.io.File;
import java.util.Date;

public class ExampleActivity extends AppCompatActivity {

    private static String TAG = ExampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        // AppPathUtilsCompat
        Log.i(TAG, "获取APP应用数据路径：" + AppPathUtilsCompat.getAppDataPath());
        Log.i(TAG, "获取APP应用缓存路径：" + AppPathUtilsCompat.getAppCachePath());
        Log.i(TAG, "获取APP应用文件路径：" + AppPathUtilsCompat.getAppFilesPath());
        Log.i(TAG, "获取APP应用SP路径：" + AppPathUtilsCompat.getAppSpPath());
        Log.i(TAG, "获取APP应用数据库路径：" + AppPathUtilsCompat.getAppDbPath());
        Log.i(TAG, "获取APP应用数据库路径：" + AppPathUtilsCompat.getAppDbPath("databaseName"));
        Log.i(TAG, "获取外存储路径：" + AppPathUtilsCompat.getExternalStoragePath());
        Log.i(TAG, "获取外存储公共目录路径(不同类别)：" + AppPathUtilsCompat.getExternalStoragePublicPath(Environment.DIRECTORY_DCIM));


        // AppUtilsCompat
        Log.i(TAG, "APP名称：" + AppUtilsCompat.getAppName());
        Log.i(TAG, "App包名：" + AppUtilsCompat.getAppPackage());
        Log.i(TAG, "App版本名称 ：" + AppUtilsCompat.getAppVersionName());
        Log.i(TAG, "App版本号 ：" + AppUtilsCompat.getAppVersionCode());
        Log.i(TAG, "App图标 ：" + AppUtilsCompat.getAppIcon());
        Log.i(TAG, "App安装原始路径：" + AppUtilsCompat.getAppInstallPath());
        Log.i(TAG, "App原始安装文件(APK)：" + AppUtilsCompat.getAppSourceFile().getAbsolutePath());
        Log.i(TAG, "APP是否安装：" + AppUtilsCompat.isAppInstalled());
        Log.i(TAG, "APP是否安装：" + AppUtilsCompat.isAppInstalled("com.jskjjx.cloudmeeting"));
        Log.i(TAG, "APP是否为Debug模式 ：" + AppUtilsCompat.isAppDebugMode());
        Log.i(TAG, "App是否处于前台 ：" + AppUtilsCompat.isAppForeground());
        // Log.i(TAG, "安装APP（兼容Android7.0及以上版本） ：");
        // AppUtilsCompat.installApk(new File(""), "");

        // DateUtilsCompat
        Log.i(TAG, "当前时间戳：" + DateUtilsCompat.getCurrentTimeMillis());
        Log.i(TAG, "当前缺省格式日期与时间：" + DateUtilsCompat.getCurrentDate());
        Log.i(TAG, "当前指定格式日期与时间：" + DateUtilsCompat.getCurrentDate(DatePatternConstants.YYYY_MM_DD_HH_MM_SS_CN_ALL));
        Log.i(TAG, "当前格式化时间：" + DateUtilsCompat.formatDate(System.currentTimeMillis(), "yyyy/MM/dd HH:mm:ss"));
        Log.i(TAG, "当前格式化时间：" + DateUtilsCompat.formatDate(new Date(), DatePatternConstants.YYYY_MM_DD_HH_MM_SS));
        Log.i(TAG, "获取新的间隔年份日期：" + DateUtilsCompat.formatDate(DateUtilsCompat.getDateByIntervalYears(new Date(), -1), DatePatternConstants.YYYY_MM_DD));
        Log.i(TAG, "获取新的间隔月数日期：" + DateUtilsCompat.formatDate(DateUtilsCompat.getDateByIntervalMonths(new Date(), 1), DatePatternConstants.YYYY_MM_DD));
        Log.i(TAG, "获取新的间隔天数日期：" + DateUtilsCompat.formatDate(DateUtilsCompat.getDateByIntervalDays(new Date(), 3), DatePatternConstants.YYYY_MM_DD));
        Log.i(TAG, "前一天：" + DateUtilsCompat.getBeforeDate(DatePatternConstants.YYYY_MM_DD_HH_MM_SS));
        Log.i(TAG, "前一月：" + DateUtilsCompat.getBeforeMonth(DatePatternConstants.YYYY_MM_DD_HH_MM_SS));

        // FileUtilsCompat
        Log.i(TAG, "判断文件是否存在：" + FileUtilsCompat.isExist(AppPathUtilsCompat.getExternalStoragePath() + File.separator + "app_clerk-debug.apk"));
        String sourcePath = AppPathUtilsCompat.getExternalStoragePath() + File.separator + "app_clerk-debug_new.apk";
        String targetPath = AppPathUtilsCompat.getExternalStoragePath() + File.separator + "app_clerk-debug.apk";
        Log.i(TAG, "判断重命名文件名称是否成功：" + FileUtilsCompat.rename(sourcePath, targetPath));

        // ScreenUtilsCompat
        Log.i(TAG, "获取状态栏高度：" + PhoneScreenUtilsCompat.getStatusBarHeight());
        Log.i(TAG, "获取ActionBar高度：" + PhoneScreenUtilsCompat.getActionBarHeight());
        Log.i(TAG, "获取导航栏高度：" + PhoneScreenUtilsCompat.getNavigationBarHeight());
        Log.i(TAG, "获取屏幕宽度：" + PhoneScreenUtilsCompat.getScreenWidth());
        Log.i(TAG, "获取屏幕高度：" + PhoneScreenUtilsCompat.getScreenHeight());
        Log.i(TAG, "dp转换为px：" + PhoneScreenUtilsCompat.dp2px(48));
        Log.i(TAG, "sp转换为px：" + PhoneScreenUtilsCompat.sp2px(48));


        // StringUtilsCompat
        Log.i(TAG, "格式化字符串：" + StringUtilsCompat.formatSafeStr("15062255123", 3, 4));
        Log.i(TAG, "格式化时长：" + StringUtilsCompat.formatDurationStr(65));


        Log.i(TAG, "ROM：" + RomUtil.isXiaomi());


        Log.i(TAG, "加密后的：" + AlgorithmEncryptUtil.getMD5("12345"));
        Log.i(TAG, "加密后的：" + AlgorithmEncryptUtil.getHash("12345", AlgorithmEncryptUtil.SHA1));
        Log.i(TAG, "加密后的：" + AlgorithmEncryptUtil.getHash("12345", AlgorithmEncryptUtil.SHA256));

        Log.i(TAG, "获取APP市场：" + AppMarketUtil.isMarketAvailable());

        HandlerMainHelper.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "测试HandlerMainHelper.postDelayed:" + HandlerMainHelper.isMain());
                //IntentUtilsCompat.gotoSettings(Settings.ACTION_SETTINGS);
                IntentUtilsCompat.gotoAppNotificationSetting();

            }
        }, 2000);
    }
}