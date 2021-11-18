package com.kennie.library.utils.core;

import java.io.File;

/**
 * @项目名 KennieUtils
 * @类名称 FileUtil
 * @类描述
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 22:11
 */
public class FileUtil {

    /**
     * 删除指定目录
     *
     * @param dir 文件目录
     * @return
     */
    public static boolean deleteDir(File dir) {
        try {
            if (dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }

            return dir.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
