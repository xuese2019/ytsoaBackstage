package com.yts.ytsoa.business.bm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bm.mapper.BmMapper;
import com.yts.ytsoa.business.bm.model.BmModel;
import com.yts.ytsoa.business.bm.service.BmService;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BmServiceImpl implements BmService {
    @Autowired
    private BmMapper bmMapper;

    @Override
    public ResponseResult<PageInfo<BmModel>> findAll(int pageNow, int pageSize, BmModel bmModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<BmModel> list = bmMapper.findAll(bmModel);
        PageInfo<BmModel> page = new PageInfo<>(list);
        BmModel model = new BmModel();
        model.setUuid("0");
        model.setBmmc("高层");
        model.setBmzwList(new ArrayList<>());
        page.getList().add(model);
        return new ResponseResult<>(true, "查询成功", page);
    }

    @Override
    public ResponseResult<BmModel> findById(String uuid) throws Exception {
        BmModel result = bmMapper.findById(uuid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<BmModel> update(BmModel bmModel) throws Exception {
        int result = bmMapper.update(bmModel);
        if (result > 0) {
            return new ResponseResult<>(true, "修改成功");
        }
        return new ResponseResult<>(false, "修改失败");
    }

    @Override
    public ResponseResult<BmModel> deleteById(String uuid) throws Exception {
        bmMapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<BmModel> add(BmModel bmModel) throws Exception {
        int result = bmMapper.add(bmModel);
        if (result > 0) {
            return new ResponseResult<>(true, "添加成功");
        }
        return new ResponseResult<>(false, "添加失败");
    }
}
