package org.yangxin.ad.mysql.listener;

import org.yangxin.ad.mysql.dto.BinlogRowData;

/**
 * @author yangxin
 * 2020/07/27 15:27
 */
public interface Listener {

    void register();

    void onEvent(BinlogRowData eventData);
}
