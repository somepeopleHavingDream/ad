package org.yangxin.ad.index.adplan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 推广计划对象
 *
 * @author yangxin
 * 2020/05/13 20:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanObject {

    /**
     * 推广计划Id
     */
    private Long planId;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 计划状态
     */
    private Integer planStatus;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 更新
     */
    public void update(AdPlanObject adPlanObject) {
        if (adPlanObject == null) {
            return;
        }

        if (adPlanObject.getPlanId() != null) {
            this.planId = adPlanObject.getPlanId();
        }
        if (adPlanObject.getUserId() != null) {
            this.userId = adPlanObject.getUserId();
        }
        if (adPlanObject.getPlanStatus() != null) {
            this.planStatus = adPlanObject.getPlanStatus();
        }
        if (adPlanObject.getStartDate() != null) {
            this.startDate = adPlanObject.getStartDate();
        }
        if (adPlanObject.getEndDate() != null) {
            this.endDate = adPlanObject.getEndDate();
        }
    }
}
