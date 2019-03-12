package com.yts.ytsoa.business.bgflbh.service;

import com.yts.ytsoa.business.bgflbh.model.BglxModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface BgflbhService {

    ResponseResult<BglxModel> find(BglxModel model) throws Exception;

    /**
     * 第一种报告编号归零
     *
     * @return
     */
    ResponseResult<BglxModel> updateFirstBglx();

    /**
     * 第二种报告编号归零
     *
     * @return
     */
    ResponseResult<BglxModel> updateSeccondBglx();

    /**
     * 第三种报告编号归零
     *
     * @return
     */
    ResponseResult<BglxModel> updateThirdBglx();

    /**
     * 第四种报告编号归零
     *
     * @return
     */
    ResponseResult<BglxModel> updateForthBglx();

    /**
     * 第五种报告编号归零
     *
     * @return
     */
    ResponseResult<BglxModel> updateFifthBglx();
}
