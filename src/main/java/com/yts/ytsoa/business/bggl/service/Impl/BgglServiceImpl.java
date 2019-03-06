package com.yts.ytsoa.business.bggl.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.bgflbh.mapper.BgflbhMapper;
import com.yts.ytsoa.business.bggl.mapper.BgglMapper;
import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.business.bggl.service.BgglService;
import com.yts.ytsoa.business.bgshjl.mapper.BgshjlMapper;
import com.yts.ytsoa.business.bgshr.mapper.BgshrMapper;
import com.yts.ytsoa.business.bgshr.model.BgshrModel;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.sz.mapper.SzMapper;
import com.yts.ytsoa.business.sz.model.SzModel;
import com.yts.ytsoa.business.xmcj.mapper.XmcjMapper;
import com.yts.ytsoa.business.xmcj.model.XmzmcModel;
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

    @Autowired
    private BgshjlMapper bgshjlMapper;
    @Autowired
    private BgshrMapper bgshrMapper;
    @Autowired
    private SzMapper szMapper;
    @Autowired
    private XmcjMapper xmcjMapper;

    /**
     * 用于报告审核，分页查询带条件
     *
     * @param pageNow
     * @param pageSize
     * @param model
     * @param fsr
     * @param accid
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult<PageInfo<BgglModel>> find(int pageNow, int pageSize, BgglModel model, String fsr, String accid) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<BgglModel> list = bgglMapper.find(model);
        PageInfo<BgglModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "成功", page);
        }
        return new ResponseResult<>(false, "未查询到数据");
    }

    @Override
    public ResponseResult<List<BgglModel>> findsh(int pageNow, BgglModel model, String fsr, String accid) throws Exception {
        List<BgglModel> list5 = new ArrayList<>();
//        第一种 项目经理
        PageHelper.startPage(pageNow, 5);
        List<BgglModel> one = bgglMapper.one(accid);
        if (one.size() > 0) {
            list5.addAll(one);
        }
//        第二种  部门经理
        AccountModel acc = bgglMapper.acc(accid);
        if (acc != null) {
            PageHelper.startPage(pageNow, 5);
            List<BgglModel> two = bgglMapper.two(acc.getBm());
            if (two.size() > 0) {
                list5.addAll(two);
            }
        }
//        第三种 质管部
        List<BgshrModel> list2 = bgshrMapper.find2();
        if (list2.size() > 0 && list2.get(0) != null) {
            if (list2.get(0).getZkbid() != null && !list2.get(0).getZkbid().isEmpty()) {
                PageHelper.startPage(pageNow, 5);
                List<BgglModel> three = bgglMapper.three(3);
                if (three.size() > 0) {
                    list5.addAll(three);
                }
            }
        }
//        第四种 合伙人
        List<SzModel> list = szMapper.findAll();
        if (list.size() > 0) {
            SzModel szModel = list.get(0);
            if (szModel.getAccid() != null && !szModel.getAccid().isEmpty() && szModel.getAccid().equals(accid)) {
                PageHelper.startPage(pageNow, 5);
                List<BgglModel> four = bgglMapper.three(4);
                if (four.size() > 0) {
                    list5.addAll(four);
                }
            }
        }
        if (list5.size() > 0) {
            return new ResponseResult<>(true, "成功", list5);
        }
        return new ResponseResult<>(false, "未查询到数据");
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
    public ResponseResult<BgglModel> findById(String uuid) throws Exception {
        if (uuid != null && !uuid.isEmpty()) {
            BgglModel model = bgglMapper.findById(uuid);
            if (model != null) {
                return new ResponseResult<>(true, "查询成功", model);
            }
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<BgglModel> findByXmZmc(String uuid) throws Exception {
        if (uuid != null && !uuid.isEmpty()) {
            BgglModel model = bgglMapper.findByXmZmc(uuid);
            if (model != null) {
                return new ResponseResult<>(true, "查询成功", model);
            }
        }
        return new ResponseResult<>(false, "报告未审核完成，不能申请归档");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<BgglModel> updateGdyxq(BgglModel model, String accid) throws Exception {
        List<BgglModel> list = bgglMapper.findAllBgs();
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                model.setGdyxq(list.get(i).getGdyxq() - 1);
                model.setUuid(list.get(i).getUuid());
                bgglMapper.updateGdyxq(model);
                XmwpModel xmwpModel = bgglMapper.findByXmid(list.get(i).getXmid());
                if (list.get(i).getGdyxq() < 1) {
                    xxglService.save(new XxglUtils().setXx(7, "归档有效期不足1天,报告名称为：" + list.get(i).getXmmc(), model.getBgbh(), accid, xmwpModel.getXmfzr()));
                }
            }
            return new ResponseResult<>(true, "归档有效期修改成功");
        }
        return new ResponseResult<>(true, "归档有效期修改失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmshModel> update(XmshModel model) throws Exception {
        String uuid = GetUuid.getUUID();
        if (model.getPrentid() == null || model.getPrentid().isEmpty()) {
            return new ResponseResult<>(false, "未传递报告id");
        }
        model.setUuid(uuid);
        model.setShsj(new Date());
        xmshMapper.add(model);
        BgglModel one = bgglMapper.findById(model.getPrentid());
        if (model.getShjg() == 2) {
            if (one != null) {
                switch (one.getShjg()) {
                    case 1:
                        bgglMapper.updateShjgByUuid(one.getUuid(), 2);
                        return new ResponseResult<>(true, "审核成功");
                    case 2:
                        bgglMapper.updateShjgByUuid(one.getUuid(), 3);
                        return new ResponseResult<>(true, "审核成功");
                    case 3:
                        XmwpModel xm = bgglMapper.getXm(one.getXmid());
                        if (xm == null) {
                            return new ResponseResult<>(false, "项目已不存在");
                        }
                        if (xm.getHhrsh() == 1) {
                            bgglMapper.updateShjgByUuid(model.getPrentid(), 4);
                        } else {
                            bgglMapper.updateShjgByUuid(model.getPrentid(), 6);
                            XmwpModel xmwpModel = bgglMapper.findXmYwztByBgXmid(model.getPrentid());
                            if (xmwpModel != null) {
                                List<BgglModel> list = bgglMapper.findBgsByUuid(xmwpModel.getUuid());
                                for (int i = 0; i < list.size(); i++) {
                                    if (list.get(i).getShjg() == 6) {
                                        XmwpModel xmwpModel1 = new XmwpModel();
                                        xmwpModel1.setUuid(xmwpModel.getUuid());
                                        xmwpModel1.setYwzt(7);
                                        xmwpMapper.updateYwzt(xmwpModel1);
                                    }
                                }
                            }
                        }
                        return new ResponseResult<>(true, "审核成功");
                    case 4:
                        bgglMapper.updateShjgByUuid(one.getUuid(), 6);
                        XmwpModel xmwpModel = bgglMapper.findXmYwztByBgXmid(model.getPrentid());
                        if (xmwpModel != null) {
                            List<BgglModel> list = bgglMapper.findBgsByUuid(xmwpModel.getUuid());
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getShjg() == 6) {
                                    XmwpModel xmwpModel1 = new XmwpModel();
                                    xmwpModel1.setUuid(xmwpModel.getUuid());
                                    xmwpModel1.setYwzt(7);
                                    xmwpMapper.updateYwzt(xmwpModel1);
                                }
                            }
                        }
                        return new ResponseResult<>(true, "审核成功");
                    default:
                        return new ResponseResult<>(true, "当前项目已审核完成");
                }
            }
            return new ResponseResult<>(false, "数据出现错误");
        } else {
            bgglMapper.updateShjgByUuid(one.getUuid(), 1);
            return new ResponseResult<>(true, "审核成功");
        }
    }

    @Override
    public ResponseResult<BgglModel> addBggl(BgglModel model) throws Exception {
        List<XmzmcModel> byXmzmcId = xmcjMapper.findByXmzmcId(model.getXmid());
        if (byXmzmcId != null) {
            int ywzt = xmwpMapper.findXmByUuid(byXmzmcId.get(0).getParentid());
            if (ywzt != 0) {
                if (ywzt != 6) {
                    return new ResponseResult<>(false, "未审核完成的项目不能出具报告");
                } else {
                    model.setShjg(1);
                    model.setGdyxq(60);
                    bgglMapper.addBggl(model);
                    return new ResponseResult<>(true, "报告出具成功");
                }
            }
        }
        return new ResponseResult<>(false, "报告出具失败");
    }

    /**
     * 用于报告管理，分页查询带条件
     *
     * @param pageNow
     * @param pageSize
     * @param model
     * @param fsr
     * @param accid
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult<PageInfo<BgglModel>> bggl(int pageNow, int pageSize, BgglModel model, String fsr, String accid) throws Exception {
        //查询报告审核人表
        BgshrModel bgshrModel = bgshrMapper.find();
        //根据报告的的xmid查出该项目的详细信息
        XmwpModel xmwpModel = bgglMapper.findByXmid(model.getXmid());
        if (xmwpModel != null) {
            if (xmwpModel.getXmfzr().equals(accid)) {
                model.setBgzbr(accid);
                PageHelper.startPage(pageNow, pageSize);
                List<BgglModel> list = bgglMapper.find(model);
                PageInfo<BgglModel> page = new PageInfo<>(list);
                model.setGdyxq(model.getGdyxq() - 1);
                bgglMapper.update(model);
                if (model.getGdyxq() < 1) {
                    xxglService.save(new XxglUtils().setXx(7, "归档有效期不足1天：" + model.getXmmc(), model.getBgbh(), fsr, xmwpModel.getXmfzr()));
                    return new ResponseResult<>(true, "查询成功", page);
                }
                return new ResponseResult<>(false, "查无信息");
            } else if (accid.equals(bgshrModel.getBmjlid())) {
                PageHelper.startPage(pageNow, pageSize);
                List<BgglModel> list = bgglMapper.find(model);
                PageInfo<BgglModel> page = new PageInfo<>(list);
                if (page.getSize() > 0) {
                    return new ResponseResult<>(true, "查询成功", page);
                }
                return new ResponseResult<>(false, "查无信息");
            } else if (accid.equals(bgshrModel.getZkbid())) {
                PageHelper.startPage(pageNow, pageSize);
                List<BgglModel> list = bgglMapper.find(model);
                PageInfo<BgglModel> page = new PageInfo<>(list);
                if (page.getSize() > 0) {
                    return new ResponseResult<>(true, "查询成功", page);
                }
                return new ResponseResult<>(false, "查无信息");
            } else if (accid.equals(bgshrModel.getHhrid())) {
                PageHelper.startPage(pageNow, pageSize);
                List<BgglModel> list = bgglMapper.find(model);
                PageInfo<BgglModel> page = new PageInfo<>(list);
                if (page.getSize() > 0) {
                    return new ResponseResult<>(true, "查询成功", page);
                }
                return new ResponseResult<>(false, "查无信息");
            }
        } else {
            PageHelper.startPage(pageNow, pageSize);
            List<BgglModel> list = bgglMapper.find(model);
            PageInfo<BgglModel> page = new PageInfo<>(list);
            if (page.getSize() > 0) {
                return new ResponseResult<>(true, "查询成功", page);
            }
            return new ResponseResult<>(false, "查无信息");
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<List<BgglModel>> findBgByXmid(int pageNow, int pageSize, BgglModel model, String xmid) throws Exception {
//        用xmid 查询所有的子项目
        List<BgglModel> bgglModels = bgglMapper.findBgByXmid(xmid);
        if (bgglModels.size() > 0) {
            return new ResponseResult<>(true, "查询成功", bgglModels);
        }
        return new ResponseResult<>(false, "查无信息");
    }
}
