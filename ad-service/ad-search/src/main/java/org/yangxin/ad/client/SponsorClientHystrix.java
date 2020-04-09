package org.yangxin.ad.client;

import org.springframework.stereotype.Component;
import org.yangxin.ad.request.AdPlanRequest;
import org.yangxin.ad.response.AdPlanResponse;
import org.yangxin.ad.vo.CommonResponseVO;

import java.util.List;

/**
 * 广告赞助商
 *
 * @author yangxin
 * 2020/04/09 20:47
 */
@Component
public class SponsorClientHystrix implements SponsorClient {

    @Override
    public CommonResponseVO<List<AdPlanResponse>> listAdPlans(AdPlanRequest request) {
        return new CommonResponseVO<>(-1, "eureka-client-ad-sponsor error");
    }
}
