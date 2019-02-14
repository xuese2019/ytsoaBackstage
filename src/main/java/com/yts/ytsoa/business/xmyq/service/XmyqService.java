package com.yts.ytsoa.business.xmyq.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmyq.model.XmyqModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface XmyqService {
    ResponseResult<XmyqModel> add(XmyqModel xmyqModel) throws Exception;

    ResponseResult<PageInfo<XmyqModel>> findAll(int pageNow, int pageSize, XmyqModel xmyqModel) throws Exception;
}
