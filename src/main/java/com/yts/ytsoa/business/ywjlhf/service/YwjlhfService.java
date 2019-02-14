package com.yts.ytsoa.business.ywjlhf.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.ywjlhf.model.YwjlhfModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface YwjlhfService {
    ResponseResult<YwjlhfModel> add(YwjlhfModel ywjlhfModel) throws Exception;


    ResponseResult<PageInfo<YwjlhfModel>> findAll(int pageNow, int pageSize, YwjlhfModel ywjlhfModel) throws Exception;
}
