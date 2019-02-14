package com.yts.ytsoa.business.tpsq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.tpsq.mapper.TpsqMapper;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<TpsqModel> add(TpsqModel tpsqModel) throws Exception {
        int result = tpsqMapper.add(tpsqModel);
        if (result != 0) {
            return new ResponseResult<>(true, "添加成功", null);
        } else {
            return new ResponseResult<>(false, "添加失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<TpsqModel>> findAll(int pageNow, int pageSize, TpsqModel tpsqModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<TpsqModel> list = tpsqMapper.findAll(tpsqModel);
        PageInfo<TpsqModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmshModel> tpsh(XmshModel model) throws Exception {
        int result = tpsqMapper.tpsh(model);
        if (result != 0) {
            if (model.getShjg() > 1) {
                TpsqModel model1 = new TpsqModel();
                model1.setUuid(model.getPrentid());
                model1.setShzt(2);
                tpsqMapper.update(model1);
            }
            return new ResponseResult<>(true, "审核成功");
        }
        return new ResponseResult<>(false, "审核失败");
    }

    @Transactional(rollbackFor = Exception.class)
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
}
