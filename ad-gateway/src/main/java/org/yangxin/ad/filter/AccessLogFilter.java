package org.yangxin.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 访问日志，自定义过滤器，打印请求响应时间
 *
 * 自定义Zuul的filter时，需要继承ZuulFilter抽象类，其中filterOrder定义了过滤器执行的顺序，数值越小，优先级越高。
 * 因为内置的响应过滤器优先级定义为常量FilterConstants.SEND_RESPONSE_FILTER_ORDER，所以，我们需要在响应返回之前执行我们自定义的过滤器
 * 最好的方式就是将这个常量减去1
 *
 * @author yangxin
 * 2019/11/28 17:34
 */
@Component
@Slf4j
public class AccessLogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        // 过滤器的类型（后置过滤器）
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        // zuul组件默认的response filter的order是1000（因为zuul的FilterConstants.SEND_RESPONSE_FILTER_ORDER
        // 常量就是1000），所以我们的response过滤器的order要在FilterConstants.SEND_RESPONSE_FILTER_ORDER之前，否则不起作用。
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        // 是否启用当前的过滤器
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Long startTime = (Long) requestContext.get("startTime");

        HttpServletRequest request = requestContext.getRequest();
        String requestUri = request.getRequestURI();

        if (log.isInfoEnabled()) {
            log.info("uri: [{}], duration: [{}]ms", requestUri, System.currentTimeMillis() - startTime);
        }

        return null;
    }
}
