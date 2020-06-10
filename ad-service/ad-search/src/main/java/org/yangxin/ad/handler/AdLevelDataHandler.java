package org.yangxin.ad.handler;

import lombok.extern.slf4j.Slf4j;
import org.yangxin.ad.dump.table.AdCreativeTable;
import org.yangxin.ad.dump.table.AdPlanTable;
import org.yangxin.ad.index.DataTable;
import org.yangxin.ad.index.IndexAware;
import org.yangxin.ad.index.adplan.AdPlanIndex;
import org.yangxin.ad.index.adplan.AdPlanObject;
import org.yangxin.ad.index.creative.CreativeIndex;
import org.yangxin.ad.index.creative.CreativeObject;
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

    public static void handleLevel2(AdPlanTable adPlanTable, OpType opType) {
        AdPlanObject adPlanObject = new AdPlanObject(adPlanTable.getId(),
                adPlanTable.getUserId(),
                adPlanTable.getPlanStatus(),
                adPlanTable.getStartDate(),
                adPlanTable.getEndDate());
        handleBinlogEvent(DataTable.of(AdPlanIndex.class), adPlanObject.getPlanId(), adPlanObject, opType);
    }

    public static void handleLevel2(AdCreativeTable adCreativeTable, OpType opType) {
        CreativeObject creativeObject = new CreativeObject(adCreativeTable.getAdId(),
                adCreativeTable.getName(),
                adCreativeTable.getType(),
                adCreativeTable.getMaterialType(),
                adCreativeTable.getHeight(),
                adCreativeTable.getWidth(),
                adCreativeTable.getAuditStatus(),
                adCreativeTable.getAdUrl());
        handleBinlogEvent(DataTable.of(CreativeIndex.class), creativeObject.getAdId(), creativeObject, opType);
    }

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
