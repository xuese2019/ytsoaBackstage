package com.yts.ytsoa.business.xmwp.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.Xmshlc.mapper.XmshlcMapper;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.shrsz.mapper.ShrszMapper;
import com.yts.ytsoa.business.shrsz.model.ShrszModel;
import com.yts.ytsoa.business.xmwp.mapper.XmwpMapper;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.business.xmwp.service.XmwpService;
import com.yts.ytsoa.business.xxgl.model.XxglModel;
import com.yts.ytsoa.business.xxgl.service.XxglService;
import com.yts.ytsoa.business.xxgl.utils.XxglUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.poi.ExcelModelExportUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private XmshlcMapper xmshlcMapper;
    @Autowired
    private ShrszMapper shrszMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmwpModel> addXmwp(XmwpModel model) throws Exception {
        model.setWpdscsj(new Timestamp(System.currentTimeMillis()));
        model.setYwzt(1);
        xmwpMapper.addXmwp(model);
//        给承接人发送通知
        XxglModel all = new XxglUtils().setXx(2, "需要承接项目：" + model.getXmmc(), null, model.getWpr(), "all");
        xxglService.save(all);
        return new ResponseResult<>(true, "添加成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmwpModel> delById(String uuid) throws Exception {
        int xmByUuid = xmwpMapper.findXmByUuid(uuid);
        if (xmByUuid < 2) {
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

    @Override
    public ResponseResult<XmwpModel> findById(String uuid) {
        XmwpModel result = xmwpMapper.findById(uuid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<PageInfo<XmwpModel>> findByXmmc(int pageNow, int pageSize, XmwpModel model, String accId) throws Exception {
        ShrszModel shrszModel = shrszMapper.findAllShr();
        if (shrszModel != null) {
            if (accId.equals(shrszModel.getBmjlid())) {
                model.setYwzt(3);
                PageHelper.startPage(pageNow, pageSize);
                List<XmwpModel> list1 = xmwpMapper.findByXmmc(model);
                PageInfo<XmwpModel> page1 = new PageInfo<>(list1);
                if (page1.getSize() > 0) {
                    return new ResponseResult<>(true, "查询成功", page1);
                }
                return new ResponseResult<>(false, "查无信息");
            } else if (accId.equals(shrszModel.getZgbid())) {
                model.setYwzt(4);
                PageHelper.startPage(pageNow, pageSize);
                List<XmwpModel> list1 = xmwpMapper.findByXmmc(model);
                PageInfo<XmwpModel> page1 = new PageInfo<>(list1);
                if (page1.getSize() > 0) {
                    return new ResponseResult<>(true, "查询成功", page1);
                }
                return new ResponseResult<>(false, "查无信息");
            } else if (accId.equals(shrszModel.getHhrid())) {
                model.setYwzt(5);
                PageHelper.startPage(pageNow, pageSize);
                List<XmwpModel> list1 = xmwpMapper.findByXmmc(model);
                PageInfo<XmwpModel> page1 = new PageInfo<>(list1);
                if (page1.getSize() > 0) {
                    return new ResponseResult<>(true, "查询成功", page1);
                }
                return new ResponseResult<>(false, "查无信息");
            } else {
                PageHelper.startPage(pageNow, pageSize);
                model.setXmfzr(accId);
                List<XmwpModel> list1 = xmwpMapper.findByXmmc(model);
                PageInfo<XmwpModel> page1 = new PageInfo<>(list1);
                if (page1.getSize() > 0) {
                    return new ResponseResult<>(true, "查询成功", page1);
                }
                return new ResponseResult<>(false, "查无信息");
            }
        }
        return new ResponseResult<>(false, "查无信息");
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmshModel> xmsh(XmshModel model, String accid) throws Exception {
        String xmfzr = xmwpMapper.findXmfzr(model.getPrentid());
        ShrszModel shrszModel = shrszMapper.findAllShr();
        if (accid.equals(xmfzr)) {
            model.setShsj(new Date());
            model.setShr(accid);
            int result = xmshMapper.add(model);
            if (result != 0) {
                XmwpModel xmwpModel = new XmwpModel();
                xmwpModel.setUuid(model.getPrentid());
                xmwpModel.setYwzt(3);
                xmwpMapper.updateYwzt(xmwpModel);
                return new ResponseResult<>(true, "审核成功");
            }
        } else if (shrszModel != null) {
            if (accid.equals(shrszModel.getBmjlid())) {
                XmwpModel xmwpModel = xmwpMapper.findById(model.getPrentid());
                if (xmwpModel != null) {
                    if (xmwpModel.getYwzt() == 3) {
                        model.setShr(accid);
                        model.setShsj(new Date());
                        int result = xmshMapper.add(model);
                        if (result != 0) {
                            if (model.getShjg() == 2) {
                                XmwpModel xmwpModel1 = new XmwpModel();
                                xmwpModel1.setUuid(model.getPrentid());
                                xmwpModel1.setYwzt(4);
                                xmwpMapper.updateYwzt(xmwpModel1);
                            }
                            return new ResponseResult<>(true, "审核成功");
                        }
                    }
                    return new ResponseResult<>(false, "项目经理未审，部门经理不可审核");
                }
            } else if (accid.equals(shrszModel.getZgbid())) {
                XmwpModel xmwpModel = xmwpMapper.findById(model.getPrentid());
                if (xmwpModel != null) {
                    if (xmwpModel.getYwzt() == 4) {
                        model.setShr(accid);
                        model.setShsj(new Date());
                        int result = xmshMapper.add(model);
                        if (result != 0) {
                            Integer hhrsh = xmwpMapper.hhrsh(model.getPrentid());
                            if (hhrsh != null) {
                                if (hhrsh == 0) {
                                    if (model.getShjg() == 2) {
                                        XmwpModel xmwpModel1 = new XmwpModel();
                                        xmwpModel1.setUuid(model.getPrentid());
                                        xmwpModel1.setYwzt(5);
                                        xmwpMapper.updateYwzt(xmwpModel1);
                                    }
                                } else {
                                    if (model.getShjg() == 2) {
                                        XmwpModel xmwpModel1 = new XmwpModel();
                                        xmwpModel1.setUuid(model.getPrentid());
                                        xmwpModel1.setYwzt(6);
                                        xmwpMapper.updateYwzt(xmwpModel1);
                                    }
                                }
                            }
                            return new ResponseResult<>(true, "审核成功");
                        }
                    }
                    return new ResponseResult<>(false, "部门经理未审，质控部不可审");
                }
            } else if (accid.equals(shrszModel.getHhrid())) {
                XmwpModel xmwpModel = xmwpMapper.findById(model.getPrentid());
                if (xmwpModel != null) {
                    if (xmwpModel.getYwzt() == 5) {
                        model.setShr(accid);
                        model.setShsj(new Date());
                        int result = xmshMapper.add(model);
                        if (result != 0) {
                            XmwpModel xmwpModel1 = new XmwpModel();
                            xmwpModel1.setUuid(model.getPrentid());
                            xmwpModel1.setYwzt(6);
                            xmwpMapper.updateYwzt(xmwpModel1);
                            return new ResponseResult<>(true, "审核成功");
                        }
                    }
                    return new ResponseResult<>(false, "质控部未审，项目合伙人不可审核");
                }
            }
        }
        return new ResponseResult<>(false, "权限不足，审核失败");
    }

    @Override
    public ResponseResult<PageInfo<XmwpModel>> findByXmyq(int pageNow, int pageSize, XmwpModel model, String accId) throws Exception {
      /*  long s = model.getXmxcjssj().getTime();
        long dd = System.currentTimeMillis();*/
        /* if (s == dd) {*/
        PageHelper.startPage(pageNow, pageSize);
        List<XmwpModel> list = xmwpMapper.findByXmyq(model);
        PageInfo<XmwpModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
       /*     }
            return new ResponseResult<>(true, "项目需要延期");*/
        }
        return new ResponseResult<>(false, "查询失败");
    }

    @Override
    public ResponseResult<PageInfo<XmwpModel>> find(int pageNow, int pageSize, XmwpModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<XmwpModel> list1 = xmwpMapper.findByXmmc(model);
        PageInfo<XmwpModel> page1 = new PageInfo<>(list1);
        if (page1.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page1);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    /**
     * 报告申请页面
     * 项目名称搜索只能查询自己承接的项目
     * 并且必须得是审核通过的项目才能申请报告
     *
     * @param model
     * @return
     */
    @Override
    public ResponseResult<List<XmwpModel>> findByXmfzr(XmwpModel model, String accid) throws Exception {
        if (model != null) {
            model.setXmfzr(accid);
            List<XmwpModel> list = xmwpMapper.findByXmfzr(model);
            if (list.size() != 0) {
                return new ResponseResult<>(true, "查询成功", list);
            }
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<PageInfo<XmwpModel>> xmgl(int pageNow, int pageSize, XmwpModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<XmwpModel> list = xmwpMapper.xmgl(model);
        PageInfo<XmwpModel> page1 = new PageInfo<>(list);
        if (page1.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page1);
        }
        return new ResponseResult<>(false, "查无信息");
    }
}
