package org.yangxin.ad.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 创建用户的响应
 *
 * @author yangxin
 * 2020/01/08 15:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUserResponse {
    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * token
     */
    private String token;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
