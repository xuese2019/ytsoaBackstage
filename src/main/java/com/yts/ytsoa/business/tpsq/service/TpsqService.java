package com.yts.ytsoa.business.tpsq.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.tpsq.model.ResultTpsqModel;
import com.yts.ytsoa.business.tpsq.model.TpsqModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface TpsqService {
    ResponseResult<TpsqModel> add(TpsqModel tpsqModel) throws Exception;

    ResponseResult<PageInfo<TpsqModel>> findAll(int pageNow, int pageSize, TpsqModel tpsqModel) throws Exception;

    ResponseResult<XmshModel> tpsh(XmshModel model) throws Exception;

    ResponseResult<TpsqModel> findById(String uuid) throws Exception;

    ResponseResult<List<ResultTpsqModel>> findByShjl(String prentid) throws Exception;
}
