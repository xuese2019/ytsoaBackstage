package com.yts.ytsoa.business.shrsz.service;

import com.yts.ytsoa.business.shrsz.model.ShrszModel;
import com.yts.ytsoa.utils.ResponseResult;

public interface ShrszService {
    ResponseResult<ShrszModel> shrsz(ShrszModel model) throws Exception;

    ResponseResult<ShrszModel> findAll() throws Exception;
}
