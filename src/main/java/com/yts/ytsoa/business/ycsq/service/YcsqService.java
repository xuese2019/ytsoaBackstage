package com.yts.ytsoa.business.ycsq.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.ycsq.model.ResultModel;
import com.yts.ytsoa.business.ycsq.model.YcsqModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface YcsqService {
    ResponseResult<PageInfo<YcsqModel>> findAll(int pageNow, int pageSize, YcsqModel ycsqModel) throws Exception;

    ResponseResult<YcsqModel> add(YcsqModel ycsqModel) throws Exception;


    ResponseResult<List<YcsqModel>> findById(YcsqModel ycsqModel) throws Exception;

    ResponseResult<YcsqModel> updById(YcsqModel ycsqModel) throws Exception;

    ResponseResult<XmshModel> update(XmshModel model, String fsr) throws Exception;

    ResponseResult<List<ResultModel>> findByShjl(String prentid) throws Exception;
}
