package com.kennie.library.utils.temp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @项目名 KennieUtils
 * @类名称 PhoneSystemUtils
 * @类描述 系统的工具类
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/5 20:22
 */
public class PhoneSystemUtils {




    /**
     * 获取总内存大小，单位是byte
     */
    public static long getTotalMemSize() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("/proc/meminfo"));
            char[] info = br.readLine().toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : info) {
                if (c >= '0' && c <= '9') {
                    sb.append(c);
                }
            }
            long kbSize = Long.parseLong(sb.toString());
            br.close();
            return kbSize * 1024;
        } catch (Exception ignore) {
        }
        return 0;
    }

    /**
     * 存储卡是否可用
     */
    public static boolean isSdCardAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取内置存储卡剩余存储空间
     */
    public static long getInternalFreeSpace() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        return stat.getAvailableBlocksLong() * stat.getBlockSizeLong();
    }

    /**
     * 获取扩展卡剩余存储空间
     */
    public static long getExternalFreeSpace() {
        if (isSdCardAvailable()) {
            StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            return stat.getAvailableBlocksLong() * stat.getBlockSizeLong();
        } else {
            return 0;
        }
    }


    /**
     * 获取存储设备剩余大小
     */
    public static long getStorageFreeSpace(@NonNull String path) {
        if (new File(path).exists()) {
            StatFs stat = new StatFs(path);
            return stat.getAvailableBlocksLong() * stat.getBlockSizeLong();
        }
        return 0;
    }

    /**
     * 存储设备总容量
     */
    public static long getStorageTotalSpace(@NonNull String path) {
        if (new File(path).exists()) {
            StatFs stat = new StatFs(path);
            return stat.getBlockSizeLong() * stat.getBlockCountLong();
        }
        return 0;
    }

    /**
     * 判断当前系统是否安装指定的应用
     *
     * @param packageName 要判断的应用包名
     */
    public static boolean isAppInstalled(@NonNull Context context, @NonNull String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_GIDS) != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 判断是否是系统应用
     *
     * @param packageName 要判断的应用包名
     */
    public static boolean isSystemApp(@NonNull Context context, @NonNull String packageName) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_GIDS);
            return info != null && (info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    /**
     * 判断位置服务是否打开
     */
    public static boolean isLocationEnabled(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            LocationManager locationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            if (locationManager != null) {
                return locationManager.isLocationEnabled();
            }
        } else {
            try {
                int locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
                return locationMode != Settings.Secure.LOCATION_MODE_OFF;
            } catch (Exception ignore) {
            }
        }
        return false;
    }

    /**
     * 判断GPS是否打开
     */
    public static boolean isGPSEnabled(@NonNull Context context) {
        LocationManager locationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 判断屏幕是否亮着
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    public static boolean isScreenOn(@NonNull Context context) {
        PowerManager powerManager = (PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE);
        return powerManager != null && powerManager.isInteractive();
    }

    /**
     * 获取可用内存大小，单位byte
     */
    public static long getAvailMemSize(@NonNull Context context) {
        ActivityManager am = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
        if (am == null) {
            return -1;
        }
        am.getMemoryInfo(outInfo);
        return outInfo.availMem;
    }

    /**
     * 获取所有存储路径
     */
    @NonNull
    public static List<String> getStoragePaths(@NonNull Context context) {
        try {
            StorageManager sm = (StorageManager) context.getApplicationContext().getSystemService(Context.STORAGE_SERVICE);
            String[] volumePaths = (String[]) Objects.requireNonNull(sm).getClass().getMethod("getVolumePaths").invoke(sm);
            List<String> pathList = new ArrayList<>();
            for (String path : volumePaths) {
                if (getStorageTotalSpace(path) > 0) {
                    pathList.add(path);
                }
            }
            return pathList;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * 存储器是否被挂载
     */
    public static boolean isMounted(@NonNull Context context, @NonNull String path) {
        try {
            StorageManager sm = (StorageManager) context.getApplicationContext().getSystemService(Context.STORAGE_SERVICE);
            Objects.requireNonNull(sm);
            String state = (String) sm.getClass().getMethod("getVolumeState", String.class).invoke(sm, path);
            return Environment.MEDIA_MOUNTED.equals(state);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断apk是否是debug包
     */
    public static boolean isDebugApk(@NonNull Context context, @NonNull String apkPath) {
        PackageInfo info = context.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        try {
            return (info.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断app是否运行在debug模式下
     */
    public static boolean isRunInDebug(@NonNull Context context) {
        try {
            return (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static void goNotificationSetting(@NonNull Context context) {
        Intent intent = new Intent();
        //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, context.getApplicationInfo().uid);
        } else {
            //这种方案适用于 API21——25，即 5.0——7.1 之间的版本可以使用
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            context.startActivity(new Intent(Settings.ACTION_SETTINGS));
        }
    }

}
