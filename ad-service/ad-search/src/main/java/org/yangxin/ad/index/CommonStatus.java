package org.yangxin.ad.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yangxin
 * 2020/08/21 10:17
 */
@Getter
@AllArgsConstructor
public enum CommonStatus {

    VALID(1, "有效状态"),
    INVALID(0, "无效状态");

    private final Integer status;
    private final String desc;
}
