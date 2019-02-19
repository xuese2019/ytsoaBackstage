package com.yts.ytsoa.business.xmwp.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.xmwp.model.ResultModel;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface XmwpService {
    /**
     * 添加一条项目委派的信息
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<XmwpModel> addXmwp(XmwpModel model) throws Exception;

    /**
     * 根据uuid删除一条信息
     *
     * @param uuid
     * @return
     * @throws Exception
     */
    ResponseResult<XmwpModel> delById(String uuid) throws Exception;

    /**
     * 更新一条项目委派的信息
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<XmwpModel> updById(XmwpModel model) throws Exception;

    /**
     * 根据uuid查到该条信息
     *
     * @param uuid
     * @return
     * @throws Exception
     */
    ResponseResult<ResultModel> findById(String uuid) throws Exception;

    /**
     * 分页，条件查询，默认查询所有项目名称
     *
     * @param
     * @return
     * @throws Exception
     */
    ResponseResult<PageInfo<XmwpModel>> findByXmmc(int pageNow, int pageSize, XmwpModel model, String accid) throws Exception;

    /**
     * excel导出
     */
    ResponseResult<String> excel(String basePath, String outPath, XmwpModel model) throws Exception;

    /**
     * 查出该项目的项目负责人
     */
    ResponseResult<String> findXmfzr(String uuid) throws Exception;

    /**
     * 项目审核
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<XmshModel> xmsh(XmshModel model, String accid) throws Exception;

    ResponseResult<PageInfo<XmwpModel>> findByXmyq(int pageNow, int pageSize, XmwpModel model, String accId) throws Exception;
}
