package com.yts.ytsoa.business.gdgl.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bggl.mapper.BgglMapper;
import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.business.gdgl.mapper.GdglMapper;
import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.gdgl.model.GdglResultModel;
import com.yts.ytsoa.business.gdgl.query.GdglQueryModel;
import com.yts.ytsoa.business.gdgl.result.ResultModel;
import com.yts.ytsoa.business.gdgl.service.GdglService;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.xmcj.model.XmzmcModel;
import com.yts.ytsoa.business.xmwp.mapper.XmwpMapper;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
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
public class GdglServiceImpl implements GdglService {
    @Autowired
    private GdglMapper gdglMapper;
    @Autowired
    private XmshMapper xmshMapper;
    @Autowired
    private BgglMapper bgglMapper;

    @Autowired
    private XmwpMapper xmwpMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<GdglModel> addGdgl(GdglModel model) throws Exception {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        BgglModel byXmZmc = bgglMapper.findByXmZmc(model.getXmzmc());
        if (byXmZmc.getShjg() >= 6) {
            String ssnf = s.format(date);
            model.setSsnf(ssnf);
            model.setShjg(1);
            int result = gdglMapper.addGdsq(model);
            if (result > 0) {
                return new ResponseResult<>(true, "添加成功");
            } else {
                return new ResponseResult<>(false, "添加未能完成");
            }
        }
        return new ResponseResult<>(false, "报告未审核完成，不能申请归档");
    }


    @Override
    public ResponseResult<PageInfo<GdglModel>> find(int pageNow, int pageSize, GdglModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        /*      model.setJyzt(1);*/
        List<GdglModel> list = gdglMapper.find(model);
        PageInfo<GdglModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查无数据");
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
        } else return new ResponseResult<>(false, "修改未能完成");
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
        String uuid = GetUuid.getUUID();
        model.setUuid(uuid);
        model.setShsj(new Date());
        int result = xmshMapper.add(model);
        if (result != 0) {
            if (model.getShjg() == 2) {
                GdglModel gdglModel = new GdglModel();
                gdglModel.setUuid(model.getPrentid());
                gdglModel.setShjg(model.getShjg());
                gdglModel.setWczt(3);
                gdglMapper.update(gdglModel);
            }
            //根据审核记录的主键查出项目的详情
            XmwpModel xmwpModel = gdglMapper.findXmwpByShjlUuid(model.getUuid());
            if (xmwpModel != null) {
                //查询该项目下所有的归档记录，判断wczt是不是等于3，如果都等于3，那么把项目的业务状态改为8
                List<GdglModel> list = gdglMapper.findGdglByXmUuid(xmwpModel.getUuid());
                if (list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getWczt() == 3) {
                            XmwpModel xmwpModel1 = new XmwpModel();
                            xmwpModel1.setYwzt(8);
                            xmwpModel1.setUuid(xmwpModel.getUuid());
                            xmwpMapper.updateYwzt(xmwpModel1);
                        }
                    }
                }
            }
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

    @Override
    public ResponseResult<List<XmzmcModel>> findByUuid(String uuid) throws Exception {
        List<XmzmcModel> list = gdglMapper.findByUuid(uuid);
        if (list.size() != 0) {
            return new ResponseResult<>(true, "查询成功", list);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<List<BgglModel>> findBgByUuid(String uuid) throws Exception {
        List<BgglModel> list = gdglMapper.findBgByUuid(uuid);
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getShjg() > 6) {
                    return new ResponseResult<>(true, "查询成功", list);
                }
                return new ResponseResult<>(false, "查无信息，报告未审核通过");
            }
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<List<GdglResultModel>> findByShjl(String prentid) throws Exception {
        List<GdglResultModel> list = gdglMapper.findByShjl(prentid);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "查询成功", list);
        } else {
            return new ResponseResult<>(false, "无审核记录");
        }
    }

    @Override
    public ResponseResult<List<GdglModel>> findGdByXmid(int pageNow, int pageSize, GdglModel model, String xmid) throws Exception {
        //        用xmid 查询所有的子项目
        List<GdglModel> gdglModels = gdglMapper.findGdByXmid(xmid);
        if (gdglModels.size() > 0) {
            return new ResponseResult<>(true, "查询成功", gdglModels);
        }
        return new ResponseResult<>(false, "查无信息");
    }
}
