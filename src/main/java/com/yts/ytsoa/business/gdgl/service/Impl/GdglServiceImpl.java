package com.yts.ytsoa.business.gdgl.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gdgl.mapper.GdglMapper;
import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.gdgl.query.GdglQueryModel;
import com.yts.ytsoa.business.gdgl.result.ResultModel;
import com.yts.ytsoa.business.gdgl.service.GdglService;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
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
public class GdglServiceImpl implements GdglService {
    @Autowired
    private GdglMapper gdglMapper;
    @Autowired
    private XmshMapper xmshMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<GdglModel> addGdgl(GdglModel model) throws Exception {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String ssnf = s.format(date);
        model.setSsnf(ssnf);
        int result = gdglMapper.addGdsq(model);
        if (result > 0) {
            return new ResponseResult<>(true, "添加成功");
        }
        return new ResponseResult<>(false, "添加失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<GdglModel>> find(int pageNow, int pageSize, GdglModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<GdglModel> list = gdglMapper.find(model);
        PageInfo<GdglModel> page = new PageInfo<>(list);
        model.setZt(1);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查询失败");
    }

    /*          if (model != null && model.getZt() < 2) {
           return new ResponseResult<>(true, "查询成功");
       } else {
           return new ResponseResult<>(false, "项目已归档，不需要再次归档");
       }*/
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<ResultModel>> findGdsh(int pageNow, int pageSize, GdglQueryModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<ResultModel> list = gdglMapper.findGdsh(model);
        PageInfo<ResultModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查无数据");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<GdglModel> updById(GdglModel gdglModel) throws Exception {
        int result = gdglMapper.updById(gdglModel);
        if (result > 0) {
            return new ResponseResult<>(true, "修改成功");
        } else return new ResponseResult<>(false, "修改失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<GdglModel> findById(String uuid) throws Exception {
        GdglModel list = gdglMapper.findById(uuid);
        if (list != null) {
            return new ResponseResult<>(true, "成功", list);
        } else {
            return new ResponseResult<>(false, "未查询到记录");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmshModel> update(XmshModel model) throws Exception {
        model.setShsj(new Date());
        int result = xmshMapper.add(model);
        if (result != 0) {
            GdglModel gdglModel = new GdglModel();
            gdglModel.setUuid(model.getPrentid());
            gdglModel.setStatus(model.getShjg());
            gdglMapper.update(gdglModel);
            return new ResponseResult<>(true, "审核成功");
        }
        return new ResponseResult<>(false, "审核失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<List<GdglModel>> findByDamc(GdglModel model) throws Exception {
        List<GdglModel> list = gdglMapper.findByDamc(model);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "查询成功", list);
        }
        return new ResponseResult<>(false, "查无信息");
    }
}