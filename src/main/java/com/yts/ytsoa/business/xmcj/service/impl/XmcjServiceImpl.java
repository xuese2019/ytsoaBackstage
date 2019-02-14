package com.yts.ytsoa.business.xmcj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmcj.mapper.XmcjMapper;
import com.yts.ytsoa.business.xmcj.model.XmcjModel;
import com.yts.ytsoa.business.xmcj.service.XmcjService;
import com.yts.ytsoa.business.xmcy.mapper.XmcyMapper;
import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.business.xmwp.mapper.XmwpMapper;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.business.xxgl.model.XxglModel;
import com.yts.ytsoa.business.xxgl.service.XxglService;
import com.yts.ytsoa.business.xxgl.utils.XxglUtils;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.poi.ExcelModelExportUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class XmcjServiceImpl implements XmcjService {
    @Autowired
    private XmcjMapper xmcjMapper;
    @Autowired
    private XxglService xxglService;
    @Autowired
    private XmcyMapper xmcyMapper;
    @Autowired
    private XmwpMapper xmwpMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<XmcjModel>> findAll(int pageNow, int pageSize, XmcjModel xmcjModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<XmcjModel> list = xmcjMapper.findAll(xmcjModel);
        PageInfo<XmcjModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "成功", page);
        } else {
            return new ResponseResult<>(false, "未查询到记录", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmcjModel> deleteById(String uuid) throws Exception {
        xmcjMapper.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<List<XmcjModel>> findById(String uuid) throws Exception {
        List<XmcjModel> list = xmcjMapper.findById(uuid);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "成功", list);
        } else {
            return new ResponseResult<>(false, "未查询到记录", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<String> excel(String basePath, String outPath, XmcjModel xmcjModel) throws Exception {
        List<XmcjModel> list = xmcjMapper.findAll(xmcjModel);
        List<String[]> datas = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String strs[] = new String[9];
                strs[0] = String.valueOf(i + 1);
                strs[1] = list.get(i).getUuid();
                strs[2] = list.get(i).getXmmc();
                strs[3] = list.get(i).getWtf();
                strs[4] = list.get(i).getWtflxr();
                strs[5] = list.get(i).getWtflxdh();
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                strs[6] = s.format(list.get(i).getWtsj());
                strs[7] = list.get(i).getBsjdw();
                strs[8] = list.get(i).getCjbm();
                datas.add(strs);
            }
            return new ExcelModelExportUtils().exportCurrency(basePath, outPath, datas);
        } else {
            return new ResponseResult<>(false, "未查询到记录");
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmcjModel> updateById(XmcjModel xmcjModel, String fsr) throws Exception {
        xmcjModel.setYwzt(2);
        List<XmcyModel> list = new ArrayList<>();
        String yglb = xmcjModel.getYglb();
        if (yglb != null) {
            String[] split = yglb.split(",");
            for (int i = 0; i < split.length; i++) {
                XmcyModel model = new XmcyModel();
                String uuid = GetUuid.getUUID();
                model.setUuid(uuid);
                model.setYgid(split[i]);
                model.setXmid(xmcjModel.getUuid());
                list.add(model);
            }
            xmcyMapper.saves(list);
        }
        xmcjMapper.updateById(xmcjModel);
        XmwpModel byId1 = xmwpMapper.findById(xmcjModel.getUuid());
        if (byId1 == null || byId1.getWpr() == null || byId1.getWpr().isEmpty()) {
            return new ResponseResult<>(false, "缺失委派人");
        }
        ResponseResult<XxglModel> save = xxglService.save(new XxglUtils().setXx(1, "已承接项目：" + byId1.getXmmc(), null, fsr, byId1.getWpr()));
        if (save.isSuccess()) {
            return new ResponseResult<>(true, "成功");
        } else {
            return new ResponseResult<>(save.isSuccess(), save.getMessage());
        }
    }
}
