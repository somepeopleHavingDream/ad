package org.yangxin.ad.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yangxin.ad.Application;
import org.yangxin.ad.repository.AdCreativeRepository;
import org.yangxin.ad.repository.AdPlanRepository;
import org.yangxin.ad.repository.AdUnitRepository;
import org.yangxin.ad.repository.unitcondition.AdCreativeUnitRepository;
import org.yangxin.ad.repository.unitcondition.AdUnitDistrictRepository;
import org.yangxin.ad.repository.unitcondition.AdUnitItRepository;
import org.yangxin.ad.repository.unitcondition.AdUnitKeywordRepository;

/**
 * 导出数据
 *
 * @author yangxin
 * 2020/05/24 18:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DumpDataService {

    @Autowired
    private AdPlanRepository adPlanRepository;
    @Autowired
    private AdUnitRepository adUnitRepository;
    @Autowired
    private AdCreativeRepository adCreativeRepository;
    @Autowired
    private AdCreativeUnitRepository adCreativeUnitRepository;
    @Autowired
    private AdUnitDistrictRepository adUnitDistrictRepository;
    @Autowired
    private AdUnitItRepository adUnitItRepository;
    @Autowired
    private AdUnitKeywordRepository adUnitKeywordRepository;
}
