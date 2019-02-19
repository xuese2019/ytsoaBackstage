package com.yts.ytsoa.business.jygl.service;


import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.jygl.model.JyglModel;
import com.yts.ytsoa.business.jygl.model.ResultModel;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;


public interface JyglService {
    ResponseResult<PageInfo<JyglModel>> findAll(int pageNow, int pageSize, JyglModel jyglModel) throws Exception;

    ResponseResult<JyglModel> findById(JyglModel jyglModel) throws Exception;

    ResponseResult<JyglModel> updById(JyglModel jyglModel, String accid) throws Exception;

    ResponseResult<String> excel(String basePath, String outPath, JyglModel jyglModel) throws Exception;

    /*ResponseResult<JyglModel> add(JyglModel model) throws Exception;*/

    ResponseResult<JyglModel> add(JyglModel model) throws Exception;

    ResponseResult<XmshModel> update(XmshModel model) throws Exception;

    ResponseResult<List<ResultModel>> findByShjl(String prentid) throws Exception;
}
