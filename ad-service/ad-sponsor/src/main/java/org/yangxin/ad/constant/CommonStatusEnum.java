package org.yangxin.ad.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用状态
 *
 * @author yangxin
 * 2020/01/07 17:46
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum {
    /**
     * 有效状态
     */
    VALID(1, "有效状态"),

    /**
     * 无效状态
     */
    INVALID(0, "无效状态");

    /**
     * 状态码
     */
    private final Integer status;

    /**
     * 描述信息
     */
    private final String description;
}
