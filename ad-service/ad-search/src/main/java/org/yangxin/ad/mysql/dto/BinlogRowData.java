package org.yangxin.ad.mysql.dto;

import com.github.shyiko.mysql.binlog.event.EventType;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author yangxin
 * 2020/07/27 15:23
 */
@Data
public class BinlogRowData {

    private TableTemplate table;
    private EventType eventType;
    private List<Map<String, String>> after;
    private List<Map<String, String>> before;
}
