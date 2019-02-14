package com.yts.ytsoa.business.xjsq.XjsqService.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.xjsq.XjsqService.XjsqService;
import com.yts.ytsoa.business.xjsq.mapper.XjsqMapper;
import com.yts.ytsoa.business.xjsq.model.XjsqModel;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class XjsqServiceImpl implements XjsqService {
    @Autowired
    private XjsqMapper xjsqMapper;
    @Autowired
    private XmshMapper xmshMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XjsqModel> addXjsq(XjsqModel model) throws Exception {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String s1 = String.valueOf(s.format(new Date().getTime()));
        model.setSsnf(s1);
        model.setSfty(1);
        int result = xjsqMapper.addXjsq(model);
        if (result > 0) {
            return new ResponseResult<>(true, "添加成功");
        }
        return new ResponseResult<>(false, "添加失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<XjsqModel>> find(int pageNow, int pageSize, XjsqModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<XjsqModel> list = xjsqMapper.find(model);
        PageInfo<XjsqModel> result = new PageInfo<>(list);
        if (result.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查询失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XjsqModel> findById(String uuid) throws SQLException {
        XjsqModel result = xjsqMapper.findById(uuid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查询失败");
    }

    @Override
    public ResponseResult<XmshModel> xjsh(XmshModel model) throws Exception {
        model.setShsj(new Date());
        int result = xmshMapper.add(model);
        if (result > 0) {
            XjsqModel model1 = new XjsqModel();
            model1.setUuid(model.getPrentid());
            model1.setSfty(model.getShjg());
            xjsqMapper.update(model1);
            return new ResponseResult<>(true, "审核完成");
        }
        return new ResponseResult<>(false, "审核失败");
    }
}
