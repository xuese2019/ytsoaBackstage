package com.yts.ytsoa.business.gzrz.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gzrz.model.GzrzModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface GzrzService {
    /**
     * 条件查询带分页，默认查所有
     *
     * @param model
     * @return
     */
    ResponseResult<PageInfo<GzrzModel>> find(int pageNow, int pageSize, GzrzModel model);

    /**
     * 添加一条日志记录（非/项目日志）
     */
    ResponseResult<GzrzModel> addGzrz(GzrzModel model, String accid);

    /**
     * 根据项目id查询该项目的所有日志
     */
    ResponseResult<PageInfo<GzrzModel>> findByXmid(int pageNow, int pageSize, GzrzModel model) throws Exception;

    /**
     * 人工统计
     * @param pageNow
     * @param pageSize
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<PageInfo<GzrzModel>> rgtj(int pageNow, int pageSize,GzrzModel model) throws Exception;
}
