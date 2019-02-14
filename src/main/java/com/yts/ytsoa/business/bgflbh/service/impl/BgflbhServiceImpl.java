package com.yts.ytsoa.business.bgflbh.service.impl;

import com.yts.ytsoa.business.bgflbh.mapper.BgflbhMapper;
import com.yts.ytsoa.business.bgflbh.model.BglxModel;
import com.yts.ytsoa.business.bgflbh.service.BgflbhService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class BgflbhServiceImpl implements BgflbhService {

    @Autowired
    private BgflbhMapper bgflbhMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<BglxModel> find(BglxModel model) throws Exception {
        BglxModel bglxModel = bgflbhMapper.find(model);
        bglxModel.setBgbhHz(bglxModel.getBgbhHz() + 1);
        bglxModel.setSsnf(new Date());
        BglxModel bglxModel1 = new BglxModel();
        bglxModel1.setBglx(model.getBglx());
        bglxModel1.setBgbhHz(bglxModel.getBgbhHz());
        bgflbhMapper.update(bglxModel1);
        if (bglxModel != null) {
            return new ResponseResult<>(true, "生成成功", bglxModel);
        }
        return new ResponseResult<>(false, "生成失败");
    }
}
