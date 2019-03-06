package com.yts.ytsoa.business.xmcy.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface XmcyService {
    /**
     * 批量/添加项目成员
     *
     * @param models
     * @return
     */
    /* ResponseResult<XmcyModel> addXmcy(XmcyModel model) throws Exception;*/
    ResponseResult<XmcyModel> addXmcy(List<XmcyModel> models) throws Exception;

    /**
     * 条件查询带分页
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<PageInfo<XmcyModel>> find(int pageNow, int pageSize, XmcyModel model) throws Exception;

    ResponseResult<XmcyModel> update(XmcyModel model,String accid) throws Exception;

    /*    *//**
     * 人工统计
     *//*
    ResponseResult<PageInfo<resultModel>> rgtj(int pageNow, int pageSize, resultModel model) throws Exception;*/

    /**
     * 根据id查出详细信息
     */
    ResponseResult<XmcyModel> findById(String uuid) throws Exception;

    /**
     * 添加项目成员
     */
    ResponseResult<XmcyModel> insertXmcy(XmcyModel model,String accid) throws Exception;

    /**
     * 查出项目中的员工
     */
    ResponseResult<List<XmcyModel>> findYgid(String xmid) throws Exception;

    ResponseResult<List<XmcyModel>> findxmid(String xmid) throws Exception;
}
