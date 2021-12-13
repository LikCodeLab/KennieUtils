package com.kennie.library.utils;

import android.text.TextUtils;
import android.util.Log;

import com.kennie.library.utils.config.DatePatternConstants;
import com.kennie.library.utils.entity.FileEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @项目名 KennieUtils
 * @类名称 FileUtilsCompat
 * @类描述 文件工具类
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/12 22:05
 *
 * <p>
 * --判断文件是否存在                                {@link #isExist(String path)}
 * --判断文件是否存在                                {@link #isExist(File file)}
 * --重命名文件                                     {@link #rename(String sourcePath, String targetPath)}
 * --删除指定目录                                   {@link #deleteDir(File dir)}
 * --删除文件                                       {@link #deleteFile(String fileName)}
 * --获取文件                                       {@link #getFile(String filePath)}
 * --获取指定文件的可读大小                            {@link #getFileAvailable(String filePath)}
 * --获取文件大小                                    {@link #getFileFormatSize(String filePath)} -- 格式化(单位BYTE, KB, MB, GB)
 * --获取指定文件夹的大小                             {@link #getDirectorySizes(String directoryPath)}
 * --获取文件夹大小                                  {@link #getDirectoryFormatSize(String directoryPath)} -- 格式化(单位BYTE, KB, MB, GB)
 * --获取指定目录下的文件和文件夹                      {@link #getFileDirectory(String directoryPath)}
 * --写入数据                                       {@link #writeFileData(String fileName, String content, boolean isCover)}
 * --读取文件内容                                    {@link #readFileData(String fileName)}
 * </p>
 */
public class FileUtilsCompat {

    private static final String TAG = FileUtilsCompat.class.getSimpleName();


    /**
     * 判断文件是否存在
     *
     * @param path 文件路径(包含文件名称)
     * @return 是否成功 true|false
     */
    public static boolean isExist(final String path) {
        if (TextUtils.isEmpty(path)) return false;
        return isExist(new File(path));
    }


    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return 是否成功 true|false
     */
    public static boolean isExist(final File file) {
        return file != null && file.exists();
    }


    /**
     * 重命名文件(文件夹)
     * 需读写权限
     * 1、如果目标路径下有相同的文件名称，则命名失败（或者移动失败）【文件重命名】；
     * 2、如果目标路径不存在，则文件从源路径移动到目标路径失败【文件移动重命名】；
     * 3、如果目标路径目录不为空，则文件夹修改失败【文件夹重命名】；
     *
     * @param sourcePath 源文件路径(包含文件名称)
     * @param targetPath 目标文件路径(包含文件名称)
     * @return 是否成功 true|false
     * @code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     * @code <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
     */
    public static boolean rename(String sourcePath, String targetPath) {
        boolean isCompleted = false;
        if (!TextUtils.isEmpty(sourcePath) && !TextUtils.isEmpty(targetPath)) {
            File sourceFile = new File(sourcePath);
            if (sourceFile.exists()) {
                File targetFile = new File(targetPath);
                isCompleted = sourceFile.renameTo(targetFile);
            }

        }
        return isCompleted;
    }


    /**
     * 删除指定目录
     *
     * @param dir 文件目录
     * @return 是否成功 true|false
     */
    public static boolean deleteDir(File dir) {
        try {
            if (dir == null) return false;
            if (dir.isDirectory()) {
                String[] children = dir.list();
                if (children == null) return false;
                for (String child : children) {
                    boolean success = deleteDir(new File(dir, child));
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


    /**
     * 删除文件
     *
     * @param fileName 文件路径(包含文件名称)
     * @return 是否删除成功 true|false
     */
    public static boolean deleteFile(String fileName) {
        boolean isCompleted = false;
        if (!TextUtils.isEmpty(fileName)) {
            File file = new File(fileName);
            if (file.exists()) {
                isCompleted = file.delete();
            }
        }
        return isCompleted;
    }


    /**
     * 获取文件
     *
     * @param filePath 文件路径
     * @return File文件
     */
    public static File getFile(String filePath) {
        if (isExist(filePath)) {
            return new File(filePath);
        }
        return null;
    }

    /**
     * 获取指定文件的可读大小
     *
     * @param filePath 文件路径
     * @return 文件总大小
     */
    public static long getFileAvailable(String filePath) {
        File file = new File(filePath);
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);// 使用FileInputStream读入file的数据流
                size = fis.available();// 文件的大小
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return size;
    }

    /**
     * 获取文件大小
     *
     * @param filePath 文件路径(包含文件名称)
     * @return 文件大小(单位BYTE, KB, MB, GB)
     */
    public static String getFileFormatSize(String filePath) {
        File file = new File(filePath);
        // 转换文件大小
        String fileSizeStr = "";
        if (file.exists() && file.isFile()) {
            long lengthSum = file.length();
            DecimalFormat df = new DecimalFormat("#.00");
            if (lengthSum < 1024) {
                fileSizeStr = df.format((double) lengthSum) + "B";
            } else if (lengthSum < 1048576) {
                fileSizeStr = df.format((double) lengthSum / 1024) + "K";
            } else if (lengthSum < 1073741824) {
                fileSizeStr = df.format((double) lengthSum / 1048576) + "M";
            } else {
                fileSizeStr = df.format((double) lengthSum / 1073741824) + "G";
            }
        }
        return fileSizeStr;
    }


    /**
     * 获取指定文件夹的大小
     *
     * @param directoryPath 文件夹路径
     * @return 文件夹大小
     */
    public static long getDirectorySizes(String directoryPath) {
        File file = new File(directoryPath);
        long size = 0;
        if (file.exists() && file.isDirectory()) {
            File[] fileList = file.listFiles();//文件夹目录下的所有文件
            if (fileList == null) {//4.2的模拟器空指针。
                return 0;
            }
            for (File value : fileList) {
                if (value.isDirectory()) {//判断是否父目录下还有子目录
                    size = size + getDirectorySizes(value.getAbsolutePath());///
                } else {
                    //*getFileSize(flist[i].getAbsolutePath())*/
                    size = size + value.length();///
                }
            }
        }
        return size;
    }

    /**
     * 获取文件夹大小
     *
     * @param directoryPath 文件夹路径
     * @return 文件夹大小(单位BYTE, KB, MB, GB)
     */
    public static String getDirectoryFormatSize(String directoryPath) {
        // 转换文件大小
        String directorySizeStr = "";
        long lengthSum = getDirectorySizes(directoryPath);
        DecimalFormat df = new DecimalFormat("#.00");
        if (lengthSum < 1024) {
            if (lengthSum == 0) {
                directorySizeStr = "0B";
            } else {
                directorySizeStr = df.format((double) lengthSum) + "B";
            }
        } else if (lengthSum < 1048576) {
            directorySizeStr = df.format((double) lengthSum / 1024) + "K";
        } else if (lengthSum < 1073741824) {
            directorySizeStr = df.format((double) lengthSum / 1048576) + "M";
        } else {
            directorySizeStr = df.format((double) lengthSum / 1073741824) + "G";
        }
        return directorySizeStr;
    }


    /**
     * 获取指定目录下的文件和文件夹
     *
     * @param directoryPath 文件夹路径
     * @return 文件夹下的列表
     */
    public static List<FileEntity> getFileDirectory(String directoryPath) {
        List<FileEntity> fileListEntityList = new ArrayList<>();
        File file = new File(directoryPath);
        if (file.exists() && file.isDirectory()) {
            File fileList[] = file.listFiles();//文件夹目录下的所有文件
            if (fileList != null) {
                for (int i = 0; i < fileList.length; i++) {
                    long size;
                    if (fileList[i].isDirectory()) {//判断是否父目录下还有子目录
                        size = getDirectorySizes(fileList[i].getAbsolutePath());///
                    } else {
                        //*getFileSize(flist[i].getAbsolutePath())*/
                        size = fileList[i].length();
                    }
                    //获取上次修改的时间
                    String lastModified = new SimpleDateFormat(DatePatternConstants.YYYY_MM_DD__HH_MM_SS, Locale.CHINA).format(new Date(fileList[i].lastModified()));
                    FileEntity fileEntity = new FileEntity(fileList[i].getName(), size, file.getAbsolutePath() + "/", lastModified, fileList[i].isDirectory());
                    fileListEntityList.add(fileEntity);
                }
            }
        }
        return fileListEntityList;
    }


    /**
     * 写入数据
     *
     * @param fileName 文件路径(包含文件名称)
     * @param content  写入的内容
     * @param isCover  是否覆盖文件的内容 true 覆盖原文件内容  | false 追加内容在最后
     * @return 是否成功 true|false
     */
    public static boolean writeFileData(String fileName, String content, boolean isCover) {
        FileOutputStream fos = null;
        try {
            File file = new File(fileName);
            //如果文件不存在
            if (!file.exists()) {
                //重新创建文件
                file.createNewFile();
            }
            fos = new FileOutputStream(file, !isCover);
            byte[] bytes = content.getBytes();
            fos.write(bytes);//将byte数组写入文件
            fos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "writeFileData: " + e.getMessage());
        } finally {
            try {
                fos.close();//关闭文件输出流
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "errMeg:" + e.getMessage());
            }
        }
        return false;
    }

    /**
     * 读取文件内容
     *
     * @param fileName 文件路径(包含文件名称)
     * @return 读取到的内容
     */
    public static String readFileData(String fileName) {
        String result = "";
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return "";
            }
            FileInputStream fis = new FileInputStream(file);
            //获取文件长度
            int length = fis.available();
            byte[] buffer = new byte[length];
            fis.read(buffer);
            fis.close();
            //将byte数组转换成指定格式的字符串
            result = new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "readFileData: " + e.getMessage());
        }
        return result;
    }


}
