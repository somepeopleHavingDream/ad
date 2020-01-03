package org.yangxin.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 前置请求，在过滤器中存储客户端发起请求的时间戳
 *
 * @author yangxin
 * 2019/11/28 17:27
 */
@Component
public class PreRequestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 是否启动当前的过滤器
        return true;
    }

    @Override
    public Object run() {
        // 用于在过滤器之间传递消息
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.set("startTime", System.currentTimeMillis());

        return null;
    }
}
