package com.yts.ytsoa.business.sz.service.impl;


import com.yts.ytsoa.business.sz.mapper.SzMapper;
import com.yts.ytsoa.business.sz.model.SzModel;
import com.yts.ytsoa.business.sz.service.SzService;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SzServiceImpl implements SzService {
    @Autowired
    private SzMapper szMapper;

    @Override
    public ResponseResult<List<SzModel>> findAll() throws Exception {
        List<SzModel> list = szMapper.findAll();
        if (list.size() > 0) {
            return new ResponseResult<>(true, "查询成功", list);
        } else {
            return new ResponseResult<>(false, "查询失败");
        }
    }

    @Override
    public ResponseResult<SzModel> findById(String uuid) throws Exception {
//        SzModel result = szMapper.findById();
//        if (result != null) {
//            return new ResponseResult<>(true, "查询成功", result);
//        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<SzModel> update(SzModel szModel) throws Exception {
        int result = szMapper.update(szModel);
        if (result > 0) {
            return new ResponseResult<>(true, "修改成功");
        }
        return new ResponseResult<>(false, "修改失败");
    }

    @Override
    public ResponseResult<SzModel> add(SzModel szModel) throws Exception {
        int result = szMapper.add(szModel);
        if (result > 0) {
            return new ResponseResult<>(true, "添加成功");
        }
        return new ResponseResult<>(false, "添加失败");
    }

    @Override
    public ResponseResult<SzModel> setSz(SzModel szModel) throws Exception {
        List<SzModel> list = szMapper.findAll();
        if (list.size() > 0) {
            szMapper.update(szModel);
        } else {
            szMapper.add(szModel);
        }
        return new ResponseResult<>(true, "成功");
    }
}
