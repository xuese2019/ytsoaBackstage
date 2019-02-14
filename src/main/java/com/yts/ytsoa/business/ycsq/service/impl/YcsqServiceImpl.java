package com.yts.ytsoa.business.ycsq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.ycsq.mapper.YcsqMapper;
import com.yts.ytsoa.business.ycsq.model.YcsqModel;
import com.yts.ytsoa.business.ycsq.service.YcsqService;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class YcsqServiceImpl implements YcsqService {
    @Autowired
    private YcsqMapper ycsqMapper;
    @Autowired
    private XmshMapper xmshMapper;

    @Override
    public ResponseResult<PageInfo<YcsqModel>> findAll(int pageNow, int pageSize, YcsqModel ycsqModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<YcsqModel> list = ycsqMapper.findAll(ycsqModel);
        PageInfo<YcsqModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<YcsqModel> add(YcsqModel ycsqModel) throws Exception {
        String uuid = GetUuid.getUUID();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String sqrq = s.format(System.currentTimeMillis());
        Date sd = s.parse(sqrq);
        ycsqModel.setSqrq(sd);
        int list = ycsqMapper.add(ycsqModel);
/*     XmshModel model = new XmshModel();
        model.setUuid(uuid);
        model.setPrentid(uuid);
        model.setShr(ycsqModel.getShr());
      *//*  model.setShsj(new Date());
        model.setShyj("无");
        model.setShjg(1);*//*
        xmshMapper.add(model);*/
        if (list != 0) {
            return new ResponseResult<>(true, "添加成功", null);
        } else {
            return new ResponseResult<>(false, "添加失败", null);
        }
    }

    @Override
    public ResponseResult<List<YcsqModel>> findById(YcsqModel ycsqModel) throws Exception {
        List<YcsqModel> list = ycsqMapper.findById(ycsqModel.getUuid());
        if (list.size() > 0) {
            return new ResponseResult<>(true, "查询成功", list);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<YcsqModel> updById(YcsqModel ycsqModel) throws Exception {
        int result = ycsqMapper.updById(ycsqModel);
        if (result > 0) {
            return new ResponseResult<>(true, "修改成功");
        } else return new ResponseResult<>(false, "修改失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmshModel> update(XmshModel model) throws Exception {

        String uuid = GetUuid.getUUID();
        model.setUuid(uuid);
        model.setShsj(new Date());
        int result = xmshMapper.add(model);
        if (result != 0) {
            YcsqModel ycsqModel = new YcsqModel();
            ycsqModel.setUuid(model.getPrentid());
            ycsqModel.setShjg(model.getShjg());
            ycsqMapper.update(ycsqModel);
            return new ResponseResult<>(true, "审核成功");
        }
        return new ResponseResult<>(false, "审核失败");
    }
}
