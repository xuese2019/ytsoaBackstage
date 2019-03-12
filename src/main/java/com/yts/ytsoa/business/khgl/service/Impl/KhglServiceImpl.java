package com.yts.ytsoa.business.khgl.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.khgl.mapper.KhglMapper;
import com.yts.ytsoa.business.khgl.model.KhglModel;
import com.yts.ytsoa.business.khgl.service.KhglService;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KhglServiceImpl implements KhglService {
    @Autowired
    private KhglMapper khglMapper;

    @Override
    public ResponseResult<PageInfo<KhglModel>> findAll(int pageNow, int pageSize, KhglModel khglModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<KhglModel> list = khglMapper.findAll(khglModel);
        PageInfo<KhglModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<KhglModel> add(KhglModel khglModel) throws Exception {
        int result = khglMapper.add(khglModel);
        if (result > 0) {
            return new ResponseResult<>(true, "添加成功！");
        }
        return new ResponseResult<>(false, "添加失败！");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<KhglModel> deleteById(String uuid) throws Exception {
        int result = khglMapper.delById(uuid);
        if (result > 0) {
            return new ResponseResult<>(true, "删除成功！");
        }
        return new ResponseResult<>(false, "删除失败！");
    }

    @Override
    public ResponseResult<List<KhglModel>> findById(String uuid) throws Exception {
        List<KhglModel> result = khglMapper.findById(uuid);
        if (result.size() > 0) {
            return new ResponseResult<>(true, "查询成功！", result);
        }
        return new ResponseResult<>(false, "查询失败！");

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<KhglModel> updById(KhglModel khglModel) throws Exception {
        int updById = khglMapper.updById(khglModel);
        if (updById > 0) {
            return new ResponseResult<>(true, "修改成功");
        } else return new ResponseResult<>(false, "修改失败");
    }
}

