package org.yangxin.ad.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.vo.CommonResponseVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常
 *
 * @author yangxin
 * 2020/01/03 23:25
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {
    /**
     * 处理异常
     */
    @ExceptionHandler(AdException.class)
    public CommonResponseVO<String> handleAdException(HttpServletRequest request, AdException e) {
        CommonResponseVO<String> commonResponseVO = new CommonResponseVO<>(-1, "business error");
        commonResponseVO.setData(e.getMessage());

        return commonResponseVO;
    }
}