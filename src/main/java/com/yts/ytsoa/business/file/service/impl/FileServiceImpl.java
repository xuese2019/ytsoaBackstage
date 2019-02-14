package com.yts.ytsoa.business.file.service.impl;

import com.yts.ytsoa.business.file.service.FileService;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public ResponseResult<String> upload(String path, String fileName, byte[] file) throws Exception {
        FileOutputStream out = null;
        try {
//            上传
            String newFileName = GetUuid.getUUID() + fileName;
            out = new FileOutputStream(path + "/" + newFileName);
            out.write(file);
            out.flush();
            out.close();
            return new ResponseResult<>(true, "文件上传成功", newFileName);
        } catch (IOException e) {
            return new ResponseResult<>(false, "文件上传错误");
        }
    }
}
