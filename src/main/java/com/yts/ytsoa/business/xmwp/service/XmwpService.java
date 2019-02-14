package com.yts.ytsoa.business.xmwp.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.utils.ResponseResult;

import javax.servlet.http.HttpServletRequest;

public interface XmwpService {
    /**
     * 添加一条项目委派的信息
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<XmwpModel> addXmwp(XmwpModel model, String fsr) throws Exception;

    /**
     * 条件查询，默认搜索全部
     *
     * @param model
     * @return
     * @throws Exception
     */
//    ResponseResult<List<XmwpModel>> find(XmwpModel model) throws Exception;

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
     * 项目经理审核
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<XmwpModel> xmsh(XmwpModel model, HttpServletRequest request) throws Exception;

}
