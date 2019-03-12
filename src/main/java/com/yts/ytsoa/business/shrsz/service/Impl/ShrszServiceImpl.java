package com.yts.ytsoa.business.shrsz.service.Impl;

import com.yts.ytsoa.business.shrsz.mapper.ShrszMapper;
import com.yts.ytsoa.business.shrsz.model.ShrszModel;
import com.yts.ytsoa.business.shrsz.service.ShrszService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ShrszServiceImpl implements ShrszService {
    @Autowired
    private ShrszMapper shrszMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ShrszModel> shrsz(ShrszModel model) throws Exception {
        if (model != null) {
            int result = shrszMapper.shrsz(model);
            if (result > 0) {
                return new ResponseResult<>(true, "设置成功");
            }
        }
        return new ResponseResult<>(false, "设置失败");
    }

    @Override
    public ResponseResult<ShrszModel> findAll() throws Exception {
        ShrszModel model = shrszMapper.findAll();
        if (model != null) {
            return new ResponseResult<>(true, "查询成功", model);
        }
        return new ResponseResult<>(false, "查无信息");
    }
}
