package org.yangxin.ad.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.yangxin.ad.dump.table.*;
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
import org.yangxin.ad.index.district.UnitDistrictIndex;
import org.yangxin.ad.index.interest.UnitItIndex;
import org.yangxin.ad.index.keyword.UnitKeywordIndex;
import org.yangxin.ad.mysql.constant.OpType;
import org.yangxin.ad.utils.CommonUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 1. 索引之间存在着层级的划分，也就是依赖关系的划分
 * 2. 加载全量索引其实是增量索引“添加”的一种特殊实现
 *
 * @author yangxin
 * 2020/06/10 20:07
 */
@Slf4j
public class AdLevelDataHandler {

    /**
     * 处理层级2的索引，推广计划
     */
    public static void handleLevel2(AdPlanTable adPlanTable, OpType opType) {
        AdPlanObject adPlanObject = new AdPlanObject(adPlanTable.getId(),
                adPlanTable.getUserId(),
                adPlanTable.getPlanStatus(),
                adPlanTable.getStartDate(),
                adPlanTable.getEndDate());

        // 处理mysql二进制日志事件
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

    public static void handleLevel4(AdUnitDistrictTable adUnitDistrictTable, OpType opType) {
        if (opType == OpType.UPDATE) {
            log.error("district index can not support update");
            return;
        }

        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adUnitDistrictTable.getUnitId());
        if (adUnitObject == null) {
            log.error("AdUnitDistrictTable index error: [{}]", adUnitDistrictTable.getUnitId());
            return;
        }

        String key = CommonUtils.stringConcat(adUnitDistrictTable.getProvince(), adUnitDistrictTable.getCity());
        Set<Long> value = new HashSet<>(Collections.singleton(adUnitDistrictTable.getUnitId()));
        handleBinlogEvent(DataTable.of(UnitDistrictIndex.class), key, value, opType);
    }

    public static void handleLevel4(AdUnitItTable adUnitItTable, OpType opType) {
        if (opType == OpType.UPDATE) {
            log.error("it index can not support update");
            return;
        }

        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adUnitItTable.getUnitId());
        if (adUnitObject == null) {
            log.error("AdUnitItTable index error: [{}]", adUnitItTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(Collections.singletonList(adUnitItTable.getUnitId()));
        handleBinlogEvent(DataTable.of(UnitItIndex.class), adUnitItTable.getItTag(), value, opType);
    }

    public static void handleLevel4(AdUnitKeywordTable adUnitKeywordTable, OpType opType) {
        if (opType == OpType.UPDATE) {
            log.error("keyword index can not support update");
            return;
        }

        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adUnitKeywordTable.getUnitId());
        if (adUnitObject == null) {
            log.error("AdUnitKeywordTable index error: [{}]", adUnitKeywordTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(Collections.singleton(adUnitKeywordTable.getUnitId()));
        handleBinlogEvent(DataTable.of(UnitKeywordIndex.class), adUnitKeywordTable.getKeyword(), value, opType);
    }

    /**
     * 处理二进制日志事件
     */
    private static <K, V> void handleBinlogEvent(IndexAware<K, V> index, K key, V value, OpType opType) {
        switch (opType) {
            case ADD:
                // 添加索引
                index.add(key, value);
                break;
            case UPDATE:
                // 更新索引
                index.update(key, value);
                break;
            case DELETE:
                // 删除索引
                index.delete(key, value);
                break;
            default:
                break;
        }
    }
}
