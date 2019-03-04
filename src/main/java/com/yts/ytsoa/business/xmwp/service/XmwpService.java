package com.yts.ytsoa.business.xmwp.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

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
    ResponseResult<XmwpModel> findById(String uuid) throws Exception;

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
     * 项目审核,只用于项目审核
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<XmshModel> xmsh(XmshModel model, String accid) throws Exception;

    ResponseResult<PageInfo<XmwpModel>> findByXmyq(int pageNow, int pageSize, XmwpModel model, String accId) throws Exception;

    /**
     * 用于项目委派管理，承接管理
     *
     * @param pageNow
     * @param pageSize
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<PageInfo<XmwpModel>> find(int pageNow, int pageSize, XmwpModel model) throws Exception;

    /**
     * 报告申请页面
     * 项目名称搜索只能查询自己承接的项目
     * 并且必须得是审核通过的项目才能申请报告
     *
     * @param model
     * @return
     */
    ResponseResult<List<XmwpModel>> findByXmfzr(XmwpModel model, String accid) throws Exception;

    /**
     * 项目管理页面
     * 业务状态必须是大于等于2的
     *
     * @param pageNow
     * @param pageSize
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<PageInfo<XmwpModel>> xmgl(int pageNow, int pageSize, XmwpModel model) throws Exception;
}
