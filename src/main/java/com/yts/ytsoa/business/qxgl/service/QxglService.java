package com.yts.ytsoa.business.qxgl.service;

import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

/**
 * @author LD
 */
public interface QxglService {
    /**
     * 层级关系集合
     *
     * @return
     * @throws Exception
     */
    ResponseResult<List<QxglModel>> findAll() throws Exception;

    ResponseResult<List<QxglModel>> findMenu() throws Exception;

    /**
     * 批量插入
     *
     * @param list
     * @return
     * @throws Exception
     */
    ResponseResult<QxglModel> saves(List<QxglModel> list) throws Exception;
}
