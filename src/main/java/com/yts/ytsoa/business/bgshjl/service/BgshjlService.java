package com.yts.ytsoa.business.bgshjl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bgshjl.model.BgshjlModel;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface BgshjlService {
    /**
     * 报告审核
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<BgshjlModel> insert(BgshjlModel model, String accid) throws Exception;

    ResponseResult<PageInfo<BgshjlModel>> find(int pageNow, int pageSize, BgshjlModel model) throws Exception;

    ResponseResult<List<XmshModel>> findByParentId(String uuid) throws Exception;


}
