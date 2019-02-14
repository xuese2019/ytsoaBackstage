package com.yts.ytsoa.business.bgshjl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bgshjl.model.BgshjlModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface BgshjlService {
    ResponseResult<BgshjlModel> insert(BgshjlModel model);

    ResponseResult<PageInfo<BgshjlModel>> find(int pageNow, int pageSize, BgshjlModel model);
}
