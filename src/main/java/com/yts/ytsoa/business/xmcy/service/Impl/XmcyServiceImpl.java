package com.yts.ytsoa.business.xmcy.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmcy.mapper.XmcyMapper;
import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.business.xmcy.service.XmcyService;
import com.yts.ytsoa.business.xmwp.mapper.XmwpMapper;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
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

    @Autowired
    private XmwpMapper xmwpMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmcyModel> addXmcy(List<XmcyModel> models) throws Exception {
        if (models != null && models.size() > 0) {
            xmcyMapper.saves(models);
        }
        return new ResponseResult<>(false, "成功");
    }

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
    public ResponseResult<XmcyModel> update(XmcyModel model, String accid) throws Exception {
        /*      XmcyModel byId = xmcyMapper.findById(model.getUuid());*/
        /*
        根据uuid查出项目负责人，如果是项目负责人的话，那么判断ywzt，如果业务状态==3 那么项目负责人已经复审完毕就不
        允许继续修改
         */
        XmcyModel byId = xmcyMapper.findById(model.getUuid());
        XmwpModel xmwpModel = xmwpMapper.findByUuid(byId.getXmid());
        if (xmwpModel != null && xmwpModel.getXmfzr().equals(accid)) {
            if (xmwpModel.getYwzt() < 3) {
                if (model != null) {
                    int result = xmcyMapper.update(model);
                    if (result > 0) {
                        return new ResponseResult<>(true, "修改成功");
                    }
                }
                return new ResponseResult<>(false, "修改失败");
            }
            return new ResponseResult<>(false, "项目审核过后无法修改成员评语");
        }
        return new ResponseResult<>(false, "非项目负责人无法修改成员评语");
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
    public ResponseResult<XmcyModel> insertXmcy(XmcyModel model, String accid) throws Exception {
        XmwpModel xmwpModel = xmwpMapper.findByUuid(model.getXmid());
        if (xmwpModel != null && xmwpModel.getXmfzr().equals(accid)) {
            if (xmwpModel.getYwzt() < 3) {
                List<XmcyModel> list = xmcyMapper.findxmid(model.getXmid());
                for (int i = 0; i < list.size(); i++) {
                    if (model.getYgid().equals(list.get(i).getYgid())) {
                        return new ResponseResult<>(false, "该员工已经在项目中，无法重复添加");
                    }
                }
                int result = xmcyMapper.insertXmcy(model);
                if (result > 0) {
                    return new ResponseResult<>(true, "添加成功");
                }
                return new ResponseResult<>(false, "添加失败");
            }
            return new ResponseResult<>(false, "项目审核过后无法添加成员");
        }
        return new ResponseResult<>(false, "非项目负责人不能添加该项目的成员");
    }

    @Override
    public ResponseResult<List<XmcyModel>> findYgid(String xmid) throws Exception {
        List<XmcyModel> list = xmcyMapper.findYgid(xmid);
        if (list != null) {
            return new ResponseResult<>(true, "查询成功", list);
        }
        return new ResponseResult<>(false, "查询失败");
    }

    @Override
    public ResponseResult<List<XmcyModel>> findxmid(String xmid) throws Exception {
        List<XmcyModel> list = xmcyMapper.findxmid(xmid);
        if (list != null) {
            return new ResponseResult<>(true, "查询成功", list);
        }
        return new ResponseResult<>(false, "查询失败");
    }
}
