package com.yts.ytsoa.business.cwgl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.cwgl.mapper.CwglMapper;
import com.yts.ytsoa.business.cwgl.model.CwglModel;
import com.yts.ytsoa.business.cwgl.service.CwglService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CwglServiceImpl implements CwglService {
    @Autowired
    private CwglMapper cwglMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<CwglModel> add(CwglModel cwglModel) throws Exception {
        int list = cwglMapper.add(cwglModel);
        if (list != 0) {
            return new ResponseResult<>(true, "添加成功", null);
        } else {
            return new ResponseResult<>(false, "添加失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<CwglModel>> findAll(int pageNow, int pageSize, CwglModel cwglModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<CwglModel> list = cwglMapper.findAll(cwglModel);
        PageInfo<CwglModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "成功", page);
        } else {
            return new ResponseResult<>(false, "未查询到记录", null);
        }
    }

    @Override
    public ResponseResult<List<CwglModel>> findById(String uuid) throws Exception {
        List<CwglModel> list = cwglMapper.findById(uuid);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "查询成功", list);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Override
    public ResponseResult<CwglModel> updById(CwglModel cwglModel) throws Exception {
        int result = cwglMapper.updById(cwglModel);
        if (result > 0) {
            return new ResponseResult<>(true, "修改成功");
        } else return new ResponseResult<>(false, "修改失败");
    }
}

