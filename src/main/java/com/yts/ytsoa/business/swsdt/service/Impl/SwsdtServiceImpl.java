package com.yts.ytsoa.business.swsdt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.swsdt.mapper.SwsdtMapper;
import com.yts.ytsoa.business.swsdt.model.SwsdtModel;
import com.yts.ytsoa.business.swsdt.service.SwsdtService;
import com.yts.ytsoa.business.swsdttp.mapper.SwsdttpMapper;
import com.yts.ytsoa.business.swsdttp.model.SwsdttpModel;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SwsdtServiceImpl implements SwsdtService {
    @Autowired
    private SwsdtMapper swsdtMapper;

    @Autowired
    private SwsdttpMapper swsdttpMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<SwsdtModel> insertSwsdt(SwsdtModel model, String accid) throws Exception {
        String uuid = GetUuid.getUUID();
        model.setUuid(uuid);
        model.setScr(accid);
        model.setScsj(new Date());
        List<SwsdttpModel> list = new ArrayList<>();
        if (model.getTpmc() != null && !model.getTpmc().isEmpty() && model.getTpmc().length() != 0) {
            String[] tpmc = model.getTpmc().split(",");
            for (int i = 0; i < tpmc.length; i++) {
                SwsdttpModel swsdttpModel = new SwsdttpModel();
                swsdttpModel.setSwsdtid(model.getUuid());
                swsdttpModel.setTpmc(tpmc[i]);
                list.add(swsdttpModel);
            }
            swsdttpMapper.tpsc(list);
        }
        int result = swsdtMapper.insertSwsdt(model);
        if (result != 0) {
            return new ResponseResult<>(true, "事务所动态添加成功");
        }
        return new ResponseResult<>(false, "事务所动态添加失败");
    }


    @Override
    public ResponseResult<PageInfo<SwsdtModel>> find(int pageNow, int pageSize, SwsdtModel model) throws Exception {
        PageHelper.startPage(pageNow, 5);
        List<SwsdtModel> list = swsdtMapper.find(model);
        PageInfo<SwsdtModel> page = new PageInfo<>(list);
        if (page.getSize() != 0) {
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查无信息");
    }
}
