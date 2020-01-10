package org.yangxin.ad.constant;

/**
 * 常量
 *
 * @author yangxin
 * 2020/01/08 16:07
 */
public class Constants {
    /**
     * 错误信息
     *
     * @author yangxin
     * 2020/01/08 16:11
     */
    public interface ErrorMsg {
        String REQUEST_PARAM_ERROR = "请求参数错误";
        String SAME_NAME_USER_ERROR = "存在同名的用户";
        String CANT_FIND_RECORD = "找不到数据记录";
        String SAME_NAME_PLAN_ERROR = "存在同名的推广计划";
        String SAME_NAME_UNIT_ERROR = "存在同名的推广单元";
    }
}
