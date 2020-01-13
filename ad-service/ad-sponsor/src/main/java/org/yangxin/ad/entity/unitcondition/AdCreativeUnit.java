package org.yangxin.ad.entity.unitcondition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 创意-推广单元
 *
 * @author yangxin
 * 2020/01/08 14:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "creative_unit")
public class AdCreativeUnit {
    /**
     * 创意-推广单元id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 创意Id
     */
    @Basic
    @Column(name = "creative_id", nullable = false)
    private Long creativeId;

    /**
     * 推广单元Id
     */
    @Basic
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    public AdCreativeUnit(Long creativeId, Long unitId) {
        this.creativeId = creativeId;
        this.unitId = unitId;
    }
}
