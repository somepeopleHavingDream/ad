package org.yangxin.ad.search;

import org.yangxin.ad.search.vo.SearchRequest;
import org.yangxin.ad.search.vo.SearchResponse;

/**
 * @author yangxin
 * 2020/08/19 15:59
 */
public interface Search {

    SearchResponse fetchAds(SearchRequest request);
}