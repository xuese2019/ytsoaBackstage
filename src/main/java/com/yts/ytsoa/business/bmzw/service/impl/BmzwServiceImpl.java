package com.yts.ytsoa.business.bmzw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bmzw.mapper.BmzwMapper;
import com.yts.ytsoa.business.bmzw.model.BmzwModel;
import com.yts.ytsoa.business.bmzw.service.BmzwService;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BmzwServiceImpl implements BmzwService {
    @Autowired
    private BmzwMapper bmzwMapper;

    @Override
    public ResponseResult<PageInfo<BmzwModel>> findAll(int pageNow, int pageSize, BmzwModel bmzwModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<BmzwModel> list = bmzwMapper.findAll(bmzwModel);
        PageInfo<BmzwModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Override
    public ResponseResult<BmzwModel> findById(String uuid) throws Exception {
        BmzwModel result = bmzwMapper.findById(uuid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<BmzwModel> update(BmzwModel bmzwModel) throws Exception {
        int result = bmzwMapper.update(bmzwModel);
        if (result > 0) {
            return new ResponseResult<>(true, "修改成功");
        }
        return new ResponseResult<>(false, "修改失败");
    }

    @Override
    public ResponseResult<BmzwModel> deleteById(String uuid) throws Exception {
        bmzwMapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<BmzwModel> add(BmzwModel bmzwModel) throws Exception {
        int result = bmzwMapper.add(bmzwModel);
        if (result > 0) {
            return new ResponseResult<>(true, "添加成功");
        }
        return new ResponseResult<>(false, "添加失败");
    }

}
