package com.yts.ytsoa.business.rgtj.service.Impl;

import com.yts.ytsoa.business.gzrz.mapper.GzrzMapper;
import com.yts.ytsoa.business.gzrz.model.GzrzModel;
import com.yts.ytsoa.business.rgtj.mapper.RgtjMapper;
import com.yts.ytsoa.business.rgtj.model.RgtjModel;
import com.yts.ytsoa.business.rgtj.service.RgtjService;
import com.yts.ytsoa.utils.GetUuid;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RgtjServiceImpl implements RgtjService {
    @Autowired
    private RgtjMapper rgtjMapper;
    @Autowired
    private GzrzMapper gzrzMapper;

    @Override
    public ResponseResult<List<RgtjModel>> find(RgtjModel model) throws Exception {
        List<RgtjModel> list = rgtjMapper.find(model);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "查询成功");
        }
        return new ResponseResult<>(false, "查询失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<RgtjModel> insertRgtj() throws Exception {
        GzrzModel model = new GzrzModel();
        List<GzrzModel> list = gzrzMapper.rgtj(model);
        if (list.size() > 0 && list != null) {
            for (int i = 0; i < list.size(); i++) {
                RgtjModel rgtjModel = new RgtjModel();
                String uuid = GetUuid.getUUID();
                rgtjModel.setUuid(uuid);
                if (list.get(i).getXmid() != null) {
                    rgtjModel.setXmid(list.get(i).getXmid());
                }
                rgtjModel.setXmid(null);
                rgtjModel.setCcts(list.get(i).getCcts());
                rgtjModel.setName(list.get(i).getTjr());
                rgtjModel.setSjgss(list.get(i).getXmts());
                rgtjModel.setXmts(list.get(i).getTrgxmsj());
                rgtjMapper.insertRgtj(rgtjModel);
            }
            return new ResponseResult<>(true, "统计成功");
        }
        return new ResponseResult<>(false, "统计失败");
    }
}
