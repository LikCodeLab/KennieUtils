package com.kennie.library.utils.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @项目名 KennieUtils
 * @类名称 RomInfo
 * @类描述 ROM信息
 * @创建人 Administrator
 * @修改人
 * @创建时间 2021/11/16 22:28
 */
public class RomInfo implements Parcelable {

    public String name;
    public String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "RomInfo{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.version);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.version = source.readString();
    }

    public RomInfo() {
    }

    protected RomInfo(Parcel in) {
        this.name = in.readString();
        this.version = in.readString();
    }

    public static final Parcelable.Creator<RomInfo> CREATOR = new Parcelable.Creator<RomInfo>() {
        @Override
        public RomInfo createFromParcel(Parcel source) {
            return new RomInfo(source);
        }

        @Override
        public RomInfo[] newArray(int size) {
            return new RomInfo[size];
        }
    };
}
