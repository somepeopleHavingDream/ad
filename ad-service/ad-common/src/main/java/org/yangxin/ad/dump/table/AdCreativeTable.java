package org.yangxin.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * 2020/05/24 18:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdCreativeTable {

    private Long adId;
    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Integer auditStatus;
    private String adUrl;
}
