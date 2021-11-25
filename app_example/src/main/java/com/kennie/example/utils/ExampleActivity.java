package com.kennie.example.utils;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.kennie.library.utils.core.DateUtil;

import java.util.Date;

public class ExampleActivity extends AppCompatActivity {

    private static String TAG = ExampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        // DateUtils
        Log.i(TAG, "当前缺省格式日期与时间：" + DateUtil.getCurrentDate());
        Log.i(TAG, "当前指定格式日期与时间：" + DateUtil.getCurrentDate(DateUtil.DEFAULT_TIME_PATTERN));
        Log.i(TAG, "当前时间戳：" + DateUtil.getTimeMillis());
        Log.i(TAG, "当前格式化时间：" + DateUtil.formatDate(System.currentTimeMillis(), "yyyy/MM/dd HH:mm:ss"));
        Log.i(TAG, "当前格式化时间：" + DateUtil.formatDate(new Date(), DateUtil.DEFAULT_DATE_PATTERN_YMD));

        Log.i(TAG, "前一天：" + DateUtil.getBeforeDate(DateUtil.DEFAULT_DATE_PATTERN));
        Log.i(TAG, "前一月：" + DateUtil.getBeforeMonth(DateUtil.DEFAULT_DATE_PATTERN));

        Log.i(TAG, "获取新的间隔年份日期：" + DateUtil.formatDate(DateUtil.getDateByIntervalYears(new Date(), -1), DateUtil.DEFAULT_DATE_PATTERN_YMD));
        Log.i(TAG, "获取新的间隔月数日期：" + DateUtil.formatDate(DateUtil.getDateByIntervalMonths(new Date(), 1), DateUtil.DEFAULT_DATE_PATTERN_YMD));
        Log.i(TAG, "获取新的间隔天数日期：" + DateUtil.formatDate(DateUtil.getDateByIntervalDays(new Date(), 3), DateUtil.DEFAULT_DATE_PATTERN_YMD));


        //        Log.i(TAG, "APP名称：" + AppUtil.getAppName());
//        Log.i(TAG, "APP版本名：" + AppUtil.getAppVersionName());
//        Log.i(TAG, "APP安装路径：" + AppUtil.getAppInstallSourcePath());
//        Log.i(TAG, "APP安装文件：" + AppUtil.getAppSourceFile().getName());
//
//        Log.i(TAG, "APP安装文件：" + AppUtil.isAppInstalled("com.jskjjx.cloudmeeting"));


//        Log.i(TAG, "ROM：" + RomUtil.isXiaomi());

//
//        Log.i(TAG, "获取APP应用数据路径：" + AppPathUtil.getAppDataPath());
//        Log.i(TAG, "获取APP应用缓存路径：" + AppPathUtil.getAppCachePath());
//        Log.i(TAG, "获取APP应用数据库路径：" + AppPathUtil.getAppDbPath());
//        Log.i(TAG, "获取APP应用数据库路径：" + AppPathUtil.getAppDbPath("databaseName"));
//        Log.i(TAG, "获取APP应用文件路径：" + AppPathUtil.getAppFilesPath());
//        Log.i(TAG, "获取APP应用SP路径：" + AppPathUtil.getAppSpPath());
//
//        Log.i(TAG, "获取APP：" + AppPathUtil.getExternalStoragePath());
//        Log.i(TAG, "获取APP：" + AppPathUtil.getAppExternalDataPath());
//        Log.i(TAG, "获取APP：" + AppPathUtil.getAppExternalOBBPath());
//        Log.i(TAG, "获取APP：" + AppPathUtil.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
//
//
//        Log.i(TAG, "获取APP：" + PhoneUtil.isPhone());
//
//
//        Log.i(TAG, "加密后的：" + AlgorithmEncryptUtil.getMD5("12345"));
//        Log.i(TAG, "加密后的：" + AlgorithmEncryptUtil.getHash("12345", AlgorithmEncryptUtil.SHA1));
//        Log.i(TAG, "加密后的：" + AlgorithmEncryptUtil.getHash("12345", AlgorithmEncryptUtil.SHA256));
//
//        Log.i(TAG, "获取APP市场：" + AppMarketUtil.isMarketAvailable());

    }
}