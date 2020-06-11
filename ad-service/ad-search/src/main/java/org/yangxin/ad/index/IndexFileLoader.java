package org.yangxin.ad.index;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.yangxin.ad.dump.DumpConstant;
import org.yangxin.ad.dump.table.*;
import org.yangxin.ad.handler.AdLevelDataHandler;
import org.yangxin.ad.mysql.constant.OpType;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangxin
 * 2020/06/11 20:45
 */
@Component
@DependsOn("dataTable")
public class IndexFileLoader {

    @PostConstruct
    public void init() {
        List<String> adPlanStringList = loadDumpData(String.format("%s%s", DumpConstant.DATA_ROOT_DIR,
                DumpConstant.AD_PLAN));
        adPlanStringList.forEach(p -> AdLevelDataHandler.handleLevel2(JSON.parseObject(p, AdPlanTable.class),
                OpType.ADD));

        List<String> adCreativeStringList = loadDumpData(String.format("%s%s", DumpConstant.DATA_ROOT_DIR,
                DumpConstant.AD_CREATIVE));
        adCreativeStringList.forEach(c -> AdLevelDataHandler.handleLevel2(JSON.parseObject(c, AdCreativeTable.class),
                OpType.ADD));

        List<String> adUnitStringList = loadDumpData(String.format("%s%s", DumpConstant.DATA_ROOT_DIR,
                DumpConstant.AD_UNIT));
        adUnitStringList.forEach(u -> AdLevelDataHandler.handleLevel3(JSON.parseObject(u, AdUnitTable.class),
                OpType.ADD));

        List<String> adCreativeUnitStringList = loadDumpData(String.format("%s%s", DumpConstant.DATA_ROOT_DIR,
                DumpConstant.AD_CREATIVE_UNIT));
        adCreativeUnitStringList.forEach(cu
                -> AdLevelDataHandler.handleLevel3(JSON.parseObject(cu, AdCreativeUnitTable.class),
                OpType.ADD));

        List<String> adUnitDistrictStringList = loadDumpData(String.format("%s%s", DumpConstant.DATA_ROOT_DIR,
                DumpConstant.AD_UNIT_DISTRICT));
        adUnitDistrictStringList.forEach(d
                -> AdLevelDataHandler.handleLevel4(JSON.parseObject(d, AdUnitDistrictTable.class),
                OpType.ADD));

        List<String> adUnitItStringList = loadDumpData(String.format("%s%s", DumpConstant.DATA_ROOT_DIR,
                DumpConstant.AD_UNIT_IT));
        adUnitItStringList.forEach(i -> AdLevelDataHandler.handleLevel4(JSON.parseObject(i, AdUnitItTable.class),
                OpType.ADD));

        List<String> adUnitKeywordStringList = loadDumpData(String.format("%s%s", DumpConstant.DATA_ROOT_DIR,
                DumpConstant.AD_UNIT_KEYWORD));
        adUnitKeywordStringList.forEach(k
                -> AdLevelDataHandler.handleLevel4(JSON.parseObject(k, AdUnitKeywordTable.class),
                OpType.ADD));
    }

    private List<String> loadDumpData(String fileName) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName))) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
