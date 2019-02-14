package com.yts.ytsoa.business.qxgl.service;

import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.business.qxgl.model.ZzQxModel;
import com.yts.ytsoa.utils.ResponseResult;

import java.util.List;

/**
 * @author LD
 */
public interface ZzQxService {

    ResponseResult<ZzQxModel> setQx(String zzid, String qxstr) throws Exception;

    ResponseResult<ZzQxModel> setQx(String zzid, List<ZzQxModel> list) throws Exception;

    ResponseResult<List<ZzQxModel>> findByZzid(String zzid) throws Exception;

    ResponseResult<List<QxglModel>> findByZzid2(String zzid) throws Exception;

    ResponseResult<List<QxglModel>> findByAccid(String accid) throws Exception;
}
