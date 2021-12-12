package com.kennie.library.utils.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @项目名 KennieUtils
 * @类名称 FileEntity
 * @类描述
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/12 22:52
 */
public class FileEntity implements Parcelable {

    private String name;
    private long length;
    private String path;
    private String lastModified;//上一次修改日期
    private boolean isDirectory;//是否是文件

    public FileEntity(String name, long length, String path, String lastModified, boolean isDirectory) {
        this.name = name;
        this.length = length;
        this.path = path;
        this.lastModified = lastModified;
        this.isDirectory = isDirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", path='" + path + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", isDirectory=" + isDirectory +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeLong(this.length);
        dest.writeString(this.path);
        dest.writeString(this.lastModified);
        dest.writeByte(this.isDirectory ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.length = source.readLong();
        this.path = source.readString();
        this.lastModified = source.readString();
        this.isDirectory = source.readByte() != 0;
    }

    protected FileEntity(Parcel in) {
        this.name = in.readString();
        this.length = in.readLong();
        this.path = in.readString();
        this.lastModified = in.readString();
        this.isDirectory = in.readByte() != 0;
    }

    public static final Parcelable.Creator<FileEntity> CREATOR = new Parcelable.Creator<FileEntity>() {
        @Override
        public FileEntity createFromParcel(Parcel source) {
            return new FileEntity(source);
        }

        @Override
        public FileEntity[] newArray(int size) {
            return new FileEntity[size];
        }
    };
}
