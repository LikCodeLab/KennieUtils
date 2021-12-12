package com.kennie.library.utils;

import android.text.TextUtils;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.util.InternalZipConstants;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @项目名 KennieUtils
 * @类名称 ZipUtilsCompat
 * @类描述 压缩相关工具类
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/13 0:51
 *
 * <p>
 * ==================================zipFile 压缩文件方法* ==================================
 * --压缩文件                                            {@link #zipFile(String resFilePath, String zipFilePath)}
 * ==================================zipFiles 批量压缩文件方法* ==================================
 * </p>
 */
public class ZipUtilsCompat {


    // ==================================zipFiles 批量压缩文件方法* ==================================


    /**
     * 批量压缩文件
     *
     * @param resFiles    待压缩文件集合
     * @param zipFilePath 压缩文件路径
     * @return {@code true}: 压缩成功<br>{@code false}: 压缩失败
     * @throws IOException IO错误时抛出
     */
    public static boolean zipFiles(Collection<File> resFiles, String zipFilePath)
            throws IOException {
        return zipFiles(resFiles, zipFilePath, null);
    }

    /**
     * 批量压缩文件
     *
     * @param resFiles    待压缩文件集合
     * @param zipFilePath 压缩文件路径
     * @param comment     压缩文件的注释
     * @return {@code true}: 压缩成功<br>{@code false}: 压缩失败
     * @throws IOException IO错误时抛出
     */
    public static boolean zipFiles(Collection<File> resFiles, String zipFilePath, String comment)
            throws IOException {
        return zipFiles(resFiles, FileUtilsCompat.getFile(zipFilePath), comment);
    }

    /**
     * 批量压缩文件
     *
     * @param resFiles 待压缩文件集合
     * @param zipFile  压缩文件
     * @return {@code true}: 压缩成功<br>{@code false}: 压缩失败
     * @throws IOException IO错误时抛出
     */
    public static boolean zipFiles(Collection<File> resFiles, File zipFile)
            throws IOException {
        return zipFiles(resFiles, zipFile, null);
    }


    /**
     * 批量压缩文件
     *
     * @param resFiles 待压缩文件集合
     * @param zipFile  压缩文件
     * @param comment  压缩文件的注释
     * @return {@code true}: 压缩成功<br>{@code false}: 压缩失败
     * @throws IOException IO错误时抛出
     */
    public static boolean zipFiles(Collection<File> resFiles, File zipFile, String comment)
            throws IOException {
        if (resFiles == null || zipFile == null) {
            return false;
        }
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
            for (File resFile : resFiles) {
                if (!zipFile(resFile, "", zos, comment)) {
                    return false;
                }
            }
            return true;
        } finally {
            if (zos != null) {
                zos.finish();
                closeIO(zos);
            }
        }
    }

    // ==================================zipFile 压缩文件方法* ==================================

    /**
     * 压缩文件
     *
     * @param resFilePath 待压缩文件路径
     * @param zipFilePath 压缩文件路径
     * @return {@code true}: 压缩成功<br>{@code false}: 压缩失败
     * @throws IOException IO错误时抛出
     */
    public static boolean zipFile(String resFilePath, String zipFilePath)
            throws IOException {
        return zipFile(resFilePath, zipFilePath, null);
    }


    /**
     * 压缩文件
     *
     * @param resFilePath 待压缩文件路径
     * @param zipFilePath 压缩文件路径
     * @param comment     压缩文件的注释
     * @return {@code true}: 压缩成功<br>{@code false}: 压缩失败
     * @throws IOException IO错误时抛出
     */
    public static boolean zipFile(String resFilePath, String zipFilePath, String comment)
            throws IOException {
        return zipFile(FileUtilsCompat.getFile(resFilePath), FileUtilsCompat.getFile(zipFilePath), comment);
    }


    /**
     * 压缩文件
     *
     * @param resFile 待压缩文件
     * @param zipFile 压缩文件
     * @return {@code true}: 压缩成功<br>{@code false}: 压缩失败
     * @throws IOException IO错误时抛出
     */
    public static boolean zipFile(File resFile, File zipFile)
            throws IOException {
        return zipFile(resFile, zipFile, null);
    }


    /**
     * 压缩文件
     *
     * @param resFile 待压缩文件
     * @param zipFile 压缩文件
     * @param comment 压缩文件的注释
     * @return {@code true}: 压缩成功<br>{@code false}: 压缩失败
     * @throws IOException IO错误时抛出
     */
    public static boolean zipFile(File resFile, File zipFile, String comment)
            throws IOException {
        if (resFile == null || zipFile == null) {
            return false;
        }
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
            return zipFile(resFile, "", zos, comment);
        } finally {
            if (zos != null) {
                zos.finish();
                closeIO(zos);
            }
        }
    }


    /**
     * 压缩文件
     *
     * @param resFile  待压缩文件
     * @param rootPath 相对于压缩文件的路径
     * @param zos      压缩文件输出流
     * @param comment  压缩文件的注释
     * @return {@code true}: 压缩成功<br>{@code false}: 压缩失败
     * @throws IOException IO错误时抛出
     */
    private static boolean zipFile(File resFile, String rootPath, ZipOutputStream zos, String comment)
            throws IOException {
        rootPath = rootPath + (TextUtils.isEmpty(rootPath) ? "" : File.separator) + resFile.getName();
        if (resFile.isDirectory()) {
            File[] fileList = resFile.listFiles();
            // 如果是空文件夹那么创建它，我把'/'换为File.separator测试就不成功，eggPain
            if (fileList.length <= 0) {
                ZipEntry entry = new ZipEntry(rootPath + '/');
                if (!TextUtils.isEmpty(comment)) {
                    entry.setComment(comment);
                }
                zos.putNextEntry(entry);
                zos.closeEntry();
            } else {
                for (File file : fileList) {
                    // 如果递归返回false则返回false
                    if (!zipFile(file, rootPath, zos, comment)) {
                        return false;
                    }
                }
            }
        } else {
            InputStream is = null;
            try {
                is = new BufferedInputStream(new FileInputStream(resFile));
                ZipEntry entry = new ZipEntry(rootPath);
                if (!TextUtils.isEmpty(comment)) {
                    entry.setComment(comment);
                }
                zos.putNextEntry(entry);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer, 0, 1024)) != -1) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
            } finally {
                closeIO(is);
            }
        }
        return true;
    }


    // ==================================unzipFiles 批量解压文件方法* ==================================

    /**
     * 批量解压文件
     *
     * @param zipFiles    压缩文件集合
     * @param destDirPath 目标目录路径
     * @return {@code true}: 解压成功<br>{@code false}: 解压失败
     * @throws IOException IO错误时抛出
     */
    public static boolean unzipFiles(Collection<File> zipFiles, String destDirPath) {
        return unzipFiles(zipFiles, FileUtilsCompat.getFile(destDirPath));
    }

    /**
     * 批量解压文件
     *
     * @param zipFiles 压缩文件集合
     * @param destDir  目标目录
     * @return {@code true}: 解压成功<br>{@code false}: 解压失败
     * @throws IOException IO错误时抛出
     */
    public static boolean unzipFiles(Collection<File> zipFiles, File destDir) {
        if (zipFiles == null || destDir == null) {
            return false;
        }
        for (File zipFile : zipFiles) {
            if (!unzipFile(zipFile, destDir)) {
                return false;
            }
        }
        return true;
    }


    // ==================================unzipFile 解压文件方法* ==================================

    /**
     * 解压文件
     *
     * @param zipFilePath 待解压文件路径
     * @param destDirPath 目标目录路径
     * @return {@code true}: 解压成功<br>{@code false}: 解压失败
     * @throws IOException IO错误时抛出
     */
    public static boolean unzipFile(String zipFilePath, String destDirPath) {
        return unzipFile(FileUtilsCompat.getFile(zipFilePath), FileUtilsCompat.getFile(destDirPath));
    }

    /**
     * 解压文件
     *
     * @param zipFile 待解压文件
     * @param destDir 目标目录
     * @return {@code true}: 解压成功<br>{@code false}: 解压失败
     * @throws IOException IO错误时抛出
     */
    public static boolean unzipFile(File zipFile, File destDir) {
        return unzipFileByKeyword(zipFile, destDir, null) != null;
    }


    /**
     * 解压带有关键字的文件
     *
     * @param zipFilePath 待解压文件路径
     * @param destDirPath 目标目录路径
     * @param keyword     关键字
     * @return 返回带有关键字的文件链表
     * @throws IOException IO错误时抛出
     */
    public static List<File> unzipFileByKeyword(String zipFilePath, String destDirPath, String keyword) {
        return unzipFileByKeyword(FileUtilsCompat.getFile(zipFilePath),
                FileUtilsCompat.getFile(destDirPath), keyword);
    }


    /**
     * 根据所给密码解压zip压缩包到指定目录
     * <p>
     * 如果指定目录不存在,可以自动创建,不合法的路径将导致异常被抛出
     *
     * @param zipFile zip压缩包绝对路径
     * @param destDir 指定解压文件夹位置
     * @param passwd  密码(可为空)
     * @return 解压后的文件数组
     * @throws ZipException
     */
    @SuppressWarnings("unchecked")
    public static List<File> unzipFileByKeyword(File zipFile, File destDir, String passwd) {
        try {
            //1.判断指定目录是否存在
            if (zipFile == null) {
                throw new ZipException("压缩文件不存在.");
            }
            if (destDir == null) {
                throw new ZipException("解压缩路径不存在.");
            }

            if (destDir.isDirectory() && !destDir.exists()) {
                destDir.mkdir();
            }

            //2.初始化zip工具
            net.lingala.zip4j.ZipFile zFile = new net.lingala.zip4j.ZipFile(zipFile);
            zFile.setCharset(InternalZipConstants.CHARSET_UTF_8);
            if (!zFile.isValidZipFile()) {
                throw new ZipException("压缩文件不合法,可能被损坏.");
            }
            //3.判断是否已加密
            if (zFile.isEncrypted()) {
                zFile.setPassword(passwd.toCharArray());
            }
            //4.解压所有文件
            zFile.extractAll(destDir.getAbsolutePath());
            List<FileHeader> headerList = zFile.getFileHeaders();
            List<File> extractedFileList = new ArrayList<File>();
            for (FileHeader fileHeader : headerList) {
                if (!fileHeader.isDirectory()) {
                    extractedFileList.add(new File(destDir, fileHeader.getFileName()));
                }
            }
            return extractedFileList;
        } catch (ZipException e) {
            e.printStackTrace();
            return null;
        }
    }


    // ==================================other 其它方法* ==================================


    /**
     * 获取压缩文件中的文件路径链表
     *
     * @param zipFilePath 压缩文件路径
     * @return 压缩文件中的文件路径链表
     * @throws IOException IO错误时抛出
     */
    public static List<String> getFilesPath(String zipFilePath)
            throws IOException {
        return getFilesPath(FileUtilsCompat.getFile(zipFilePath));
    }

    /**
     * 获取压缩文件中的文件路径链表
     *
     * @param zipFile 压缩文件
     * @return 压缩文件中的文件路径链表
     * @throws IOException IO错误时抛出
     */
    public static List<String> getFilesPath(File zipFile)
            throws IOException {
        if (zipFile == null) {
            return null;
        }
        List<String> paths = new ArrayList<>();
        Enumeration<?> entries = getEntries(zipFile);
        while (entries.hasMoreElements()) {
            paths.add(((ZipEntry) entries.nextElement()).getName());
        }
        return paths;
    }


    /**
     * 获取压缩文件中的注释链表
     *
     * @param zipFilePath 压缩文件路径
     * @return 压缩文件中的注释链表
     * @throws IOException IO错误时抛出
     */
    public static List<String> getComments(String zipFilePath)
            throws IOException {
        return getComments(FileUtilsCompat.getFile(zipFilePath));
    }


    /**
     * 获取压缩文件中的注释链表
     *
     * @param zipFile 压缩文件
     * @return 压缩文件中的注释链表
     * @throws IOException IO错误时抛出
     */
    public static List<String> getComments(File zipFile)
            throws IOException {
        if (zipFile == null) {
            return null;
        }
        List<String> comments = new ArrayList<>();
        Enumeration<?> entries = getEntries(zipFile);
        while (entries.hasMoreElements()) {
            ZipEntry entry = ((ZipEntry) entries.nextElement());
            comments.add(entry.getComment());
        }
        return comments;
    }


    /**
     * 获取压缩文件中的文件对象
     *
     * @param zipFilePath 压缩文件路径
     * @return 压缩文件中的文件对象
     * @throws IOException IO错误时抛出
     */
    public static Enumeration<?> getEntries(String zipFilePath)
            throws IOException {
        return getEntries(FileUtilsCompat.getFile(zipFilePath));
    }

    /**
     * 获取压缩文件中的文件对象
     *
     * @param zipFile 压缩文件
     * @return 压缩文件中的文件对象
     * @throws IOException IO错误时抛出
     */
    public static Enumeration<?> getEntries(File zipFile)
            throws IOException {
        if (zipFile == null) {
            return null;
        }
        return new ZipFile(zipFile).entries();
    }


    
    /**
     * 关闭IO流
     *
     * @param closeables
     */
    private static void closeIO(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        try {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    closeable.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
