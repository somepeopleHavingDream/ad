package org.yangxin.ad.sender.index;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yangxin.ad.dump.table.AdCreativeTable;
import org.yangxin.ad.dump.table.AdPlanTable;
import org.yangxin.ad.handler.AdLevelDataHandler;
import org.yangxin.ad.index.DataLevel;
import org.yangxin.ad.mysql.constant.Constant;
import org.yangxin.ad.mysql.dto.MySQLRowData;
import org.yangxin.ad.sender.Sender;
import org.yangxin.ad.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangxin
 * 2020/08/05 13:57
 */
@Slf4j
@Component("indexSender")
public class IndexSender implements Sender {

    @Override
    public void sender(MySQLRowData rowData) {
        String level = rowData.getLevel();
        if (Objects.equals(DataLevel.LEVEL2.getLevel(), level)) {
            level2RowData(rowData);
        } else if (Objects.equals(DataLevel.LEVEL3.getLevel(), level)) {
            // todo
        } else if (Objects.equals(DataLevel.LEVEL4.getLevel(), level)) {
            // todo
        } else {
            log.error("MySQLRowData ERROR: [{}]", JSON.toJSONString(rowData));
        }
    }

    private void level2RowData(MySQLRowData rowData) {
        if (Objects.equals(rowData.getTableName(), Constant.AD_PLAN_TABLE_INFO.TABLE_NAME)) {
            List<AdPlanTable> planTableList = new ArrayList<>();
            for (Map<String, String> fieldValueMap : rowData.getFieldValueMap()) {
                AdPlanTable planTable = new AdPlanTable();

                fieldValueMap.forEach((k, v) -> {
                    switch (k) {
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_ID:
                            planTable.setId(Long.valueOf(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_USER_ID:
                            planTable.setUserId(Long.valueOf(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_PLAN_STATUS:
                            planTable.setPlanStatus(Integer.valueOf(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_START_DATE:
                            planTable.setStartDate(CommonUtils.parseStringDate(v));
                            break;
                        case Constant.AD_PLAN_TABLE_INFO.COLUMN_END_DATE:
                            planTable.setEndDate(CommonUtils.parseStringDate(v));
                            break;
                    }
                });

                planTableList.add(planTable);
            }

            planTableList.forEach(p -> AdLevelDataHandler.handleLevel2(p, rowData.getOpType()));
        } else if (Objects.equals(rowData.getTableName(), Constant.AD_CREATIVE_TABLE_INFO.TABLE_NAME)) {
            List<AdCreativeTable> creativeTableList = new ArrayList<>();
            for (Map<String, String> fieldValueMap : rowData.getFieldValueMap()) {
                AdCreativeTable creativeTable = new AdCreativeTable();
                fieldValueMap.forEach((k, v) -> {
                    switch (k) {
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_ID:
                            creativeTable.setAdId(Long.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_TYPE:
                            creativeTable.setType(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_MATERIAL_TYPE:
                            creativeTable.setMaterialType(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_HEIGHT:
                            creativeTable.setHeight(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_WIDTH:
                            creativeTable.setWidth(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_AUDIT_STATUS:
                            creativeTable.setAuditStatus(Integer.valueOf(v));
                            break;
                        case Constant.AD_CREATIVE_TABLE_INFO.COLUMN_URL:
                            creativeTable.setAdUrl(v);
                            break;
                    }
                });

                creativeTableList.add(creativeTable);
            }

            creativeTableList.forEach(c -> AdLevelDataHandler.handleLevel2(c, rowData.getOpType()));
        }
    }
}
