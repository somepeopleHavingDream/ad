package org.yangxin.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * 2020/05/24 18:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitKeywordTable {

    private Long unitId;
    private String keyword;
}
