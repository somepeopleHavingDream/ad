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
 * @author yangxin
 * 2019/11/28 17:34
 */
@Component
@Slf4j
public class AccessLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Long startTime = (Long) requestContext.get("startTime");

        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();

        log.info("uri: [{}], duration: [{}]ms", requestURI, System.currentTimeMillis() - startTime);

        return null;
    }
}
