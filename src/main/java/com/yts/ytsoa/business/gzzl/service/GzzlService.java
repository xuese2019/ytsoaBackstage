package com.yts.ytsoa.business.gzzl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gzzl.model.GzzlModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface GzzlService {

    ResponseResult<PageInfo<GzzlModel>> findAll(int pageNow, int pageSize, GzzlModel gzzlModel) throws Exception;

    ResponseResult<GzzlModel> add(GzzlModel gzzlModel, String accid) throws Exception;
}
