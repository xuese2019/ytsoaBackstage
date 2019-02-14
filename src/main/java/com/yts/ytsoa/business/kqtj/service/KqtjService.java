package com.yts.ytsoa.business.kqtj.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.kqtj.model.KqtjModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface KqtjService {
    ResponseResult<PageInfo<KqtjModel>> findAll(int pageNow, int pageSize, KqtjModel kqtjModel) throws Exception;
}
