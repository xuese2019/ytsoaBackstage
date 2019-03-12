package com.yts.ytsoa.business.gzzl.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gzzl.mapper.GzzlMapper;
import com.yts.ytsoa.business.gzzl.model.GzzlModel;
import com.yts.ytsoa.business.gzzl.service.GzzlService;
import com.yts.ytsoa.business.xmcy.mapper.XmcyMapper;
import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.business.xmwp.mapper.XmwpMapper;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.business.xxgl.model.XxglModel;
import com.yts.ytsoa.business.xxgl.service.XxglService;
import com.yts.ytsoa.business.xxgl.utils.XxglUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.websocket.WebSocketController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class GzzlServiceImpl implements GzzlService {

    private static final String FGF = ",";
    private static final String ALL_PEOPLE = "all";
    @Autowired
    private GzzlMapper gzzlMapper;
    @Autowired
    private XmcyMapper xmcyMapper;

    @Autowired
    private XmwpMapper xmwpMapper;

    @Autowired
    private XxglService xxglService;

    @Override
    public ResponseResult<PageInfo<GzzlModel>> findAll(int pageNow, int pageSize, GzzlModel gzzlModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<GzzlModel> list = gzzlMapper.findAll(gzzlModel);
        PageInfo<GzzlModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查询失败", null);
        }
    }

    @Override
    public ResponseResult<GzzlModel> add(GzzlModel gzzlModel, String accid) throws Exception {
        List<GzzlModel> gzzlModels = new ArrayList<>();
        String fsr = gzzlModel.getFsr();
        String xmid = gzzlModel.getXmid();
        XmwpModel xmwpModel = xmwpMapper.findXmwpByUuid(gzzlModel.getXmid());
        if (accid.equals(xmwpModel.getXmfzr())) {
            if (xmwpModel.getYwzt() < 3) {
                if (gzzlModel.getJsr() != null) {
                    if (ALL_PEOPLE.equals(gzzlModel.getJsr())) {
                        gzzlModel.setZt(2);
                        List<XmcyModel> list = xmcyMapper.findxmid(gzzlModel.getXmid());
                        for (int i = 0; i < list.size(); i++) {
                            GzzlModel gzzlModelss = new GzzlModel();
                            gzzlModelss.setFsnr(gzzlModel.getFsnr());
                            gzzlModelss.setFssj(new Timestamp(System.currentTimeMillis()));
                            gzzlModelss.setZt(1);
                            gzzlModelss.setFsr(fsr);
                            gzzlModelss.setXmid(xmid);
                            gzzlModelss.setJsr(list.get(i).getYgid());
                            gzzlModels.add(gzzlModelss);
                            XxglModel all = new XxglUtils().setXx(5, "工作指令：", gzzlModelss.getFsnr(), gzzlModelss.getFsr(), gzzlModelss.getJsr());
                            xxglService.save(all);
                        }
                    } else {
                        String[] split = gzzlModel.getJsr().split(FGF);
                        for (int i = 0; i < split.length; i++) {
                            if (split[i] != null && !split[i].isEmpty()) {
                                XmcyModel byId = xmcyMapper.findById(split[i]);
                                GzzlModel gzzlModelss = new GzzlModel();
                                gzzlModelss.setFsnr(gzzlModel.getFsnr());
                                gzzlModelss.setFssj(new Timestamp(System.currentTimeMillis()));
                                gzzlModelss.setZt(1);
                                gzzlModelss.setFsr(fsr);
                                gzzlModelss.setXmid(xmid);
                                gzzlModelss.setJsr(byId.getYgid());
                                gzzlModels.add(gzzlModelss);
                                xxglService.save(new XxglUtils().setXx(5, "工作指令:", gzzlModelss.getFsnr(), gzzlModelss.getFsr(), gzzlModelss.getJsr()));
                            }
                        }
                    }
                    if (gzzlModels.size() > 0) {
                        gzzlMapper.save(gzzlModels);
//                刷新所有人的通知
                        WebSocketController.sendInfo("");
                        return new ResponseResult<>(true, "工作指令保存成功");
                    } else {
                        return new ResponseResult<>(false, "工作指令保存失败");
                    }
                } else {
                    return new ResponseResult<>(false, "缺失接收人");
                }
            }
            return new ResponseResult<>(false, "项目审核通过，无法发送工作指令");
        }
        return new ResponseResult<>(false, "非项目负责人没有权限发送工作指令");
    }
}

