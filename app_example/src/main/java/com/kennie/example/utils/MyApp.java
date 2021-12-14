package com.kennie.example.utils;

import android.app.Application;

import com.kennie.library.utils.KennieUtilInit;
import com.kennie.library.utils.ZOLD.MMKVConfig;

/**
 * @项目名 KennieUtils
 * @类名称 MyApp
 * @类描述
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 21:41
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KennieUtilInit.init(this);
        MMKVConfig.init(this);
    }

}
