package com.yts.ytsoa.business.xjsq.XjsqService;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.xjsq.model.XjsqModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.sql.SQLException;

public interface XjsqService {
    /**
     * 添加一条休假申请
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<XjsqModel> addXjsq(XjsqModel model) throws Exception;

    /**
     * 条件查询，带分页
     *
     * @param pageNow  当前页
     * @param pageSize 页面大小
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<PageInfo<XjsqModel>> find(int pageNow, int pageSize, XjsqModel model) throws Exception;

    /**
     * 根据uuid 查到当前的休假申请
     *
     * @param uuid
     * @return
     */
    ResponseResult<XjsqModel> findById(String uuid) throws SQLException;

    ResponseResult<XmshModel> xjsh(XmshModel model) throws Exception;
}
