package com.yts.ytsoa.business.xmjlcy.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmjlcy.model.XmjlcyModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface XmjlcyService {
    ResponseResult<PageInfo<XmjlcyModel>> findAll(int pageNow, int pageSize, XmjlcyModel xmjlcyModel) throws Exception;

    ResponseResult<XmjlcyModel> add(XmjlcyModel xmjlcyModel) throws Exception;

    ResponseResult<XmjlcyModel> delById(String uuid) throws Exception;

    ResponseResult<List<XmjlcyModel>> findById(String uuid) throws Exception;
}
