package org.yangxin.ad.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创意
 *
 * @author yangxin
 * 2020/01/10 17:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeResponse {
    /**
     * id
     */
    private Long id;

    /**
     * 创意名称
     */
    private String name;
}
