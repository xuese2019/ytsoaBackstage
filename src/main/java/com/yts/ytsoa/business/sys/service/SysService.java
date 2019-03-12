package com.yts.ytsoa.business.sys.service;

import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.annot.AnnotC;

/**
 * @author: LD
 * @date:
 * @description:
 */
@AnnotC
public interface SysService {

    ResponseResult<String> deleteAll();
}
