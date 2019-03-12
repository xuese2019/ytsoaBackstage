package com.yts.ytsoa.business.swsdt.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.swsdt.model.SwsdtModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface SwsdtService {
    /**
     * 添加事务所动态
     *
     * @param model
     * @param accid
     * @return
     * @throws Exception
     */
    ResponseResult<SwsdtModel> insertSwsdt(SwsdtModel model, String accid) throws Exception;

    ResponseResult<PageInfo<SwsdtModel>> find(int pageNow, int pageSize, SwsdtModel model) throws Exception;
}
