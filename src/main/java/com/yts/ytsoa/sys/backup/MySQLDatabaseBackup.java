package com.yts.ytsoa.sys.backup;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
public class MySQLDatabaseBackup {

//    public static void main(String[] args) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//        try {
//            if (exportDatabaseTool("localhost", "root", "root", "D:/backupDatabase", format.format(System.currentTimeMillis())+".sql", "hbcrm")) {
//                System.out.println("数据库成功备份！！！");
//                log.info("数据库成功备份！！！");
//            } else {
//                System.out.println("数据库备份失败！！！");
//                log.info("数据库备份失败！！！");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Java代码实现MySQL数据库导出
     *
     * @param hostIP       MySQL数据库所在服务器地址IP
     * @param userName     进入数据库所需要的用户名
     * @param password     进入数据库所需要的密码
     * @param savePath     数据库导出文件保存路径
     * @param fileName     数据库导出文件文件名
     * @param databaseName 要导出的数据库名
     * @param dumpPath     mysqldump安装位置
     * @return 返回true表示导出成功，否则返回false。
     */
    public static boolean exportDatabaseTool(String hostIP,
                                             String userName,
                                             String password,
                                             String savePath,
                                             String fileName,
                                             String databaseName,
                                             String dumpPath) throws InterruptedException {
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), StandardCharsets.UTF_8));
            Process process = Runtime.getRuntime().exec(" " + dumpPath + "mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            printWriter.flush();
            if (process.waitFor() == 0) {//0 表示线程正常终止。
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
