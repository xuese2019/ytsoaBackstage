package com.yts.ytsoa.utils;

import java.util.UUID;

/**
 * @author LD
 */
public class GetUuid {

    public static String getUUID() {
        //获取UUID并转化为String对象
        String uuid = UUID.randomUUID().toString();
        //因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
        uuid = uuid.replace("-", "");
        return uuid;
    }
}
