package com.yts.ytsoa.business.sz.service;

import com.yts.ytsoa.business.sz.model.SzModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface SzService {
    ResponseResult<List<SzModel>> findAll() throws Exception;

    ResponseResult<SzModel> findById(String uuid) throws Exception;

    ResponseResult<SzModel> update(SzModel szModel) throws Exception;

    ResponseResult<SzModel> add(SzModel szModel) throws Exception;

    ResponseResult<SzModel> setSz(SzModel szModel) throws Exception;
}
