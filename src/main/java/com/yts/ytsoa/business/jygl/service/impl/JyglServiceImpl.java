package com.yts.ytsoa.business.jygl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gdgl.mapper.GdglMapper;
import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.jygl.mapper.JyglMapper;
import com.yts.ytsoa.business.jygl.model.JyglModel;
import com.yts.ytsoa.business.jygl.service.JyglService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.poi.ExcelModelExportUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class JyglServiceImpl implements JyglService {
    @Autowired
    private JyglMapper jyglMapper;
    @Autowired
    private GdglMapper gdglMapper;

    /**
     * 条件分页查询
     *
     * @param pageNow
     * @param pageSize
     * @param jyglModel
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult<PageInfo<JyglModel>> findAll(int pageNow, int pageSize, JyglModel jyglModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<JyglModel> list = jyglMapper.findAll(jyglModel);
        PageInfo<JyglModel> page = new PageInfo<>(list);
        if (page.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", page);
        } else {
            return new ResponseResult<>(false, "查无信息", null);
        }
    }

    /**
     * 根据uuid查询
     *
     * @param jyglModel
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult<JyglModel> findById(JyglModel jyglModel) throws Exception {
        JyglModel list = jyglMapper.findById(jyglModel.getUuid());
        if (list != null) {
            return new ResponseResult<>(true, "查询成功", list);
        } else {
            return new ResponseResult<>(false, "查询信息", null);
        }
    }

    /**
     * 根据uuid修改
     *
     * @param jyglModel
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<JyglModel> updById(JyglModel jyglModel) throws Exception {
        int result = jyglMapper.updById(jyglModel);
        if (result > 0) {
            return new ResponseResult<>(true, "修改成功");
        } else return new ResponseResult<>(false, "修改失败");
    }

    /**
     * excel导出
     *
     * @param basePath
     * @param outPath
     * @param jyglModel
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult<String> excel(String basePath, String outPath, JyglModel jyglModel) throws Exception {
        List<JyglModel> list = jyglMapper.findAll(jyglModel);
        List<String[]> datas = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String strs[] = new String[6];
                strs[0] = String.valueOf(i + 1);
                strs[1] = list.get(i).getDgjybh();
                strs[2] = list.get(i).getXmmc();
                strs[3] = list.get(i).getSqf();
                SimpleDateFormat sh = new SimpleDateFormat("yyyy-MM-dd");
                strs[4] = sh.format(list.get(i).getJyrq());
                strs[5] = list.get(i).getJyr();
                datas.add(strs);
            }
            return new ExcelModelExportUtils().exportCurrency(basePath, outPath, datas);
        } else {
            return new ResponseResult<>(false, "未查询到记录");
        }
    }


    /**
     * 添加
     *
     * @param model
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<JyglModel> add(JyglModel model) throws Exception {
        model.setJyrq(new Date());
        model.setJyzt(2);
        int add = jyglMapper.add(model);
        if (add != 0) {
            GdglModel model1 = jyglMapper.findGdglByGdId(model.getDgid());
            model1.setJyzt(2);
            model1.setUuid(model.getDgid());
            gdglMapper.updById(model1);
        }
        return new ResponseResult<>(true, "借阅成功");
    }
}

