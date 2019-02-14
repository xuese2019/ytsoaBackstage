package com.yts.ytsoa.business.bggl.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bgflbh.mapper.BgflbhMapper;
import com.yts.ytsoa.business.bggl.mapper.BgglMapper;
import com.yts.ytsoa.business.bggl.model.Bggl2Model;
import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.business.bggl.service.BgglService;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.xmwp.mapper.XmwpMapper;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.business.xxgl.service.XxglService;
import com.yts.ytsoa.business.xxgl.utils.XxglUtils;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.poi.ExcelModelExportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BgglServiceImpl implements BgglService {
    @Autowired
    private BgglMapper bgglMapper;
    @Autowired
    private BgflbhMapper bgflbhMapper;

    @Autowired
    private XmshMapper xmshMapper;
    @Autowired
    private XxglService xxglService;

    @Autowired
    private XmwpMapper xmwpMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<BgglModel> addBggl(BgglModel model) throws Exception {
        XmwpModel xmwpModel = xmwpMapper.findById(model.getXmid());
        int ywzt = xmwpModel.getYwzt();
        if (ywzt <= 6) {
            return new ResponseResult<>(false, "未审核完成的项目不能出具报告");
        }
        List<BgglModel> list = new ArrayList<>();
        String[][] bgs = model.getBgs();
        if (bgs != null && bgs.length > 1) {
            for (int i = 0; i < bgs.length; i++) {
                for (int j = 0; j < bgs[j].length; j++) {
                    String s = bgs[i][j];
                    String[] s1 = s.split(",");
                    for (int k = 0; k < s1.length; k++) {
                        BgglModel model1 = new BgglModel();
                        String uuid = GetUuid.getUUID();
                        model1.setUuid(uuid);
                        model1.setXmmc(s1[k]);
                        model1.setBgzbr(s1[k]);
                        model1.setBgmc(s1[k]);
                        model1.setSqf(s1[k]);
                        model1.setBgrq(new Date());
                        model1.setBglx(Integer.parseInt(s1[k]));
                        model1.setBgbh(s1[k]);
                        model1.setBgfwbhQz(s1[k]);
                        model1.setBgfwbhHz(Integer.parseInt(s1[k]));
                        model1.setBgcs(Integer.parseInt(s1[k]));
                        model1.setZcze(new BigDecimal(s1[k]));
                        model1.setFzze(new BigDecimal(s1[k]));
                        model1.setSrlr(new BigDecimal(s1[k]));
                        model1.setJlr(new BigDecimal(s1[k]));
                        model1.setXgcs(Integer.parseInt(s1[k]));
                        model1.setXgyy(s1[k]);
                        model1.setQzzs1(s1[k]);
                        model1.setQzzs2(s1[k]);
                        list.add(model1);
                        bgglMapper.addBgglPl(list);
                        return new ResponseResult<>(true, "批量申请报告成功");
                    }
                }
            }
        } else {
            if (model != null) {
                int result = bgglMapper.addBggl(model);
                if (result != 0) {
                    return new ResponseResult<>(true, "申请报告成功");
                }
            }
        }
        return new ResponseResult<>(false, "申请报告失败");
    }

    @Override
    public ResponseResult<PageInfo<BgglModel>> find(int pageNow, int pageSize, BgglModel model, String fsr) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<BgglModel> list = bgglMapper.find(model);
        PageInfo<BgglModel> pageInfo = new PageInfo<>(list);
        model.setGdyxq(model.getGdyxq() - 1);
        model.setShjg(2);
        XmwpModel xmwpModel = new XmwpModel();
        bgglMapper.update(model);
//        给项目负责人人发送通知
        if (model.getGdyxq() < 1) {
            xxglService.save(new XxglUtils().setXx(7, "归档有效期不足1天：" + model.getXmmc(), model.getBgbh(), fsr, xmwpModel.getXmfzr()));

            return new ResponseResult<>(true, "查询成功", pageInfo);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<BgglModel> delete(BgglModel model) {
        if (model != null) {
            int result = bgglMapper.delete(model);
            if (result != 0) {
                return new ResponseResult<>(true, "删除成功");
            }
        }
        return new ResponseResult<>(false, "删除失败");
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<String> excel(String basePath, String outPath, BgglModel model) throws Exception {
        List<BgglModel> list = bgglMapper.find(model);
        List<String[]> datas = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String s[] = new String[11];
                s[0] = String.valueOf(i + 1);
                s[1] = list.get(0).getBgbh();
                s[2] = list.get(0).getXmmc();
                s[3] = list.get(0).getXmlx();
                s[4] = list.get(0).getBgzbr();
                s[5] = list.get(0).getSqf();
                s[6] = String.valueOf(list.get(0).getBglx());
                s[7] = String.valueOf(list.get(0).getBgcs());
                s[8] = String.valueOf(list.get(0).getBgrq());
                s[9] = String.valueOf(list.get(0).getShjg());
                s[10] = String.valueOf(list.get(0).getGdyxq());
                datas.add(s);
            }
            return new ExcelModelExportUtils().exportCurrency(basePath, outPath, datas);
        }
        return new ResponseResult<>(false, "导出失败");
    }


    @Override
    public ResponseResult<Bggl2Model> findById(String uuid) throws Exception {
        if (uuid != null && !uuid.isEmpty()) {
            List<Bggl2Model> model = bgglMapper.findById(uuid);
            if (model != null) {
                return new ResponseResult<>(true, "查询成功");
            }
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<XmshModel> update(XmshModel model) throws Exception {
        String uuid = GetUuid.getUUID();
        model.setUuid(uuid);
        model.setShsj(new Date());
        int result = xmshMapper.add(model);
        if (result != 0) {
            BgglModel bgglModel = new BgglModel();
            bgglModel.setUuid(model.getPrentid());
            bgglModel.setShjg(model.getShjg());
            bgglModel.setWczt(3);
            bgglMapper.update(bgglModel);
            return new ResponseResult<>(true, "审核成功");
        }
        return new ResponseResult<>(false, "审核失败");
    }
}
