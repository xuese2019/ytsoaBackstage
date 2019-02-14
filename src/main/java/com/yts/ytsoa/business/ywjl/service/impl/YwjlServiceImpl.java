package com.yts.ytsoa.business.ywjl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.ywjl.mapper.YwjlMapper;
import com.yts.ytsoa.business.ywjl.model.YwjlModel;
import com.yts.ytsoa.business.ywjl.service.YwjlService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class YwjlServiceImpl implements YwjlService {
    @Autowired
    private YwjlMapper ywjlMapper;

    /**
     * 查询所有
     *
     * @param pageNow
     * @param pageSize
     * @param ywjlModel
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult<PageInfo<YwjlModel>> findAll(int pageNow, int pageSize, YwjlModel ywjlModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<YwjlModel> list = ywjlMapper.findAll(ywjlModel);
        PageInfo<YwjlModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<YwjlModel> add(YwjlModel ywjlModel) throws Exception {
        int list = ywjlMapper.add(ywjlModel);
        if (list != 0) {
            return new ResponseResult<>(true, "添加成功", null);
        } else {
            return new ResponseResult<>(false, "添加失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<YwjlModel> delById(String uuid) throws Exception {
        ywjlMapper.delById(uuid);
        return new ResponseResult<>(true, "删除成功", null);
    }
}
