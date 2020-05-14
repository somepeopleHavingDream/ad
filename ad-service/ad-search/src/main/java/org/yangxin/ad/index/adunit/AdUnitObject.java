package org.yangxin.ad.index.adunit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yangxin.ad.index.adplan.AdPlanObject;

/**
 * 推广单元对象
 * @author yangxin
 * 2020/05/14 19:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitObject {

    /**
     * 推广单元Id
     */
    private Long unitId;

    /**
     * 单元状态
     */
    private Integer unitStatus;

    /**
     * 位置类型
     */
    private Integer positionType;

    /**
     * 计划Id
     */
    private Long planId;

    private AdPlanObject adPlanObject;

    void update(AdUnitObject adUnitObject) {
        if (adUnitObject == null) {
            return;
        }

        if (adUnitObject.getUnitId() != null) {
            this.unitId = adUnitObject.getUnitId();
        }
        if (adUnitObject.getUnitStatus() != null) {
            this.unitStatus = adUnitObject.getUnitStatus();
        }
        if (adUnitObject.getPositionType() != null) {
            this.positionType = adUnitObject.getPositionType();
        }
        if (adUnitObject.getPlanId() != null) {
            this.planId = adUnitObject.getPlanId();
        }
        if (adUnitObject.getAdPlanObject() != null) {
            this.adPlanObject = adUnitObject.getAdPlanObject();
        }
    }
}
