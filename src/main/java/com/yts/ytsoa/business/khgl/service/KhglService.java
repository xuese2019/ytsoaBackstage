package com.yts.ytsoa.business.khgl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.khgl.model.KhglModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface KhglService {
    ResponseResult<PageInfo<KhglModel>> findAll(int pageNow, int pageSize, KhglModel khglModel) throws Exception;

    ResponseResult<KhglModel> add(KhglModel khglModel) throws Exception;

    ResponseResult<KhglModel> deleteById(String uuid) throws Exception;

    ResponseResult<List<KhglModel>> findById(String uuid) throws Exception;

    ResponseResult<KhglModel> updById(KhglModel khglModel) throws Exception;
}
