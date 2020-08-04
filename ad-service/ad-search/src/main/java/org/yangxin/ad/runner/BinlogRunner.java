package org.yangxin.ad.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yangxin.ad.mysql.BinlogClient;

/**
 * @author yangxin
 * 2020/08/04 17:21
 */
@Slf4j
@Component
public class BinlogRunner implements CommandLineRunner {

    private final BinlogClient client;

    @Autowired
    public BinlogRunner(BinlogClient client) {
        this.client = client;
    }

    @Override
    public void run(String... args) {
        log.info("Coming in BinlogRunner...");
        client.connect();
    }
}
