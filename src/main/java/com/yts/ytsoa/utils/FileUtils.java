package com.yts.ytsoa.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Slf4j
public class FileUtils {

    // 判断文件是否存在
    public static void judeFileExists(File file) {

        if (!file.exists()) {
            log.info("文件路径" + file.getPath() + "不存在");
            try {
                boolean b = file.createNewFile();
                if (b) {
                    log.info("文件路径" + file.getPath() + "创建成功");
                } else {
                    log.error("文件路径" + file.getPath() + "创建失败");
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error("文件路径" + file.getPath() + "创建失败");
                e.printStackTrace();
            }
        }
    }

    // 判断文件夹是否存在
    public static void judeDirExists(File file) {
        if (file.exists()) {
            if (!file.isDirectory()) {
                log.info("文件路径" + file.getPath() + "不是目录");
            }
        } else {
            log.info("文件路径" + file.getPath() + "不存在");
            boolean b = file.mkdir();
            if (b) {
                log.info("文件路径" + file.getPath() + "创建成功");
            } else {
                log.error("文件路径" + file.getPath() + "创建失败");
            }
        }
    }
}
