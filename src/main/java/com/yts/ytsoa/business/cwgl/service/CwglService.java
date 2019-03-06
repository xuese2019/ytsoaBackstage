package com.yts.ytsoa.business.cwgl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.cwgl.model.CwglModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface CwglService {

    ResponseResult<PageInfo<CwglModel>> findAll(int pageNow, int pageSize, CwglModel cwglModel) throws Exception;

    ResponseResult<CwglModel> add(CwglModel cwglModel, String accid) throws Exception;

    ResponseResult<List<CwglModel>> findById(String uuid) throws Exception;

    ResponseResult<CwglModel> updById(CwglModel cwglModel) throws Exception;
}
