package com.yts.ytsoa.business.bm.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bm.model.BmModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface BmService {
    ResponseResult<PageInfo<BmModel>> findAll(int pageNow, int pageSize, BmModel bmModel) throws Exception;

    ResponseResult<BmModel> findById(String uuid) throws Exception;

    ResponseResult<BmModel> update(BmModel bmModel) throws Exception;

    ResponseResult<BmModel> deleteById(String uuid) throws Exception;

    ResponseResult<BmModel> add(BmModel bmModel) throws Exception;
}
