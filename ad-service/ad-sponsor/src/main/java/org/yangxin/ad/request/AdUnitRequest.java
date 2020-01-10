package org.yangxin.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * 推广单元
 *
 * @author yangxin
 * 2020/01/10 15:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitRequest {
    /**
     * 推广计划Id
     */
    private Long planId;

    /**
     * 推广单元名称
     */
    private String unitName;

    /**
     * 位置类型
     */
    private Integer positionType;

    /**
     * 预算
     */
    private Long budget;

    /**
     * 创建校验
     */
    public boolean createValidate() {
        return planId != null
                && !StringUtils.isEmpty(unitName)
                && positionType != null
                && budget != null;
    }
}
