package com.yts.ytsoa.business.xjsq.XjsqService.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.account.mapper.AccountMapper;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.shrs.mapper.ShrsMapper;
import com.yts.ytsoa.business.sz.mapper.SzMapper;
import com.yts.ytsoa.business.xjsq.XjsqService.XjsqService;
import com.yts.ytsoa.business.xjsq.mapper.XjsqMapper;
import com.yts.ytsoa.business.xjsq.model.XjsqModel;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.GetUuid;
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
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private SzMapper szMapper;

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
    public ResponseResult<PageInfo<XjsqModel>> find(int pageNow, int pageSize, XjsqModel model, HttpServletRequest request) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        String accid = JWTUtils.getAccId(request);
        AccountModel model1 = accountMapper.findById(accid);
        if (model1 != null) {
            if (model1.getBm().equals("0") && model1.getBmzw().equals("0")) {
                model.setShjg(2);
                List<XjsqModel> byBm = xjsqMapper.findByBms(model);
                PageInfo page = new PageInfo<>(byBm);
                if (byBm.size() > 0) {
                    return new ResponseResult<>(true, "所长查询成功", page);
                }
                return new ResponseResult<>(false, "查无数据");
            }
            if (model1.getBmzw().equals("0")) {
                List<XjsqModel> byBm = xjsqMapper.findByBm(model1.getBm(), 1);
                PageInfo<XjsqModel> results = new PageInfo<>(byBm);
                if (results.getSize() > 0) {
                    return new ResponseResult<>(true, "经理查询成功", results);
                } else {
                    return new ResponseResult<>(false, "查无结果");
                }
            }

        }
        return new ResponseResult<>(false, "查无数据");
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
    public ResponseResult<PageInfo<XjsqModel>> kqgl(int pageNow, int pageSize, XjsqModel model, HttpServletRequest request) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<XjsqModel> list = xjsqMapper.kqgl(model);
        PageInfo<XjsqModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Override
    public ResponseResult<XmshModel> xjsh(XmshModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        String uuid = GetUuid.getUUID();
        model.setUuid(uuid);
        model.setShr(accid);
        model.setShsj(new Date());
        int result = xmshMapper.add(model);
        if (result != 0) {
            AccountModel accountModel = accountMapper.findById(accid);
            if (accountModel != null) {
                if (accountModel.getBmzw().equals("0") && accountModel.getBm().equals("0")) {
                    if (model.getShjg() == 2) {
                        XjsqModel xjsqModel = new XjsqModel();
                        xjsqModel.setUuid(model.getPrentid());
                        xjsqModel.setShjg(5);
                        xjsqMapper.update(xjsqModel);
                    } else {
                        XjsqModel xjsqModel = new XjsqModel();
                        xjsqModel.setUuid(model.getPrentid());
                        xjsqModel.setShjg(1);
                        xjsqMapper.update(xjsqModel);
                    }

                } else if (accountModel.getBmzw().equals("0")) {
                    if (model.getShjg() == 2) {
                        XjsqModel xjsqModel = new XjsqModel();
                        xjsqModel.setUuid(model.getPrentid());
                        xjsqModel.setShjg(2);
                        xjsqMapper.update(xjsqModel);
                    }
                }
            }
            return new ResponseResult<>(true, "审核成功");
        }
        return new ResponseResult<>(false, "审核失败");
    }


    @Override
    public ResponseResult<XjsqModel> findByBm(String bm) throws SQLException {
        XjsqModel result = xjsqMapper.findById(bm);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查询失败");
    }
}

