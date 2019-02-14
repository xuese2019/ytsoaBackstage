package com.yts.ytsoa.business.ywjlhf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.ywjlhf.mapper.YwjlhfMapper;
import com.yts.ytsoa.business.ywjlhf.model.YwjlhfModel;
import com.yts.ytsoa.business.ywjlhf.service.YwjlhfService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.websocket.WebSocketController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class YwjlhfServiceImpl implements YwjlhfService {
    @Autowired
    private YwjlhfMapper ywjlhfMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<YwjlhfModel> add(YwjlhfModel ywjlhfModel) throws Exception {
        int list = ywjlhfMapper.add(ywjlhfModel);
        WebSocketController.sendInfo("");
        if (list != 0) {
            return new ResponseResult<>(true, "添加成功", null);
        } else {
            return new ResponseResult<>(false, "添加失败", null);
        }
    }

    @Override
    public ResponseResult<PageInfo<YwjlhfModel>> findAll(int pageNow, int pageSize, YwjlhfModel ywjlhfModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<YwjlhfModel> list = ywjlhfMapper.findAll(ywjlhfModel);
        PageInfo<YwjlhfModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }
}
