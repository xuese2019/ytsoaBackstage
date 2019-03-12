package com.yts.ytsoa.business.bgshr.service.Impl;

import com.yts.ytsoa.business.bgshr.mapper.BgshrMapper;
import com.yts.ytsoa.business.bgshr.model.BgshrModel;
import com.yts.ytsoa.business.bgshr.service.BgshrszService;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BgshrszServiceImpl implements BgshrszService {
    @Autowired
    private BgshrMapper bgshrMapper;

    @Override
    public ResponseResult<BgshrModel> bgshrsz(BgshrModel model) throws Exception {
        int result = bgshrMapper.bgshrsz(model);
        if (result != 0) {
            return new ResponseResult<>(true, "报告审核人设置成功");
        }
        return new ResponseResult<>(false, "设置失败");
    }

    @Override
    public ResponseResult<BgshrModel> find() throws Exception {
        BgshrModel model = bgshrMapper.find();
        if (model != null) {
            return new ResponseResult<>(true, "查询成功", model);
        }
        return new ResponseResult<>(false, "查无信息");
    }
}
