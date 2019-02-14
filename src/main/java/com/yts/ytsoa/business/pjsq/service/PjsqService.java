package com.yts.ytsoa.business.pjsq.service;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.pjsq.model.PjsqModel;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface PjsqService {
    ResponseResult<PjsqModel> add(PjsqModel pjsqModel) throws Exception;

    ResponseResult<PageInfo<PjsqModel>> findAll(int pageNow, int pageSize, PjsqModel pjsqModel) throws Exception;

    ResponseResult<String> excel(String basePath, String outPath, PjsqModel pjsqModel) throws Exception;

    ResponseResult<PageInfo<PjsqModel>> find(int pageNow, int pageSize, PjsqModel model) throws Exception;

    ResponseResult<XmshModel> shjl(XmshModel model) throws Exception;

    ResponseResult<PjsqModel> findById(String uuid) throws Exception;
}
