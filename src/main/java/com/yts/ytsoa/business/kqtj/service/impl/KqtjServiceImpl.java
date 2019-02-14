package com.yts.ytsoa.business.kqtj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.kqtj.mapper.KqtjMapper;
import com.yts.ytsoa.business.kqtj.model.KqtjModel;
import com.yts.ytsoa.business.kqtj.service.KqtjService;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KqtjServiceImpl implements KqtjService {
    @Autowired
    private KqtjMapper kqtjMapper;

    @Override
    public ResponseResult<PageInfo<KqtjModel>> findAll(int pageNow, int pageSize, KqtjModel kqtjModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<KqtjModel> list = kqtjMapper.findAll(kqtjModel);
        PageInfo<KqtjModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }
}
