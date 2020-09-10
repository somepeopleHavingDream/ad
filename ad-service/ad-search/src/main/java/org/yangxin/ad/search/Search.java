package org.yangxin.ad.search;

import org.yangxin.ad.search.vo.SearchRequest;
import org.yangxin.ad.search.vo.SearchResponse;

/**
 * 检索
 *
 * @author yangxin
 * 2020/08/19 15:59
 */
public interface Search {

    /**
     * 抓取广告
     *
     * @param request 检索请求
     * @return 搜索响应
     */
    SearchResponse fetchAds(SearchRequest request);
}