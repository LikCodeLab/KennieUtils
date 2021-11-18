package com.kennie.library.utils.entity;

import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * @项目名 KennieUtils
 * @类名称 NotificationInfo
 * @类描述 通知信息
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/11/18 23:16
 */
public class NotificationInfo {

    private String groupId;
    private String groupName;
    private String channelId;
    private String channelName;
    private int importance;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public NotificationInfo(String groupId, String groupName, String channelId, String channelName) {
        this(groupId, groupName, channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
    }

    public NotificationInfo(String groupId, String groupName, String channelId, String channelName, int importance) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.channelId = channelId;
        this.channelName = channelName;
        this.importance = importance;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
