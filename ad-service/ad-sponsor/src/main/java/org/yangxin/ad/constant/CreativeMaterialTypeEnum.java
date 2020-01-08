package org.yangxin.ad.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 创意物料类型
 *
 * @author yangxin
 * 2020/01/08 15:08
 */
@Getter
@AllArgsConstructor
public enum CreativeMaterialTypeEnum {
    /**
     * 图片
     */
    JPG(1, "jpg"),
    BMP(2, "bmp"),

    /**
     * 视频
     */
    MP4(3, "mp4"),
    AVI(4, "avi"),

    /**
     * 文档
     */
    TXT(5, "txt");

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 描述
     */
    private final String description;
}
