package com.yts.ytsoa.business.tpsq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.tpsq.mapper.TpsqMapper;
import com.yts.ytsoa.business.tpsq.model.ResultTpsqModel;
import com.yts.ytsoa.business.tpsq.model.TpsqModel;
import com.yts.ytsoa.business.tpsq.service.TpsqService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TpsqServiceImpl implements TpsqService {
    @Autowired
    private TpsqMapper tpsqMapper;
    @Autowired
    private XmshMapper xmshMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<TpsqModel> add(TpsqModel tpsqModel) throws Exception {
        int result = tpsqMapper.add(tpsqModel);
        if (result != 0) {
            return new ResponseResult<>(true, "添加成功");
        } else {
            return new ResponseResult<>(false, "添加失败");
        }
    }

    @Override
    public ResponseResult<PageInfo<TpsqModel>> findAll(int pageNow, int pageSize, TpsqModel tpsqModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<TpsqModel> list = tpsqMapper.findAll(tpsqModel);
        PageInfo<TpsqModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查无信息", null);
        }
    }

    @Override
    public ResponseResult<XmshModel> tpsh(XmshModel model) throws Exception {
        int result = xmshMapper.add(model);
        if (result != 0) {
            TpsqModel tpsqModel = new TpsqModel();
            tpsqModel.setUuid(model.getPrentid());
            tpsqModel.setShjg(model.getShjg());
            tpsqMapper.update(tpsqModel);
            return new ResponseResult<>(true, "审核成功");
        }
        return new ResponseResult<>(false, "审核未通过");
    }


    @Override
    public ResponseResult<TpsqModel> findById(String uuid) throws Exception {
        if (uuid != null && !uuid.isEmpty()) {
            TpsqModel result = tpsqMapper.findById(uuid);
            if (result != null) {
                return new ResponseResult<>(true, "查询成功", result);
            }
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<List<ResultTpsqModel>> findByShjl(String prentid) throws Exception {
        List<ResultTpsqModel> list = tpsqMapper.findByShjl(prentid);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "查询成功", list);
        } else {
            return new ResponseResult<>(false, "无审核记录");
        }
    }
}
