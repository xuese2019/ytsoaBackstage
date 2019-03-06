package com.yts.ytsoa.business.shjl.service.impl;

import com.yts.ytsoa.business.bggl.mapper.BgglMapper;
import com.yts.ytsoa.business.gdgl.mapper.GdglMapper;
import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.shjl.service.XmshService;
import com.yts.ytsoa.business.xmwp.mapper.XmwpMapper;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.business.ycsq.mapper.YcsqMapper;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class XmshServiceImpl implements XmshService {
    @Autowired
    private XmshMapper xmshMapper;
    @Autowired
    private YcsqMapper ycsqMapper;
    @Autowired
    private XmwpMapper xmwpMapper;
    @Autowired
    private GdglMapper gdglMapper;
    @Autowired
    private BgglMapper bgglMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<XmshModel> add(XmshModel xmshModel) throws Exception {
        String uuid = GetUuid.getUUID();
        xmshModel.setShsj(new Date());
        xmshModel.setUuid(uuid);
        int result = xmshMapper.add(xmshModel);
        if (result != 0) {
            XmwpModel xmwpModel = new XmwpModel();
            xmwpModel.setUuid(xmshModel.getPrentid());
            xmwpModel.setYwzt(xmshModel.getShjg());
            xmwpMapper.update(xmwpModel);
            return new ResponseResult<>(true, "审核记录添加成功");
        }
        return new ResponseResult<>(false, "添加审核记录失败");
    }

    @Override
    public ResponseResult<XmshModel> findById(String uuid) throws SQLException {
        XmshModel result = xmshMapper.findById(uuid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<List<XmshModel>> findShjl(String prentid) throws Exception {
        if (prentid != null) {
            List<XmshModel> list = xmshMapper.findShjl(prentid);
            if (list.size() != 0) {
                return new ResponseResult<>(true, "查询成功", list);
            }
        }
        return new ResponseResult<>(false, "查无记录");
    }

    @Override
    public ResponseResult<XmshModel> jysh(XmshModel model, String accid) throws Exception {
        model.setShr(accid);
        model.setShsj(new Date());
        int result = xmshMapper.add(model);
        if (result > 0) {
            GdglModel gdglModel = new GdglModel();
            gdglModel.setUuid(model.getPrentid());
            gdglModel.setJyzt(2);
            gdglMapper.updatejyzt(gdglModel);
            return new ResponseResult<>(true, "审核成功");
        }
        return new ResponseResult<>(false, "审核失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<XmshModel> bgsh(XmshModel model, String accid) throws Exception {
        return null;
    }
}




