package org.yangxin.ad.sender;

import org.yangxin.ad.mysql.dto.MySQLRowData;

/**
 * @author yangxin
 * 2020/07/28 21:16
 */
public interface Sender {

    void sender(MySQLRowData rowData);
}
