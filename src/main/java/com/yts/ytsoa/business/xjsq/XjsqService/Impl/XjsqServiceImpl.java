package com.yts.ytsoa.business.xjsq.XjsqService.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.shrs.mapper.ShrsMapper;
import com.yts.ytsoa.business.shrs.model.ShrsModel;
import com.yts.ytsoa.business.xjsq.XjsqService.XjsqService;
import com.yts.ytsoa.business.xjsq.mapper.XjsqMapper;
import com.yts.ytsoa.business.xjsq.model.XjsqModel;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private ShrsMapper shrsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XjsqModel> addXjsq(XjsqModel model) throws Exception {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String s1 = String.valueOf(s.format(new Date().getTime()));
        model.setSsnf(s1);
        model.setShjg(1);
        xjsqMapper.addXjsq(model);
        return new ResponseResult<>(true, "添加成功");
    }

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

    @Override
    public ResponseResult<XjsqModel> findById(String uuid) throws SQLException {
        XjsqModel result = xjsqMapper.findById(uuid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查询失败");
    }

    @Override
    public ResponseResult<XmshModel> xjsh(XmshModel model, HttpServletRequest request) throws Exception {
        XjsqModel xjsqModel = new XjsqModel();
        String accid = JWTUtils.getAccId(request);
        ShrsModel shrsModel = shrsMapper.findBylb(6);
        if (shrsModel != null && accid.equals(shrsModel.getBmjl())) {
            model.setShsj(new Date());
            model.setShr(accid);
            int result = xmshMapper.add(model);
            if (result > 0) {
                if (model.getShjg() == 2) {
                    xjsqModel.setUuid(model.getPrentid());
                    xjsqModel.setShjg(4);
                    xjsqMapper.update(xjsqModel);
                    return new ResponseResult<>(true, "审核完成");
                }
                return new ResponseResult<>(false, "部门经理审核没通过");
            }
            return new ResponseResult<>(false, "审核失败");
        }
        if (accid.equals(shrsModel.getSz())) {
            if (model.getShjg() == 4) {
                model.setShsj(new Date());
                model.setShr(accid);
                xmshMapper.add(model);
                if (model.getShjg() == 4) {
                    xjsqModel.setUuid(model.getPrentid());
                    xjsqModel.setShjg(5);
                    xjsqMapper.update(xjsqModel);
                    return new ResponseResult<>(true, "审核完成");
                }
                return new ResponseResult<>(false, "所长审核没通过");
            }
            return new ResponseResult<>(false, "审核失败");
        }
        return new ResponseResult<>(false, "审核未通过");
    }
}

