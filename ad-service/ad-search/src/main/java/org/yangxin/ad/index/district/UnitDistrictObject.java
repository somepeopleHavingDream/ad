package org.yangxin.ad.index.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地域索引对象
 *
 * @author yangxin
 * 2020/05/23 12:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDistrictObject {

    /**
     * 推广单元Id
     */
    private Long unitId;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    // <String, Set<Long>>
    // province-city
}
