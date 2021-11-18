package com.kennie.example.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.kennie.library.utils.core.AlgorithmEncryptUtil;
import com.kennie.library.utils.core.AppMarketUtil;
import com.kennie.library.utils.core.AppPathUtil;
import com.kennie.library.utils.core.AppUtil;
import com.kennie.library.utils.core.DateUtil;
import com.kennie.library.utils.core.PhoneUtil;
import com.kennie.library.utils.core.RomUtil;

import org.w3c.dom.DocumentType;

import java.util.Date;

public class ExampleActivity extends AppCompatActivity {

    private static String TAG = ExampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        Log.i(TAG, "APP名称：" + AppUtil.getAppName());
        Log.i(TAG, "APP版本名：" + AppUtil.getAppVersionName());
        Log.i(TAG, "APP安装路径：" + AppUtil.getAppInstallSourcePath());
        Log.i(TAG, "APP安装文件：" + AppUtil.getAppSourceFile().getName());

        Log.i(TAG, "APP安装文件：" + AppUtil.isAppInstalled("com.jskjjx.cloudmeeting"));


        Log.i(TAG, "当前日期与时间：" + DateUtil.getDateTime());
        Log.i(TAG, "当前日期：" + DateUtil.getDate());
        Log.i(TAG, "当前时间：" + DateUtil.getTime());
        Log.i(TAG, "当前时间：" + DateUtil.getTimeMillis());
        Log.i(TAG, "当前格式化时间：" + DateUtil.formatDate("yyyyMMdd"));
        Log.i(TAG, "当前格式化时间：" + DateUtil.formatDate(System.currentTimeMillis(), "HH:mm:ss"));
        Log.i(TAG, "当前格式化时间：" + DateUtil.formatDate(new Date(), "yyyyMMdd"));

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

    }
}