package com.yts.ytsoa.business.ywzt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.ywzt.mapper.YwztMapper;
import com.yts.ytsoa.business.ywzt.model.YwztModel;
import com.yts.ytsoa.business.ywzt.service.YwztService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class YwztServiceImpl implements YwztService {
    @Autowired
    private YwztMapper ywztMapper;

    @Override
    public ResponseResult<PageInfo<YwztModel>> findAll(int pageNow, int pageSize, YwztModel ywztModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<YwztModel> list = ywztMapper.findAll(ywztModel);
        PageInfo<YwztModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<YwztModel> add(YwztModel ywztModel) throws Exception {
        int list = ywztMapper.add(ywztModel);
        if (list != 0) {
            return new ResponseResult<>(true, "添加成功", null);
        } else {
            return new ResponseResult<>(false, "添加失败", null);
        }
    }
}
