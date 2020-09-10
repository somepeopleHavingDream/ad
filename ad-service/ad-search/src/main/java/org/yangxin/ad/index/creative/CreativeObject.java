package org.yangxin.ad.index.creative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创意对象
 *
 * @author yangxin
 * 2020/05/23 12:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeObject {

    /**
     * 广告Id
     */
    private Long adId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 原材料类型
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
     * 视频状态
     */
    private Integer auditStatus;

    /**
     * url
     */
    private String adUrl;

    /**
     * 更新操作
     */
    public void update(CreativeObject newObject) {
        if (newObject.getAdId() != null) {
            this.adId = newObject.getAdId();
        }
        if (newObject.getName() != null) {
            this.name = newObject.getName();
        }
        if (newObject.getType() != null) {
            this.type = newObject.getType();
        }
        if (newObject.getMaterialType() != null) {
            this.materialType = newObject.getMaterialType();
        }
        if (newObject.getHeight() != null) {
            this.height = newObject.getHeight();
        }
        if (newObject.getWidth() != null) {
            this.width = newObject.getWidth();
        }
        if (newObject.getAuditStatus() != null) {
            this.auditStatus = newObject.getAuditStatus();
        }
        if (newObject.getAdUrl() != null) {
            this.adUrl = newObject.getAdUrl();
        }
    }
}
