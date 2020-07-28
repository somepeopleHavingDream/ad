package org.yangxin.ad.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yangxin.ad.mysql.constant.OpType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangxin
 * 2020/07/28 20:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MySQLRowData {

    private String tableName;

    private String level;

    private OpType opType;

    private List<Map<String, String>> fieldValueMap = new ArrayList<>();
}
