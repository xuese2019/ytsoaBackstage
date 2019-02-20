package com.yts.ytsoa.business.gdgl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.gdgl.model.GdglResultModel;
import com.yts.ytsoa.business.gdgl.query.GdglQueryModel;
import com.yts.ytsoa.business.gdgl.result.ResultModel;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.xmcj.model.XmzmcModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface GdglService {
    /**
     * 添加一条归档申请
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<GdglModel> addGdgl(GdglModel model) throws Exception;

    /**
     * 带条件查询，带分页
     *
     * @param pageNow
     * @param pageSize
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<PageInfo<GdglModel>> find(int pageNow, int pageSize, GdglModel model) throws Exception;

    /**
     * 待条件查询，带分页
     *
     * @param model
     * @return
     */
    ResponseResult<PageInfo<ResultModel>> findGdsh(int pageNow, int pageSize, GdglQueryModel model) throws Exception;

    ResponseResult<GdglModel> updById(GdglModel gdglModel) throws Exception;

    ResponseResult<GdglModel> findById(String uuid) throws Exception;

    ResponseResult<XmshModel> update(XmshModel model) throws Exception;

    ResponseResult<List<GdglModel>> findByDamc(GdglModel model) throws Exception;

    ResponseResult<List<XmzmcModel>> findByUuid(String parentid) throws Exception;

    ResponseResult<List<BgglModel>> findBgByUuid(String uuid) throws Exception;

    ResponseResult<List<GdglResultModel>> findByShjl(String prentid) throws Exception;
}
