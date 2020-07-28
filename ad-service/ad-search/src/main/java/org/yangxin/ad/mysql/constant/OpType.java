package org.yangxin.ad.mysql.constant;

import com.github.shyiko.mysql.binlog.event.EventType;

/**
 * 操作类型
 *
 * @author yangxin
 * 2020/06/10 20:11
 */
public enum OpType {

    ADD, UPDATE, DELETE, OTHER;

    public static OpType to(EventType eventType) {
        switch (eventType) {
            case EXT_WRITE_ROWS:
                return ADD;
            case EXT_UPDATE_ROWS:
                return UPDATE;
            case EXT_DELETE_ROWS:
                return DELETE;
            default:
                return OTHER;
        }
    }
}
