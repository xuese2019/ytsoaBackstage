package com.yts.ytsoa.business.shjd.service;

import com.yts.ytsoa.business.shjd.model.ShjdModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface ShjdService {
    ResponseResult<ShjdModel> find(ShjdModel model);

    ResponseResult<ShjdModel> insert(ShjdModel model);

    ResponseResult<ShjdModel> update(ShjdModel model);
}
