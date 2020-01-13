package org.yangxin.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yangxin.ad.constant.CommonStatusEnum;
import org.yangxin.ad.entity.Creative;

import java.util.Date;

/**
 * 创意
 *
 * @author yangxin
 * 2020/01/10 17:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdCreativeRequest {
    /**
     * 创意名称
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 物料类型
     */
    private Integer materialType;

    /**
     * 高度
     */
    private Integer height;

    /**
     * 宽度
     */
    private Integer width;

    /**
     * 大小
     */
    private Long size;

    /**
     * 长度
     */
    private Integer duration;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * url
     */
    private String url;

    /**
     * 转换成Creative对象
     */
    public Creative convertToEntity() {
        return Creative.builder()
                .name(name)
                .type(type)
                .materialType(materialType)
                .height(height)
                .width(width)
                .size(size)
                .duration(duration)
                .auditStatus(CommonStatusEnum.VALID.getStatus())
                .userId(userId)
                .url(url)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }
}
