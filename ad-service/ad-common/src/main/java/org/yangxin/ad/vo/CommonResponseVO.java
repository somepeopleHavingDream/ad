package org.yangxin.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一响应
 *
 * @author yangxin
 * 2020/01/03 23:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponseVO<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public CommonResponseVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
