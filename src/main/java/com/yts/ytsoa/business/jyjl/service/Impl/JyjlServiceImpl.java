package com.yts.ytsoa.business.jyjl.service.Impl;

import com.yts.ytsoa.business.gdgl.mapper.GdglMapper;
import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.jyjl.mapper.JyjlMapper;
import com.yts.ytsoa.business.jyjl.model.JyjlModel;
import com.yts.ytsoa.business.jyjl.service.JyjlService;
import com.yts.ytsoa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class JyjlServiceImpl implements JyjlService {
    @Autowired
    private JyjlMapper jyjlMapper;
    @Autowired
    private GdglMapper gdglMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<JyjlModel> isnert(JyjlModel model) throws Exception {
        model.setGhrq(new Date());
        int result = jyjlMapper.insert(model);
        if (result > 0) {
            GdglModel model1 = new GdglModel();
            model1.setGdsqbh_hz(model.getDgjybh());
            model1.setJyzt(1);
            gdglMapper.updatejyzt(model1);
            return new ResponseResult<>(true, "归还成功");
        }
        return new ResponseResult<>(false, "归还失败");
    }

}
