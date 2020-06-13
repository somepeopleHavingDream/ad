package org.yangxin.ad.service;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author yangxin
 * 2020/06/13 17:54
 */
@Slf4j
public class BinlogServiceTest {

    public static void main(String[] args) throws IOException {
        BinaryLogClient binaryLogClient = new BinaryLogClient("127.0.0.1",
                3306,
                "root",
                "123456");
        binaryLogClient.registerEventListener(event -> {
            EventData data = event.getData();
            if (data instanceof UpdateRowsEventData) {
                log.info("update, data: [{}]", data.toString());
            } else if (data instanceof WriteRowsEventData) {
                log.info("write, data: [{}]", data.toString());
            } else if (data instanceof DeleteRowsEventData) {
                log.info("delete, data: [{}]", data.toString());
            }
        });

        binaryLogClient.connect();
    }
}
