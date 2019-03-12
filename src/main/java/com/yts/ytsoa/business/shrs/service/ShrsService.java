package com.yts.ytsoa.business.shrs.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shrs.model.ShrsModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface ShrsService {
    ResponseResult<PageInfo<ShrsModel>> findAll(int pageNow, int pageSize, ShrsModel shrsModel) throws Exception;


    ResponseResult<ShrsModel> add(ShrsModel shrsModel) throws Exception;

    ResponseResult<ShrsModel> delById(String uuid) throws Exception;

    ResponseResult<ShrsModel> updById(ShrsModel shrsModel) throws Exception;

    ResponseResult<ShrsModel> findById(String uuid) throws Exception;
}
