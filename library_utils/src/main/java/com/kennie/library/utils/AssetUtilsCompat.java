package com.kennie.library.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @项目名 KennieUtils
 * @类名称 AssetUtil
 * @类描述 assets文件目录
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 21:56
 */
public class AssetUtilsCompat {

    /**
     * 复制assets目录下的文件到指定目录,如复制到SD卡上，需申请WRITE_EXTERNAL_STORAGE权限
     *
     * @param fileName  Asset目录下的文件名
     * @param targetDir 复制文件目录
     * @return
     */
    public static boolean copyAsset(String fileName, String targetDir) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = KennieUtilInit.getAppContext().getResources().getAssets().open(fileName);
            if (!targetDir.endsWith(File.separator)) targetDir += File.separator;
            File parent = new File(targetDir);
            if (!parent.exists()) {
                parent.mkdirs();
            }
            out = new FileOutputStream(targetDir + fileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
