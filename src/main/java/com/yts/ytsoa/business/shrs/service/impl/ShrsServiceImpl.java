package com.yts.ytsoa.business.shrs.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shrs.mapper.ShrsMapper;
import com.yts.ytsoa.business.shrs.model.ShrsModel;
import com.yts.ytsoa.business.shrs.service.ShrsService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ShrsServiceImpl implements ShrsService {
    @Autowired
    private ShrsMapper shrsMapper;

    @Override
    public ResponseResult<PageInfo<ShrsModel>> findAll(int pageNow, int pageSize, ShrsModel shrsModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<ShrsModel> list = shrsMapper.findAll(shrsModel);
        PageInfo<ShrsModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Override
    public ResponseResult<ShrsModel> findById(String uuid) throws Exception {
        ShrsModel result = shrsMapper.findById(uuid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<ShrsModel> add(ShrsModel shrsModel) throws Exception {
        int list = shrsMapper.add(shrsModel);
        if (list != 0) {
            return new ResponseResult<>(true, "添加成功", null);
        } else {
            return new ResponseResult<>(false, "添加失败", null);
        }
    }

    @Override
    public ResponseResult<ShrsModel> delById(String uuid) throws Exception {
        shrsMapper.delById(uuid);
        return new ResponseResult<>(true, "删除成功", null);
    }

    @Override
    public ResponseResult<ShrsModel> updById(ShrsModel shrsModel) throws Exception {
        int result = shrsMapper.updById(shrsModel);
        if (result > 0) {
            return new ResponseResult<>(true, "修改成功");
        } else return new ResponseResult<>(false, "修改失败");
    }
}
