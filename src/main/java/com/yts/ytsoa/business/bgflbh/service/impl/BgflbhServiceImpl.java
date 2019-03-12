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

    @Override
    public ResponseResult<BglxModel> updateForthBglx() {
        BglxModel model = bgflbhMapper.findForthLb();
        if (model != null && model.getBgbhHz() >= 6999) {
            int result = bgflbhMapper.updateForthBgbh();
            if (result != 0) {
                return new ResponseResult<>(true, "报告编号4归零成功");
            }
        }
        return new ResponseResult<>(false, "报告编号4归零失败");
    }

    @Override
    public ResponseResult<BglxModel> updateFifthBglx() {
        BglxModel model = bgflbhMapper.findFifthLb();
        if (model != null && model.getBgbhHz() >= 999) {
            int result = bgflbhMapper.updateFifthBgbh();
            if (result != 0) {
                return new ResponseResult<>(true, "报告编号5归零成功");
            }
        }
        return new ResponseResult<>(false, "报告编号5归零失败");
    }

    @Override
    public ResponseResult<BglxModel> updateThirdBglx() {
        BglxModel model = bgflbhMapper.findThirdLb();
        if (model != null && model.getBgbhHz() >= 5999) {
            int result = bgflbhMapper.updateThirdBgbh();
            if (result != 0) {
                return new ResponseResult<>(true, "报告编号3归零成功");
            }
        }
        return new ResponseResult<>(false, "报告编号3归零失败");
    }

    @Override
    public ResponseResult<BglxModel> updateSeccondBglx() {
        BglxModel model = bgflbhMapper.findSeccondLb();
        if (model != null && model.getBgbhHz() >= 3999) {
            int result = bgflbhMapper.updateFirstBgbh();
            if (result != 0) {
                return new ResponseResult<>(true, "报告编号2归零成功");
            }
        }
        return new ResponseResult<>(false, "报告编号2归零失败");
    }

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

    /**
     * 如果当第一种报告类型等于1999的时候，修改回原始的1000
     *
     * @return
     */
    @Override
    public ResponseResult<BglxModel> updateFirstBglx() {
        BglxModel model = bgflbhMapper.findFirstLb();
        if (model != null && model.getBgbhHz() >= 1999) {
            int result = bgflbhMapper.updateFirstBgbh();
            if (result != 0) {
                return new ResponseResult<>(true, "报告编号1归零成功");
            }
        }
        return new ResponseResult<>(false, "报告编号2归零失败");
    }
}
