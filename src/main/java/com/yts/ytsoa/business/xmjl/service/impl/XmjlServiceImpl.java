package com.yts.ytsoa.business.xmjl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmjl.mapper.XmjlMapper;
import com.yts.ytsoa.business.xmjl.model.XmjlModel;
import com.yts.ytsoa.business.xmjl.service.XmjlService;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class XmjlServiceImpl implements XmjlService {
    @Autowired
    private XmjlMapper xmjlMapper;

    @Override
    public ResponseResult<PageInfo<XmjlModel>> findAll(int pageNow, int pageSize, XmjlModel xmjlModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<XmjlModel> list = xmjlMapper.findAll(xmjlModel);
        PageInfo<XmjlModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }
}
