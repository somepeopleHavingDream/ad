package org.yangxin.ad.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yangxin.ad.Application;
import org.yangxin.ad.entity.AdPlan;
import org.yangxin.ad.exception.AdException;
import org.yangxin.ad.request.AdPlanRequest;

import java.util.Collections;
import java.util.List;

/**
 * @author yangxin
 * 2020/09/07 21:08
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AdPlanServiceTest {

    @Autowired
    private AdPlanService adPlanService;

    @Test
    public void testGetAdPlan() throws AdException {
        AdPlanRequest adPlanRequest = new AdPlanRequest();
        adPlanRequest.setUserId(15L);
        adPlanRequest.setIds(Collections.singletonList(10L));
        List<AdPlan> plans = adPlanService.listAdPlanByIds(adPlanRequest);
//        log.info("plans.size: [{}]", plans.size());
        Assert.assertEquals(1, plans.size());
    }
}