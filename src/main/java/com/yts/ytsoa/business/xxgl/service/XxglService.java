package com.yts.ytsoa.business.xxgl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xxgl.model.XxglModel;
import com.yts.ytsoa.utils.ResponseResult;

/**
 * @author: LD
 * @date:
 * @description:
 */
public interface XxglService {

    /**
     * 保存消息管理实体
     *
     * @param model 消息管理实体
     * @return 成功状态
     * @throws Exception
     */
    ResponseResult<XxglModel> save(XxglModel model) throws Exception;

    /**
     * 根据主键修改消息状态
     *
     * @param uuid 消息主键
     * @param zt   状态
     * @return 成功状态
     * @throws Exception
     */
    ResponseResult<XxglModel> updateZtByUuid(String uuid, int zt) throws Exception;

    /**
     * 条件分页查询
     *
     * @param pageNow  当前页
     * @param pageSize 每页条数
     * @param model    消息实体
     * @return 带分页的结果集
     * @throws Exception
     */
    ResponseResult<PageInfo<XxglModel>> findAll(int pageNow, int pageSize, XxglModel model) throws Exception;

}
