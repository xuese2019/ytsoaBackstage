package com.yts.ytsoa.business.xmjl.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmjl.model.XmjlModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface XmjlService {
    ResponseResult<PageInfo<XmjlModel>> findAll(int pageNow, int pageSize, XmjlModel xmjlModel) throws Exception;
}
