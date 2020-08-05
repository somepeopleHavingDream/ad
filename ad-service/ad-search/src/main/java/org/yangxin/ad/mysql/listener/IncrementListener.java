package org.yangxin.ad.mysql.listener;

import com.github.shyiko.mysql.binlog.event.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yangxin.ad.mysql.constant.Constant;
import org.yangxin.ad.mysql.constant.OpType;
import org.yangxin.ad.mysql.dto.BinlogRowData;
import org.yangxin.ad.mysql.dto.MySQLRowData;
import org.yangxin.ad.mysql.dto.TableTemplate;
import org.yangxin.ad.sender.Sender;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangxin
 * 2020/07/28 21:18
 */
@Slf4j
@Component
public class IncrementListener implements Listener {

    @Resource(name = "indexSender")
    private Sender sender;

    private final AggregationListener aggregationListener;

    @Autowired
    public IncrementListener(AggregationListener aggregationListener) {
        this.aggregationListener = aggregationListener;
    }

    @Override
    @PostConstruct
    public void register() {
        log.info("IncrementListener register db and table info");

        Constant.table2DB.forEach((k, v) -> aggregationListener.register(v, k, this));
    }

    @Override
    public void onEvent(BinlogRowData eventData) {
        TableTemplate table = eventData.getTable();
        EventType eventType = eventData.getEventType();

        // 包装成最后需要投递的数据
        MySQLRowData rowData = new MySQLRowData();
        rowData.setTableName(table.getTableName());
        rowData.setLevel(eventData.getTable().getLevel());
        OpType opType = OpType.to(eventType);
        rowData.setOpType(opType);

        // 取出模板中该操作对应的字段列表
        List<String> fieldList = table.getOpTypeFieldSetMap().get(opType);
        if (null == fieldList) {
            log.warn("[{}] not support for [{}]", opType, table.getTableName());
        }

        for (Map<String, String> afterMap : eventData.getAfter()) {
            Map<String, String> map = new HashMap<>();
            for (Map.Entry<String, String> entry : afterMap.entrySet()) {
                String colName = entry.getKey();
                String colValue = entry.getValue();
                afterMap.put(colName, colValue);
            }

            rowData.getFieldValueMap().add(map);
        }

        sender.sender(rowData);
    }
}
