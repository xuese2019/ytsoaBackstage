package com.yts.ytsoa.business.gzrz.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gzrz.model.GzrzModel;
import com.yts.ytsoa.business.gzrz.model.RztjsjModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

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
     * 人工统计,用于接口
     *
     * @param pageNow
     * @param pageSize
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<PageInfo<GzrzModel>> rgtj(int pageNow, int pageSize, GzrzModel model) throws Exception;

    /**
     * 修改日志提交的时间
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<RztjsjModel> updateTjsj(RztjsjModel model) throws Exception;

    /**
     * 查看日志的提交时间
     *
     * @return
     * @throws Exception
     */
    ResponseResult<RztjsjModel> findTjsj() throws Exception;

    /**
     * 日志点评
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<GzrzModel> addRzdp(GzrzModel model) throws Exception;

    /**
     * 根据ID查询
     *
     * @param uuid
     * @return
     * @throws Exception
     */
    ResponseResult<List<GzrzModel>> findById(String uuid) throws Exception;
}
