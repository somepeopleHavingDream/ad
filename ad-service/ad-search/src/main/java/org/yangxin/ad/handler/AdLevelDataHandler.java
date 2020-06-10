package org.yangxin.ad.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.yangxin.ad.dump.table.AdCreativeTable;
import org.yangxin.ad.dump.table.AdCreativeUnitTable;
import org.yangxin.ad.dump.table.AdPlanTable;
import org.yangxin.ad.dump.table.AdUnitTable;
import org.yangxin.ad.index.DataTable;
import org.yangxin.ad.index.IndexAware;
import org.yangxin.ad.index.adplan.AdPlanIndex;
import org.yangxin.ad.index.adplan.AdPlanObject;
import org.yangxin.ad.index.adunit.AdUnitIndex;
import org.yangxin.ad.index.adunit.AdUnitObject;
import org.yangxin.ad.index.creative.CreativeIndex;
import org.yangxin.ad.index.creative.CreativeObject;
import org.yangxin.ad.index.creativeunit.CreativeUnitIndex;
import org.yangxin.ad.index.creativeunit.CreativeUnitObject;
import org.yangxin.ad.mysql.constant.OpType;
import org.yangxin.ad.utils.CommonUtils;

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

    public static void handleLevel3(AdUnitTable adUnitTable, OpType opType) {
        AdPlanObject adPlanObject = DataTable.of(AdPlanIndex.class).get(adUnitTable.getPlanId());
        if (adPlanObject == null) {
            log.error("handleLevel3 found AdPlanObject error: [{}]", adUnitTable.getPlanId());
            return;
        }

        AdUnitObject adUnitObject = new AdUnitObject(adUnitTable.getUnitId(),
                adUnitTable.getUnitStatus(),
                adUnitTable.getPositionType(),
                adUnitTable.getPlanId(),
                adPlanObject);

        handleBinlogEvent(DataTable.of(AdUnitIndex.class), adUnitTable.getUnitId(), adUnitObject, opType);
    }

    public static void handleLevel3(AdCreativeUnitTable adCreativeUnitTable, OpType opType) {
        if (OpType.UPDATE == opType) {
            log.error("AdCreativeUnitIndex not support update");
            return;
        }

        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adCreativeUnitTable.getUnitId());
        CreativeObject creativeObject = DataTable.of(CreativeIndex.class).get(adCreativeUnitTable.getAdId());
        if (adUnitObject == null || creativeObject == null) {
            log.error("AdCreativeUnitTable index error: [{}]", JSON.toJSON(adCreativeUnitTable));
            return;
        }

        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(adCreativeUnitTable.getAdId(),
                adCreativeUnitTable.getUnitId());
        handleBinlogEvent(DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(creativeUnitObject.getAdId().toString(),
                        creativeUnitObject.getUnitId().toString()),
                creativeUnitObject,
                opType);
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
