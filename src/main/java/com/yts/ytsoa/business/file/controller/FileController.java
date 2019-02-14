package com.yts.ytsoa.business.file.controller;

import com.yts.ytsoa.business.file.service.FileService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlFileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Api(value = "上传下载文件", description = "上传下载文件")
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private YamlFileUtils yamlFileUtils;
    @Autowired
    private FileService service;

    @ApiOperation(value = "上传文件，单个文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseResult<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.getSize() <= 0) {
            return new ResponseResult<>(false, "上传的文件大小不能小于等于0");
        }
//        文件名称
        String fileName = file.getOriginalFilename();
//        文件大小
        long size = file.getSize();
        //获取文件后缀名
        if (fileName == null || fileName.isEmpty()) {
            return new ResponseResult<>(false, "上传的文件名称必须是标准的xxx.xx格式");
        }
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (yamlFileUtils.getUpPrefix() != null && !yamlFileUtils.getUpPrefix().isEmpty()) {
            if (!yamlFileUtils.getUpPrefix().contains(prefix)) {
                return new ResponseResult<>(false, "不支持的上传类型");
            }
        }
        return service.upload(yamlFileUtils.getUpPath(), fileName, file.getBytes());
    }

    /**
     * 仅用于上传的文件进行下载
     *
     * @param fileName
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "根据文件名字下载文件，仅用于上传的文件，不能作用于导出")
    @RequestMapping(value = "/dowfile", method = RequestMethod.GET)
    public void dowFile(@RequestParam("fileName") String fileName,
                        HttpServletResponse response) throws Exception {
        // path是指欲下载的文件的路径。
        File file = new File(yamlFileUtils.getUpPath(), fileName);
        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(yamlFileUtils.getUpPath() + "/" + fileName));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename="
                + new String(fileName.getBytes(), StandardCharsets.ISO_8859_1));
        response.addHeader("Content-Length", "" + file.length());
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    }

    /**
     * 仅用于上传的文件进行下载
     *
     * @param fileName
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "根据文件名字下载文件，仅用于导出的文件，不能作用于上传的文件")
    @RequestMapping(value = "/dowfileexc", method = RequestMethod.GET)
    public void dowfileexc(@RequestParam("fileName") String fileName,
                           HttpServletResponse response) throws Exception {
        // path是指欲下载的文件的路径。
        File file = new File(yamlFileUtils.getDowPath(), fileName);
        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(yamlFileUtils.getDowPath() + "/" + fileName));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename="
                + new String(fileName.getBytes(), StandardCharsets.ISO_8859_1));
        response.addHeader("Content-Length", "" + file.length());
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    }
}
