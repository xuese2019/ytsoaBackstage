package com.yts.ytsoa.business.file.service;

import com.yts.ytsoa.utils.ResponseResult;

/**
 * @author: LD
 * @date:
 * @description:
 */
public interface FileService {

    ResponseResult<String> upload(String path, String fileName, byte[] file) throws Exception;
}
