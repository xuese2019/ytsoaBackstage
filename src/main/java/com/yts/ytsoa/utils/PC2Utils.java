package com.yts.ytsoa.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Slf4j
public class PC2Utils {

    private static String P = "-1331468711";

    public static boolean pc() {
        String c = PCUtils.getSerialNumber("C");
        return P.equals(c);
    }
}
