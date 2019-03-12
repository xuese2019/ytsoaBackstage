package com.yts.ytsoa.business.bgshr.service;

import com.yts.ytsoa.business.bgshr.model.BgshrModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface BgshrszService {
    ResponseResult<BgshrModel> bgshrsz(BgshrModel model) throws Exception;

    ResponseResult<BgshrModel> find() throws Exception;
}
