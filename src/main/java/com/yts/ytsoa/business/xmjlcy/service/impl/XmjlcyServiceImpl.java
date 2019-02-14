package com.yts.ytsoa.business.xmjlcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmjlcy.mapper.XmjlcyMapper;
import com.yts.ytsoa.business.xmjlcy.model.XmjlcyModel;
import com.yts.ytsoa.business.xmjlcy.service.XmjlcyService;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class XmjlcyServiceImpl implements XmjlcyService {
    @Autowired
    private XmjlcyMapper xmjlcyMapper;

    @Override
    public ResponseResult<PageInfo<XmjlcyModel>> findAll(int pageNow, int pageSize, XmjlcyModel xmjlcyModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<XmjlcyModel> list = xmjlcyMapper.findAll(xmjlcyModel);
        PageInfo<XmjlcyModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmjlcyModel> add(XmjlcyModel xmjlcyModel) throws Exception {
        int list = xmjlcyMapper.add(xmjlcyModel);
        if (list != 0) {
            return new ResponseResult<>(true, "添加成功", null);
        } else {
            return new ResponseResult<>(false, "添加失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmjlcyModel> delById(String uuid) throws Exception {
        xmjlcyMapper.delById(uuid);
        return new ResponseResult<>(true, "删除成功", null);
    }

    @Override
    public ResponseResult<List<XmjlcyModel>> findById(String uuid) throws Exception {
        List<XmjlcyModel> list = xmjlcyMapper.findById(uuid);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "查询成功", list);
        } else {

            return new ResponseResult<>(false, "查询失败", null);
        }
    }
}
