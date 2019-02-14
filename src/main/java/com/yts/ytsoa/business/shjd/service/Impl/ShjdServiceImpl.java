package com.yts.ytsoa.business.shjd.service.Impl;

import com.yts.ytsoa.business.shjd.mapper.ShjdMapper;
import com.yts.ytsoa.business.shjd.model.ShjdModel;
import com.yts.ytsoa.business.shjd.service.ShjdService;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ShjdServiceImpl implements ShjdService {
    @Autowired
    private ShjdMapper shjdMapper;

    @Override
    public ResponseResult<ShjdModel> find(ShjdModel model) {
        ShjdModel result = shjdMapper.find(model);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<ShjdModel> insert(ShjdModel model) {
        String uuid = GetUuid.getUUID();
        model.setUuid(uuid);
        int result = shjdMapper.insert(model);
        if (result != 0) {
            return new ResponseResult<>(true, "添加成功");
        }
        return new ResponseResult<>(false, "添加失败");
    }

    @Override
    public ResponseResult<ShjdModel> update(ShjdModel model) {
        if (model != null) {
            int result = shjdMapper.update(model);
            if (result != 0) {
                return new ResponseResult<>(true, "修改成功");
            }
        }
        return new ResponseResult<>(false, "修改失败");
    }
}
