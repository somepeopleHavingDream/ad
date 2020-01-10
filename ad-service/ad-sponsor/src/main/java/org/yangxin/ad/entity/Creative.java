package org.yangxin.ad.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 创意
 *
 * @author yangxin
 * 2020/01/08 14:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_creative")
@Builder
public class Creative {
    /**
     * 创意id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 创意名称
     */
    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 创意类型
     */
    @Basic
    @Column(name = "type", nullable = false)
    private Integer type;

    /**
     * 物料类型，比如图片可以是bmp，jpg等等
     */
    @Basic
    @Column(name = "material_type", nullable = false)
    private Integer materialType;

    /**
     * 高
     */
    @Basic
    @Column(name = "height", nullable = false)
    private Integer height;

    /**
     * 宽
     */
    @Basic
    @Column(name = "width", nullable = false)
    private Integer width;

    /**
     * 大小
     */
    @Basic
    @Column(name = "size", nullable = false)
    private Long size;

    /**
     * 持续时长，只有视频不为0
     */
    @Basic
    @Column(name = "duration", nullable = false)
    private Integer duration;

    /**
     * 审核状态
     */
    @Basic
    @Column(name = "audit_status", nullable = false)
    private Integer auditStatus;

    /**
     * 用户Id
     */
    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * url
     */
    @Basic
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * 创建时间
     */
    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}
