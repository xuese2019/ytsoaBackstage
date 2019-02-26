package com.yts.ytsoa.business.gzrz.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gzrz.mapper.GzrzMapper;
import com.yts.ytsoa.business.gzrz.model.GzrzModel;
import com.yts.ytsoa.business.gzrz.service.GzrzService;
import com.yts.ytsoa.business.xmcy.mapper.XmcyMapper;
import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.utils.DateUtils;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class GzrzServiceImpl implements GzrzService {
    @Autowired
    private GzrzMapper gzrzMapper;
    @Autowired
    private XmcyMapper xmcyMapper;

    @Override
    public ResponseResult<PageInfo<GzrzModel>> find(int pageNow, int pageSize, GzrzModel model) {
        PageHelper.startPage(pageNow, pageSize);
        List<GzrzModel> list = gzrzMapper.find(model);
        PageInfo<GzrzModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查询失败");
    }

    @Override
    public ResponseResult<GzrzModel> addGzrz(GzrzModel model, String accid) {
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("HHss");
        String tjsj = s.format(date);
        if (DateUtils.rztjsjqr(tjsj, "1700")) {
            List<XmcyModel> list = xmcyMapper.findYgidByXmid(model.getXmid());
            if (list.size() != 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (accid.equals(list.get(i).getYgid())) {
                        model.setTjsj(new Date());
                        model.setTjr(accid);
                        gzrzMapper.addGzrz(model);
                        return new ResponseResult<>(true, "提交成功");
                    }
                    return new ResponseResult<>(false, "非项目成员不可提交该项目的工作日志");
                }
            }
        }
        return new ResponseResult<>(false, "项目日志或非项目日志必须在17:00以后才能提交");
    }

    @Override
    public ResponseResult<PageInfo<GzrzModel>> findByXmid(int pageNow, int pageSize, GzrzModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<GzrzModel> list = gzrzMapper.findByXmid(model);
        PageInfo<GzrzModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<PageInfo<GzrzModel>> rgtj(int pageNow, int pageSize, GzrzModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<GzrzModel> list = gzrzMapper.rgtj(model);
        PageInfo<GzrzModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "统计成功", page);
        }
        return new ResponseResult<>(false, "查无信息");
    }

}
