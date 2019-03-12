package com.yts.ytsoa.business.jyjl.service;

import com.yts.ytsoa.business.jyjl.model.JyjlModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface JyjlService {
    ResponseResult<JyjlModel> isnert(JyjlModel model) throws Exception;

}
