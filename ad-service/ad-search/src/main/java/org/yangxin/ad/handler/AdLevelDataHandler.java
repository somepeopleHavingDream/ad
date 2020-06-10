package org.yangxin.ad.handler;

import lombok.extern.slf4j.Slf4j;
import org.yangxin.ad.index.IndexAware;
import org.yangxin.ad.mysql.constant.OpType;

/**
 * 1. 索引之间存在着层级的划分，也就是依赖关系的划分
 * 2. 加载全量索引其实是增量索引“添加”的一种特殊实现
 *
 * @author yangxin
 * 2020/06/10 20:07
 */
@Slf4j
public class AdLevelDataHandler {

    private static <K, V> void handleBinlogEvent(IndexAware<K, V> index, K key, V value, OpType opType) {
        switch (opType) {
            case ADD:
                index.add(key, value);
                break;
            case UPDATE:
                index.update(key, value);
                break;
            case DELETE:
                index.delete(key, value);
                break;
            default:
                break;
        }
    }
}
