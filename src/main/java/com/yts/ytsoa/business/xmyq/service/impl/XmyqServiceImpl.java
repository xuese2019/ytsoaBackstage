package com.yts.ytsoa.business.xmyq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmwp.mapper.XmwpMapper;
import com.yts.ytsoa.business.xmyq.mapper.XmyqMapper;
import com.yts.ytsoa.business.xmyq.model.XmyqModel;
import com.yts.ytsoa.business.xmyq.service.XmyqService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class XmyqServiceImpl implements XmyqService {
    @Autowired
    private XmyqMapper xmyqMapper;
    @Autowired
    private XmwpMapper xmwpMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmyqModel> add(XmyqModel xmyqModel) throws Exception {
        int list = xmyqMapper.add(xmyqModel);
        if (list != 0) {
            return new ResponseResult<>(true, "添加成功", null);
        } else {
            return new ResponseResult<>(false, "添加失败", null);
        }
    }

    @Override
    public ResponseResult<PageInfo<XmyqModel>> findAll(int pageNow, int pageSize, XmyqModel xmyqModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<XmyqModel> list = xmyqMapper.findAll(xmyqModel);
        PageInfo<XmyqModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败");
        }
    }
}

