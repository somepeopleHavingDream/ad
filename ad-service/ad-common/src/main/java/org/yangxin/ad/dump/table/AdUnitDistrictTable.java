package org.yangxin.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * 2020/05/24 18:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictTable {

    private Long unitId;
    private String province;
    private String city;
}
