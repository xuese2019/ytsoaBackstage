package com.yts.ytsoa.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Slf4j
public class DateUtils {

    /**
     * 工作日志处判断当前时分是否在指定时分之后
     *
     * @param dqsj 当前的时分 格式：“HHMM” 例如："1701"
     * @param zdsj 指定的时分 格式：“HHMM” 例如："1701"
     * @return 当前时间如果晚于指定时间则返回true，否则false，相等视为false
     */
    public static boolean rztjsjqr(String dqsj, String zdsj) {
        try {
//            String dqsj = "1701";
//            String zdsj = "1700";
            int i = Integer.parseInt(dqsj);
            int i1 = Integer.parseInt(zdsj);
            if (i > i1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
