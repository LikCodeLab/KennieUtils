package com.kennie.library.utils.ZOLD.core;

import com.kennie.library.utils.KennieUtilInit;

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
public class AssetUtil {

    /**
     * 复制assets目录下的文件到指定目录,如复制到SD卡上，需申请WRITE_EXTERNAL_STORAGE权限
     *
     * @param fileName 文件名
     * @param destDir  复制文件目录
     * @return
     */
    public static boolean copyAsset(String fileName, String destDir) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = KennieUtilInit.getApp().getResources().getAssets().open(fileName);
            if (!destDir.endsWith(File.separator)) destDir += File.separator;
            File parent = new File(destDir);
            if (!parent.exists()) {
                parent.mkdirs();
            }
            out = new FileOutputStream(destDir + fileName);
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
