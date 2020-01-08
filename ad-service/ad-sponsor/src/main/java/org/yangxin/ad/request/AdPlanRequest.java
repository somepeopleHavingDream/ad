package org.yangxin.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * 推广计划
 *
 * @author yangxin
 * 2020/01/08 16:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanRequest {
    /**
     * id
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
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 创建校验
     */
    public boolean createValidate() {
        return userId != null
                && !StringUtils.isEmpty(planName)
                && !StringUtils.isEmpty(startDate)
                && !StringUtils.isEmpty(endDate);
    }

    /**
     * 更新校验
     */
    public boolean updateValidate() {
        return id != null && userId != null;
    }

    /**
     * 删除校验
     */
    public boolean deleteValidate() {
        return id != null && userId != null;
    }
}
