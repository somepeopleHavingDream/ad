package org.yangxin.ad.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 推广计划
 *
 * @author yangxin
 * 2020/01/14 16:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanResponse {
    /**
     * 推广计划Id
     */
    private Long id;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 推广计划名称
     */
    private String planName;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
