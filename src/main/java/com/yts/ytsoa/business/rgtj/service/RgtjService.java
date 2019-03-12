package com.yts.ytsoa.business.rgtj.service;

import com.yts.ytsoa.business.rgtj.model.RgtjModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

public interface RgtjService {
    /**
     * 根据项目id 查询
     *
     * @param model
     * @return
     * @throws Exception
     */
    ResponseResult<List<RgtjModel>> find(RgtjModel model) throws Exception;

    /**
     * 每天定时（凌晨十二点）定时统计
     *
     * @param
     * @return
     * @throws Exception
     */
    ResponseResult<RgtjModel> insertRgtj() throws Exception;
}
