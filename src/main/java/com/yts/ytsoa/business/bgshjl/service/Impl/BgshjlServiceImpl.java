package com.yts.ytsoa.business.bgshjl.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bgshjl.mapper.BgshjlMapper;
import com.yts.ytsoa.business.bgshjl.model.BgshjlModel;
import com.yts.ytsoa.business.bgshjl.service.BgshjlService;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BgshjlServiceImpl implements BgshjlService {

    @Autowired
    private BgshjlMapper bgshjlMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<BgshjlModel> insert(BgshjlModel model) {
        String uuid = GetUuid.getUUID();
        model.setUuid(uuid);
        int result = bgshjlMapper.insert(model);
        if (result > 0) {
            return new ResponseResult<>(true, "添加成功");
        }
        return new ResponseResult<>(false, "添加失败");
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
