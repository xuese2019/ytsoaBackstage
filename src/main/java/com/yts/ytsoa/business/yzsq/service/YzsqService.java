package com.yts.ytsoa.business.yzsq.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.business.yzsq.model.ResultsModel;
import com.yts.ytsoa.business.yzsq.model.YzsqModel;
import com.yts.ytsoa.business.yzsq.result.result;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface YzsqService {
    /**
     * 添加一条用章申请
     *
     * @param model
     * @return
     */
    ResponseResult<YzsqModel> addYzsq(YzsqModel model) throws Exception;

    /**
     * 条件查询带分页
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<PageInfo<YzsqModel>> find(int pageNow, int pageSize, YzsqModel model) throws Exception;

    /**
     * 用章申请修改
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<YzsqModel> update(YzsqModel model) throws Exception;

    /**
     * excel导出
     *
     * @param basePath
     * @param outPath
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<String> excel(String basePath, String outPath, YzsqModel model) throws Exception;

    /**
     * 根据id查出该条信息
     *
     * @param uuid
     * @return
     */
    ResponseResult<YzsqModel> findById(String uuid) throws Exception;

    /**
     * 用章管理
     */
    ResponseResult<PageInfo<result>> yzgl(int pageNow, int pageSize, YzsqModel model) throws Exception;

    ResponseResult<List<XmcyModel>> findXmcyByXmid(String xmid) throws Exception;

    /**
     * 用章审核
     */
    ResponseResult<XmshModel> yzsh(XmshModel model, String fsr) throws Exception;

    ResponseResult<List<ResultsModel>> findByShjl(String prentid) throws Exception;

    /**
     * 项目管理详情，用章管理
     * 且为审核通过的用章申请
     *
     * @param xmid
     * @return
     * @throws Exception
     */
    ResponseResult<List<YzsqModel>> findYzglByXmid(String xmid) throws Exception;
}
