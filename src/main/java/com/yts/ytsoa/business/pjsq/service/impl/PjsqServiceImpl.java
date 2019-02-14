package com.yts.ytsoa.business.pjsq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.pjsq.mapper.PjsqMapper;
import com.yts.ytsoa.business.pjsq.model.PjsqModel;
import com.yts.ytsoa.business.pjsq.service.PjsqService;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
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
public class PjsqServiceImpl implements PjsqService {
    @Autowired
    private PjsqMapper pjsqMapper;

    @Autowired
    private XmshMapper xmshMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PjsqModel> add(PjsqModel pjsqModel) throws Exception {
        pjsqModel.setShzt(1);
        int list = pjsqMapper.add(pjsqModel);
        if (list != 0) {
            return new ResponseResult<>(true, "添加成功", null);
        } else {
            return new ResponseResult<>(false, "添加失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<PjsqModel>> findAll(int pageNow, int pageSize, PjsqModel pjsqModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<PjsqModel> list = pjsqMapper.findAll(pjsqModel);
        PageInfo<PjsqModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<String> excel(String basePath, String outPath, PjsqModel pjsqModel) throws Exception {
        List<PjsqModel> list = pjsqMapper.findAll(pjsqModel);
        List<String[]> datas = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String strs[] = new String[11];
                strs[0] = String.valueOf(i + 1);
                strs[1] = list.get(i).getUuid();
                strs[2] = list.get(i).getKpbh();
                strs[3] = list.get(i).getXmmc();
                strs[4] = list.get(i).getXmfzr();
                strs[5] = list.get(i).getBm();
                strs[6] = list.get(i).getSqf();
                strs[7] = list.get(i).getFpsqr();
                strs[8] = String.valueOf(list.get(i).getKpje());
                strs[9] = list.get(i).getFplx();
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                strs[10] = s.format(list.get(i).getKprq());
                datas.add(strs);
            }
            return new ExcelModelExportUtils().exportCurrency(basePath, outPath, datas);
        } else {
            return new ResponseResult<>(false, "未查询到记录");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<PjsqModel>> find(int pageNow, int pageSize, PjsqModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<PjsqModel> list = pjsqMapper.find(model);
        PageInfo<PjsqModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmshModel> shjl(XmshModel model) throws Exception {
        int result = xmshMapper.add(model);
        if (result != 0) {
            if (model.getShjg() > 2) {
                PjsqModel model1 = new PjsqModel();
                model1.setUuid(model.getUuid());
                model1.setShzt(model.getShjg());
                pjsqMapper.update(model1);
            }
            return new ResponseResult<>(true, "审核成功");
        }
        return new ResponseResult<>(false, "审核失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PjsqModel> findById(String uuid) throws Exception {
        if (uuid != null && !uuid.isEmpty()) {
            PjsqModel result = pjsqMapper.findById(uuid);
            if (result != null) {
                return new ResponseResult<>(true, "查询成功", result);
            }
        }
        return new ResponseResult<>(false, "查无信息");
    }

}
