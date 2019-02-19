package com.yts.ytsoa.business.xmcj.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmcj.model.XmcjModel;
import com.yts.ytsoa.business.xmcj.model.XmzmcModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface XmcjService {
    ResponseResult<PageInfo<XmcjModel>> findAll(int pageNow, int pageSize, XmcjModel xmcjModel) throws Exception;

    ResponseResult<XmcjModel> deleteById(String uuid) throws Exception;

    ResponseResult<List<XmcjModel>> findById(String uuid) throws Exception;

    ResponseResult<String> excel(String basePath, String outPath, XmcjModel xmcjModel) throws Exception;

    ResponseResult<XmcjModel> updateById(XmcjModel xmcjModel) throws Exception;

    ResponseResult<List<XmzmcModel>> findXmzmc(XmzmcModel model) throws Exception;

    /*ResponseResult<List<ResultModel>> findXmzmcByParentid(XmzmcModel model) throws Exception;*/
}
