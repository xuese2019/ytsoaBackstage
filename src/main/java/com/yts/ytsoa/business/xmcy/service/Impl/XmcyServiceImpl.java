package com.yts.ytsoa.business.xmcy.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmcy.mapper.XmcyMapper;
import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.business.xmcy.result.resultModel;
import com.yts.ytsoa.business.xmcy.service.XmcyService;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class XmcyServiceImpl implements XmcyService {
    @Autowired
    private XmcyMapper xmcyMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmcyModel> addXmcy(List<XmcyModel> models) throws Exception {
        if (models != null && models.size() > 0) {
            xmcyMapper.saves(models);
        }
//        if (models.size() == 1) {
//            models.get(0).setUuid(uuid);
//            models.get(0).setXmid(models.get(0).getXmid());
//            models.get(0).setYgid(models.get(0).getXmid());
//            xmcyMapper.addXmcy(models.get(0));
//            return new ResponseResult<>(true, "添加成功");
//        } else {
//            for (int i = 0; i < models.size(); i++) {
//                for (int j = 0; j < models.size(); j++) {
//                    models.get(i).setUuid(models.get(j).getUuid());
//                    models.get(i).setYgid(String.valueOf(models.get(j).getYgid()));
//                    models.get(i).setXmid(String.valueOf(models.get(j).getXmid()));
//                    xmcyMapper.addXmcyPl(models);
//                    return new ResponseResult<>(true, "批量添加成功");
//                }
//            }
//        }
        return new ResponseResult<>(false, "成功");
    }

   /* @Override
    public ResponseResult<XmcyModel> addXmcy(XmcyModel model) throws Exception {
        List<XmcyModel> list = new ArrayList<>();
        String uuid = GetUuid.getUUID();
        String s[]=model.getName().split("},");
        for (int i = 0; i < s.length; i++) {
            if (s[i] != null && !s[i].isEmpty()) {
                XmcyModel xmcyModel = new XmcyModel();
                xmcyModel.setName(model.getName());
                xmcyModel.setXmid(model.getXmid());
                xmcyModel.setUuid(uuid);
                xmcyModel.setYgid(model.getYgid());
                xmcyModel.setGtnl(model.getGtnl());
                xmcyModel.setGzxl(model.getGzxl());
                xmcyModel.setZrxjtdjs(model.getZrxjtdjs());
                xmcyModel.setZyjsnl(model.getZyjsnl());
                xmcyModel.setPy(model.getPy());
                list.add(xmcyModel);
            }
            if (list.size() > 0) {
                xmcyMapper.addXmcyPl(list);
                return new ResponseResult<>(true, "添加成功");
            }
        }
        return new ResponseResult<>(false, "添加失败");
    }*/

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<XmcyModel>> find(int pageNow, int pageSize, XmcyModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<XmcyModel> list = xmcyMapper.find(model);
        PageInfo<XmcyModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmcyModel> update(XmcyModel model) throws Exception {
        if (model != null) {
            int result = xmcyMapper.update(model);
            if (result > 0) {
                return new ResponseResult<>(true, "修改成功");
            }
        }
        return new ResponseResult<>(false, "修改失败");
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<resultModel>> rgtj(int pageNow, int pageSize, resultModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<resultModel> models = xmcyMapper.rgtj(model);
        PageInfo<resultModel> page = new PageInfo<>(models);
        if (page.getSize() > 0) {
            xmcyMapper.insertRgglTable(models);
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查无信息");
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmcyModel> findById(String uuid) throws Exception {
        XmcyModel result = xmcyMapper.findById(uuid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmcyModel> insertXmcy(XmcyModel model) throws Exception {
        int result = xmcyMapper.insertXmcy(model);
        if (result > 0) {
            return new ResponseResult<>(true, "添加成功");
        }
        return new ResponseResult<>(false, "添加失败");

    }

    @Override
    public ResponseResult<List<XmcyModel>> findYgid(String xmid) throws Exception {
        List<XmcyModel> list = xmcyMapper.findYgid(xmid);
        if (list != null) {
            return new ResponseResult<>(true, "查询成功", list);
        }
        return new ResponseResult<>(false, "查询失败");
    }
}
