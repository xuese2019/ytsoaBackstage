package com.yts.ytsoa.business.xmjz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmjz.mapper.XmjzMapper;
import com.yts.ytsoa.business.xmjz.modedl.XmjzModel;
import com.yts.ytsoa.business.xmjz.service.XmjzService;
import com.yts.ytsoa.business.xmwp.mapper.XmwpMapper;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class XmjzServiceImpl implements XmjzService {
    @Autowired
    private XmjzMapper xmjzMapper;
    @Autowired
    private XmwpMapper xmwpMapper;

    @Override
    public ResponseResult<PageInfo<XmjzModel>> findAll(int pageNow, int pageSize, XmjzModel xmjzModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<XmjzModel> list = xmjzMapper.findAll(xmjzModel);
        PageInfo<XmjzModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmjzModel> add(XmjzModel xmjzModel) throws Exception {
        XmwpModel xmwpModel = xmwpMapper.findByUuid(xmjzModel.getXmid());
        if (xmwpModel != null && xmwpModel.getYwzt() < 3) {
            xmjzModel.setRq(new Date());
            int result = xmjzMapper.add(xmjzModel);
            if (result != 0) {
                return new ResponseResult<>(true, "添加项目进展成功");
            }
            return new ResponseResult<>(false, "添加失败");
        }
        return new ResponseResult<>(false, "项目审核过后无法添加项目进展");
    }

}
