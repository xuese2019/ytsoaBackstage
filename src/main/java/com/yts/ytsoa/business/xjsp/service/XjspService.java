package com.yts.ytsoa.business.xjsp.service;

import com.yts.ytsoa.business.xjsp.model.XjspModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface XjspService {
    /**
     * 增加一条审批记录
     *
     * @param model
     * @return
     */
    ResponseResult<XjspModel> addXjsp(XjspModel model);
}
