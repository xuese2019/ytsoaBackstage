package com.yts.ytsoa.business.xjsp.service.Impl;

import com.yts.ytsoa.business.xjsp.mapper.XjspMapper;
import com.yts.ytsoa.business.xjsp.model.XjspModel;
import com.yts.ytsoa.business.xjsp.service.XjspService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class XjspServiceImpl implements XjspService {
    @Autowired
    private XjspMapper xjspMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XjspModel> addXjsp(XjspModel model) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String s1 = String.valueOf(s.format(new Date().getTime()));
        model.setSsnf(s1);
        int result = xjspMapper.addXjsp(model);
        if (result > 0) {
            return new ResponseResult<>(true, "添加成功");
        }
        return new ResponseResult<>(false, "添加失败");
    }
}
