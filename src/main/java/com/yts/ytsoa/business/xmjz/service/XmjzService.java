package com.yts.ytsoa.business.xmjz.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmjz.modedl.XmjzModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface XmjzService {
    ResponseResult<PageInfo<XmjzModel>> findAll(int pageNow, int pageSize, XmjzModel xmjzModel) throws Exception;

    ResponseResult<XmjzModel> add(XmjzModel xmjzModel) throws Exception;

    /*    ResponseResult<XmwpModel> findByXmid(XmwpModel xmwpModel)throws Exception;*/
}
