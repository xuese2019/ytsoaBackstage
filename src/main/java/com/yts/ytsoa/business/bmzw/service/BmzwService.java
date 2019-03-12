package com.yts.ytsoa.business.bmzw.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bmzw.model.BmzwModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface BmzwService {
    ResponseResult<PageInfo<BmzwModel>> findAll(int pageNow, int pageSize, BmzwModel bmzwModel) throws Exception;

    ResponseResult<BmzwModel> findById(String uuid) throws Exception;

    ResponseResult<BmzwModel> update(BmzwModel bmzwModel) throws Exception;

    ResponseResult<BmzwModel> deleteById(String uuid) throws Exception;

    ResponseResult<BmzwModel> add(BmzwModel bmzwModel) throws Exception;
}
