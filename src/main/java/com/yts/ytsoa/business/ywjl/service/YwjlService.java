package com.yts.ytsoa.business.ywjl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.ywjl.model.YwjlModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface YwjlService {

    ResponseResult<PageInfo<YwjlModel>> findAll(int pageNow, int pageSize, YwjlModel ywjlModel) throws Exception;

    ResponseResult<YwjlModel> add(YwjlModel ywjlModel) throws Exception;

    ResponseResult<YwjlModel> delById(String uuid) throws Exception;
}
