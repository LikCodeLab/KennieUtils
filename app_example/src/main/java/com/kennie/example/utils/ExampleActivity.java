package com.kennie.example.utils;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.kennie.library.utils.AppUtilsCompat;
import com.kennie.library.utils.DateUtilsCompat;
import com.kennie.library.utils.config.DatePatternConstants;
import com.kennie.library.utils.old.core.AlgorithmEncryptUtil;
import com.kennie.library.utils.old.core.AppMarketUtil;
import com.kennie.library.utils.old.core.AppPathUtil;
import com.kennie.library.utils.old.core.PhoneUtil;
import com.kennie.library.utils.old.core.RomUtil;

import java.util.Date;

public class ExampleActivity extends AppCompatActivity {

    private static String TAG = ExampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

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


        Log.i(TAG, "ROM：" + RomUtil.isXiaomi());


        Log.i(TAG, "获取APP应用数据路径：" + AppPathUtil.getAppDataPath());
        Log.i(TAG, "获取APP应用缓存路径：" + AppPathUtil.getAppCachePath());
        Log.i(TAG, "获取APP应用数据库路径：" + AppPathUtil.getAppDbPath());
        Log.i(TAG, "获取APP应用数据库路径：" + AppPathUtil.getAppDbPath("databaseName"));
        Log.i(TAG, "获取APP应用文件路径：" + AppPathUtil.getAppFilesPath());
        Log.i(TAG, "获取APP应用SP路径：" + AppPathUtil.getAppSpPath());

        Log.i(TAG, "获取APP：" + AppPathUtil.getExternalStoragePath());
        Log.i(TAG, "获取APP：" + AppPathUtil.getAppExternalDataPath());
        Log.i(TAG, "获取APP：" + AppPathUtil.getAppExternalOBBPath());
        Log.i(TAG, "获取APP：" + AppPathUtil.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());


        Log.i(TAG, "获取APP：" + PhoneUtil.isPhone());


        Log.i(TAG, "加密后的：" + AlgorithmEncryptUtil.getMD5("12345"));
        Log.i(TAG, "加密后的：" + AlgorithmEncryptUtil.getHash("12345", AlgorithmEncryptUtil.SHA1));
        Log.i(TAG, "加密后的：" + AlgorithmEncryptUtil.getHash("12345", AlgorithmEncryptUtil.SHA256));

        Log.i(TAG, "获取APP市场：" + AppMarketUtil.isMarketAvailable());

        // DateUtils
        Log.i(TAG, "当前缺省格式日期与时间：" + DateUtilsCompat.getCurrentDate());
        Log.i(TAG, "当前指定格式日期与时间：" + DateUtilsCompat.getCurrentDate(DatePatternConstants.YYYY_MM_DD));
        Log.i(TAG, "当前时间戳：" + DateUtilsCompat.getCurrentTimeMillis());
        Log.i(TAG, "当前格式化时间：" + DateUtilsCompat.formatDate(System.currentTimeMillis(), "yyyy/MM/dd HH:mm:ss"));
        Log.i(TAG, "当前格式化时间：" + DateUtilsCompat.formatDate(new Date(), DatePatternConstants.YYYY_MM_DD_HH_MM_SS));

        Log.i(TAG, "前一天：" + DateUtilsCompat.getBeforeDate(DatePatternConstants.YYYY_MM_DD_HH_MM_SS));
        Log.i(TAG, "前一月：" + DateUtilsCompat.getBeforeMonth(DatePatternConstants.YYYY_MM_DD_HH_MM_SS));

        Log.i(TAG, "获取新的间隔年份日期：" + DateUtilsCompat.formatDate(DateUtilsCompat.getDateByIntervalYears(new Date(), -1), DatePatternConstants.YYYY_MM_DD));
        Log.i(TAG, "获取新的间隔月数日期：" + DateUtilsCompat.formatDate(DateUtilsCompat.getDateByIntervalMonths(new Date(), 1), DatePatternConstants.YYYY_MM_DD));
        Log.i(TAG, "获取新的间隔天数日期：" + DateUtilsCompat.formatDate(DateUtilsCompat.getDateByIntervalDays(new Date(), 3), DatePatternConstants.YYYY_MM_DD));


    }
}