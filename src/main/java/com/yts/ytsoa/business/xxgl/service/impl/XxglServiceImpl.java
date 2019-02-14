package com.yts.ytsoa.business.xxgl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.account.mapper.AccountMapper;
import com.yts.ytsoa.business.account.model.AccountModel;
import com.yts.ytsoa.business.xxgl.mapper.XxglMapper;
import com.yts.ytsoa.business.xxgl.model.XxglModel;
import com.yts.ytsoa.business.xxgl.service.XxglService;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.websocket.WebSocketController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class XxglServiceImpl implements XxglService {

    private static final String FGF = ",";
    private static final String ALL_PEOPLE = "all";

    @Autowired
    private XxglMapper mapper;
    @Autowired
    private AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XxglModel> save(XxglModel model) throws Exception {
        List<XxglModel> xxglModels = new ArrayList<>();
        String uuid = GetUuid.getUUID();
        String fsr = model.getFsr();
        if (model.getJsr() != null) {
            if (ALL_PEOPLE.equals(model.getJsr())) {
                model.setZt(2);
                AccountModel model1 = new AccountModel();
                List<AccountModel> list = accountMapper.findAll(model1);
                for (int i = 0; i < list.size(); i++) {
                    XxglModel entity = new XxglModel();
                    entity.setXxbt(model.getXxbt());
                    entity.setXxnr(model.getXxnr());
                    entity.setTzlj(model.getTzlj());
                    entity.setXxfssj(new Timestamp(System.currentTimeMillis()));
                    entity.setZt(1);
                    entity.setFsr(fsr);
                    entity.setJsr(list.get(i).getUuid());
                    entity.setTytFlag(uuid);
                    entity.setXxlx(6);
                    xxglModels.add(entity);
                }
            } else {
                String[] split = model.getJsr().split(FGF);
                for (int i = 0; i < split.length; i++) {
                    if (split[i] != null && !split[i].isEmpty()) {
                        XxglModel entity = new XxglModel();
                        entity.setXxbt(model.getXxbt());
                        entity.setXxnr(model.getXxnr());
                        entity.setTzlj(model.getTzlj());
                        entity.setXxfssj(new Timestamp(System.currentTimeMillis()));
                        entity.setZt(1);
                        entity.setFsr(fsr);
                        entity.setJsr(split[i]);
                        entity.setTytFlag(uuid);
                        entity.setXxlx(model.getXxlx() == 0 ? 6 : model.getXxlx());
                        xxglModels.add(entity);
                    }
                }
            }
            if (xxglModels.size() > 0) {
                mapper.saves(xxglModels);
//                刷新所有人的通知
                WebSocketController.sendInfo("");
                return new ResponseResult<>(true, "成功");
            } else {
                return new ResponseResult<>(false, "消息保存失败");
            }
        } else {
            return new ResponseResult<>(false, "缺失接收人");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XxglModel> updateZtByUuid(String uuid, int zt) throws Exception {
        mapper.updateZtByUuid(uuid, zt);
        //                刷新所有人的通知
        WebSocketController.sendInfo("");
        return new ResponseResult<>(true, "成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<XxglModel>> findAll(int pageNow, int pageSize, XxglModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize, "xxfssj desc");
        List<XxglModel> list = mapper.findAll(model);
        PageInfo<XxglModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "成功", page);
        }
        return new ResponseResult<>(false, "未查询到记录");
    }
}
