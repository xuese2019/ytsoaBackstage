package com.yts.ytsoa.business.xmwp.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.xmwp.mapper.XmwpMapper;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.business.xmwp.service.XmwpService;
import com.yts.ytsoa.business.xxgl.service.XxglService;
import com.yts.ytsoa.business.xxgl.utils.XxglUtils;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.poi.ExcelModelExportUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class XmwpServiceImpl implements XmwpService {
    @Autowired
    private XmwpMapper xmwpMapper;
    @Autowired
    private XxglService xxglService;
    @Autowired
    private XmshMapper xmshMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmwpModel> addXmwp(XmwpModel model, String fsr) throws Exception {
        model.setWpdscsj(new Timestamp(System.currentTimeMillis()));
        model.setYwzt(1);
        xmwpMapper.addXmwp(model);
//        给承接人发送通知
        xxglService.save(new XxglUtils().setXx(2, "需要承接项目：" + model.getXmmc(), null, fsr, model.getXmfzr()));
        return new ResponseResult<>(true, "添加成功");
    }

    //    @Override
//    public ResponseResult<List<XmwpModel>> find(XmwpModel model) throws Exception {
//        List<XmwpModel> result = xmwpMapper.find(model);
//        if (result.size() > 0) {
//            return new ResponseResult<>(true, "查询成功", result);
//        }
//        return new ResponseResult<>(false, "查询失败", null);
//    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmwpModel> delById(String uuid) throws Exception {
        XmwpModel model = xmwpMapper.findById(uuid);
        if (model != null && model.getYwzt() < 2) {
            xmwpMapper.delById(uuid);
            return new ResponseResult<>(true, "删除成功");
        }
        return new ResponseResult<>(false, "项目已开始进行，不允许删除");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmwpModel> updById(XmwpModel model) {
        int result = xmwpMapper.updById(model);
        if (result > 0) {
            return new ResponseResult<>(true, "修改成功");
        }
        return new ResponseResult<>(false, "修改失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmwpModel> findById(String uuid) {
        XmwpModel result = xmwpMapper.findById(uuid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<XmwpModel>> findByXmmc(int pageNow, int pageSize, XmwpModel model, String accId) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<XmwpModel> list = xmwpMapper.findByXmmc(model);
        PageInfo<XmwpModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查无数据");
    }

    @Override
    public ResponseResult<String> excel(String basePath, String outPath, XmwpModel model) throws Exception {
        List<XmwpModel> list = xmwpMapper.findByXmmc(model);
        List<String[]> datas = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String str[] = new String[11];
                str[0] = String.valueOf(i + 1);
                str[1] = list.get(i).getXmmc();
                str[2] = list.get(i).getWtf();
                str[3] = list.get(i).getWtflxr();
                str[4] = list.get(i).getWtflxdh();
                str[5] = String.valueOf(list.get(i).getWtsj());
                str[6] = list.get(i).getBsjdw();
                str[7] = String.valueOf(list.get(i).getWpr());
                str[8] = String.valueOf(list.get(i).getCjbm());
                str[9] = list.get(i).getXmfzr();
                str[10] = String.valueOf(list.get(i).getYwzt());
                datas.add(str);
            }
            return new ExcelModelExportUtils().exportCurrency(basePath, outPath, datas);
        }
        return new ResponseResult<>(false, "查无失败");
    }

    @Override
    public ResponseResult<String> findXmfzr(String uuid) throws Exception {
        String xmfzr = xmwpMapper.findXmfzr(uuid);
        if (xmfzr != null) {
            return new ResponseResult<>(true, "查询成功", xmfzr);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<XmwpModel> xmsh(XmwpModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        XmwpModel xmwpModel = xmwpMapper.findYwzt(model.getUuid());
        model.setYwzt(xmwpModel.getYwzt() + 1);
        int result = xmwpMapper.xmjlsh(model);
        if (result > 0) {
            XmshModel xmshModel = new XmshModel();
            String uuid = GetUuid.getUUID();
            xmshModel.setShr(accid);
            xmshModel.setUuid(uuid);
            xmshModel.setShsj(new Date());
            xmshModel.setShjg(model.getYwzt());
            xmshModel.setPrentid(model.getUuid());
            xmshMapper.add(xmshModel);
            return new ResponseResult<>(true, "项目经理审核成功");
        }
        return new ResponseResult<>(false, "项目经理审核失败");
    }
}
