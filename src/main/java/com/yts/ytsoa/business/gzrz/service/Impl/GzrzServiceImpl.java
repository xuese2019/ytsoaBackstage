package com.yts.ytsoa.business.gzrz.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gzrz.mapper.GzrzMapper;
import com.yts.ytsoa.business.gzrz.model.GzrzModel;
import com.yts.ytsoa.business.gzrz.service.GzrzService;
import com.yts.ytsoa.business.xmcy.mapper.XmcyMapper;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class GzrzServiceImpl implements GzrzService {
    @Autowired
    private GzrzMapper mapper;
    @Autowired
    private XmcyMapper xmcyMapper;

    @Override
    public ResponseResult<PageInfo<GzrzModel>> find(int pageNow, int pageSize, GzrzModel model) {
        PageHelper.startPage(pageNow, pageSize);
        List<GzrzModel> list = mapper.find(model);
        PageInfo<GzrzModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查询失败");
    }

    @Override
    public ResponseResult<GzrzModel> addGzrz(GzrzModel model) {

        if (model != null) {
            int result = mapper.addGzrz(model);
            if (result != 0) {
                return new ResponseResult<>(true, "提交成功");
            }
        }
        return new ResponseResult<>(false, "非本项目的员工不能提交该项目的日志");
    }

    @Override
    public ResponseResult<PageInfo<GzrzModel>> findByXmid(int pageNow, int pageSize, GzrzModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<GzrzModel> list = mapper.findByXmid(model);
        PageInfo<GzrzModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查无信息");
    }

}
