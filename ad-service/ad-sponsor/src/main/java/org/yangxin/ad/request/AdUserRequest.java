package org.yangxin.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * 创建用户
 *
 * @author yangxin
 * 2020/01/08 15:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUserRequest {

    /**
     * 用户名
     */
    private String username;

    public boolean validate() {
        return !StringUtils.isEmpty(username);
    }
}
