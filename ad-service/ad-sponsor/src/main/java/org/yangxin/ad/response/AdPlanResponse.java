package org.yangxin.ad.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 推广计划
 *
 * @author yangxin
 * 2020/01/08 16:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanResponse {
    /**
     * id
     */
    private Long id;

    /**
     * 推广计划名称
     */
    private String planName;
}
