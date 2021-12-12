package com.kennie.library.utils.network;

import com.kennie.library.utils.entity.NET_TYPE;

/**
 * @项目名 KennieUtils
 * @类名称 OnNetChangeListener
 * @类描述
 * @创建人 Kennie
 * @修改人
 * @创建时间 2021/12/13 0:25
 */
public interface OnNetChangeListener {
    void changed(NET_TYPE type, boolean isNormal);
}
