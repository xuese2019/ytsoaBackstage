package com.yts.ytsoa.business.sys.service.impl;

import com.yts.ytsoa.business.sys.mapper.SysMapper;
import com.yts.ytsoa.business.sys.service.SysService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.annot.AnnotC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: LD
 * @date:
 * @description:
 */
@AnnotC
@Slf4j
@Service
public class SysServiceImpl implements SysService {

    @Autowired
    private SysMapper mapper;

    @Override
    public ResponseResult<String> deleteAll() {
        mapper.deleteAll();
        return new ResponseResult<>(true, "所有业务表清理完成");
    }
}
