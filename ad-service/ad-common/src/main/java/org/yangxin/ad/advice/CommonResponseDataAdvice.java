package org.yangxin.ad.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.yangxin.ad.annotation.IgnoreResponseAdvice;
import org.yangxin.ad.vo.CommonResponseVO;

/**
 * 统一响应拦截
 *
 * @author yangxin
 * 2020/01/03 23:04
 */
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return !methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class);
    }

    @Override
//    @Nullable
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        CommonResponseVO<Object> commonResponseVO = new CommonResponseVO<>(0, "");
        if (o == null) {
            return commonResponseVO;
        } else if (o instanceof CommonResponseVO) {
            commonResponseVO = (CommonResponseVO<Object>) o;
        } else {
            commonResponseVO.setData(o);
        }

        return commonResponseVO;
    }
}
