package com.yts.ytsoa.business.zsk.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.zsk.model.ZskModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface ZskService {

    ResponseResult<PageInfo<ZskModel>> findAll(int pageNow, int pageSize, ZskModel zskModel) throws Exception;

    ResponseResult<List<ZskModel>> findById(ZskModel zskModel) throws Exception;

    ResponseResult<ZskModel> add(ZskModel zskModel) throws Exception;

    ResponseResult<ZskModel> delById(String uuid) throws Exception;

    ResponseResult<ZskModel> updById(ZskModel zskModel) throws Exception;

    ResponseResult<ZskModel> addZsk(List<ZskModel> zskModel) throws Exception;
}
