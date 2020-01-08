package org.yangxin.ad.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 创意类型
 *
 * @author yangxin
 * 2020/01/08 14:54
 */
@Getter
@AllArgsConstructor
public enum CreativeTypeEnum {
    /**
     * 图片
     */
    IMAGE(1, "图片"),

    /**
     * 视频
     */
    VIDEO(2, "视频"),

    /**
     * 文本
     */
    TEXT(3, "文本");

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 描述
     */
    private final String description;
}
