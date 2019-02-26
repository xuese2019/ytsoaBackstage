package com.yts.ytsoa.business.bgshjl.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bggl.mapper.BgglMapper;
import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.business.bgshjl.mapper.BgshjlMapper;
import com.yts.ytsoa.business.bgshjl.model.BgshjlModel;
import com.yts.ytsoa.business.bgshjl.service.BgshjlService;
import com.yts.ytsoa.business.bgshr.mapper.BgshrMapper;
import com.yts.ytsoa.business.bgshr.model.BgshrModel;
import com.yts.ytsoa.business.xmwp.mapper.XmwpMapper;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class BgshjlServiceImpl implements BgshjlService {

    @Autowired
    private BgshjlMapper bgshjlMapper;
    @Autowired
    private BgshrMapper bgshrMapper;

    @Autowired
    private BgglMapper bgglMapper;

    @Autowired
    private XmwpMapper xmwpMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<BgshjlModel> insert(BgshjlModel model, String accid) throws SQLException {
        //查询该条报告的项目信息，用作当前登陆人和项目负责人做匹配
        XmwpModel xmwpModel = bgshjlMapper.findXmfzr(model.getBgid());
        //查询报告审核人表，用作和当前登陆人做判断
        BgshrModel bgshrModel = bgshrMapper.find();
        if (accid.equals(xmwpModel.getXmfzr())) {
            int result = bgshjlMapper.insert(model);
            if (result != 0) {
                if (model.getTof() == 2) {
                    BgglModel bgglModel = new BgglModel();
                    bgglModel.setShjg(3);
                    bgglModel.setUuid(model.getBgid());
                    bgglMapper.update(bgglModel);
                    return new ResponseResult<>(true, "审核成功");
                }
            }
        } else if (bgshrModel != null) {
            if (accid.equals(bgshrModel.getBmjlid())) {
                int result = bgshjlMapper.insert(model);
                if (result != 0) {
                    if (model.getTof() == 2) {
                        BgglModel bgglModel = new BgglModel();
                        bgglModel.setShjg(4);
                        bgglModel.setUuid(model.getBgid());
                        bgglMapper.update(bgglModel);
                    }
                    return new ResponseResult<>(true, "审核成功");
                }
            } else if (accid.equals(bgshrModel.getZkbid())) {
                int result = bgshjlMapper.insert(model);
                if (result != 0) {
                    if (model.getTof() == 2) {
                        BgglModel bgglModel = new BgglModel();
                        bgglModel.setShjg(5);
                        bgglModel.setUuid(model.getBgid());
                        bgglMapper.update(bgglModel);
                    }
                    return new ResponseResult<>(true, "审核成功");
                }
            } else if (accid.equals(bgshrModel.getHhrid())) {
                int result = bgshjlMapper.insert(model);
                if (result != 0) {
                    if (model.getTof() == 2) {
                        BgglModel bgglModel = new BgglModel();
                        bgglModel.setShjg(6);
                        bgglModel.setUuid(model.getBgid());
                        bgglMapper.update(bgglModel);
                        List<BgglModel> list = bgglMapper.findBgsByUuid(xmwpModel.getUuid());
                        //判断该项目下所有的报告是否都是审核通过，如果所有报告都审核通过，则把项目的业务状态改未报告已出具
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getShjg() == 6) {
                                XmwpModel xmwpModel1 = new XmwpModel();
                                xmwpModel1.setUuid(xmwpModel.getUuid());
                                xmwpModel1.setYwzt(7);
                                xmwpMapper.updateYwzt(xmwpModel1);
                            }
                        }
                    }
                    return new ResponseResult<>(true, "审核成功");
                }
            }
        }
        return new ResponseResult<>(false, "权限不足无法审核");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<BgshjlModel>> find(int pageNow, int pageSize, BgshjlModel model) {
        PageHelper.startPage(pageNow, pageSize);
        List<BgshjlModel> list = bgshjlMapper.find(model);
        PageInfo<BgshjlModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "成功", page);
        }
        return new ResponseResult<>(false, "查无信息");
    }
}
