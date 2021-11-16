package com.kennie.example.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.kennie.library.utils.core.AppPathUtils;
import com.kennie.library.utils.core.AppUtils;
import com.kennie.library.utils.core.DateUtils;
import com.kennie.library.utils.core.RomUtils;

import java.util.Date;

public class ExampleActivity extends AppCompatActivity {

    private static String TAG = ExampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        Log.i(TAG, "APP名称：" + AppUtils.getAppName());
        Log.i(TAG, "APP版本名：" + AppUtils.getAppVersionName());
        Log.i(TAG, "APP安装路径：" + AppUtils.getAppInstallSourcePath());
        Log.i(TAG, "APP安装文件：" + AppUtils.getAppSourceFile().getName());

        Log.i(TAG, "当前日期与时间：" + DateUtils.getDateTime());
        Log.i(TAG, "当前日期：" + DateUtils.getDate());
        Log.i(TAG, "当前时间：" + DateUtils.getTime());
        Log.i(TAG, "当前时间：" + DateUtils.getTimeMillis());
        Log.i(TAG, "当前格式化时间：" + DateUtils.formatDate("yyyyMMdd"));
        Log.i(TAG, "当前格式化时间：" + DateUtils.formatDate(System.currentTimeMillis(), "HH:mm:ss"));
        Log.i(TAG, "当前格式化时间：" + DateUtils.formatDate(new Date(), "yyyyMMdd"));

        Log.i(TAG, "ROM：" + RomUtils.isXiaomi());


        Log.i(TAG, "获取APP应用数据路径：" + AppPathUtils.getAppDataPath());
        Log.i(TAG, "获取APP应用缓存路径：" + AppPathUtils.getAppCachePath());
        Log.i(TAG, "获取APP应用数据库路径：" + AppPathUtils.getAppDbPath());
        Log.i(TAG, "获取APP应用数据库路径：" + AppPathUtils.getAppDbPath("databaseName"));
        Log.i(TAG, "获取APP应用文件路径：" + AppPathUtils.getAppFilesPath());
        Log.i(TAG, "获取APP应用SP路径：" + AppPathUtils.getAppSpPath());

        Log.i(TAG, "获取APP：" + AppPathUtils.getExternalStoragePath());
        Log.i(TAG, "获取APP：" + AppPathUtils.getAppExternalDataPath());
        Log.i(TAG, "获取APP：" + AppPathUtils.getAppExternalOBBPath());

    }
}