package com.yts.ytsoa.business.bgflbh.service;

import com.yts.ytsoa.business.bgflbh.model.BglxModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface BgflbhService {

    /**
     * 统计所有报告类型条数
     */
    /*ResponseResult<BgflbhModel> upd(BgflbhModel model) throws Exception;*/

    /**
     * 根据报告类型生成编号
     */
    /*ResponseResult<Integer> autoBh(BgflbhModel model) throws Exception;*/

    ResponseResult<BglxModel> find(BglxModel model) throws Exception;
}
