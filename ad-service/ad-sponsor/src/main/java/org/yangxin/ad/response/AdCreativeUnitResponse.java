package org.yangxin.ad.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 创意-推广计划
 *
 * @author yangxin
 * 2020/01/13 14:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdCreativeUnitResponse {
    private List<Long> ids;
}
