package com.yts.ytsoa.business.ywzt.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.ywzt.model.YwztModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface YwztService {
    ResponseResult<PageInfo<YwztModel>> findAll(int pageNow, int pageSize, YwztModel ywztModel) throws Exception;

    ResponseResult<YwztModel> add(YwztModel ywztModel) throws Exception;
}
