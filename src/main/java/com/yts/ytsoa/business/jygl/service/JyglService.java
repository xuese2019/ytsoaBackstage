package com.yts.ytsoa.business.jygl.service;


import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.jygl.model.JyglModel;
import com.yts.ytsoa.utils.ResponseResult;


public interface JyglService {
    ResponseResult<PageInfo<JyglModel>> findAll(int pageNow, int pageSize, JyglModel jyglModel) throws Exception;

    ResponseResult<JyglModel> findById(JyglModel jyglModel) throws Exception;

    ResponseResult<JyglModel> updById(JyglModel jyglModel) throws Exception;

    ResponseResult<String> excel(String basePath, String outPath, JyglModel jyglModel) throws Exception;

    /*ResponseResult<JyglModel> add(JyglModel model) throws Exception;*/

    ResponseResult<JyglModel> add(JyglModel model) throws Exception;
}
