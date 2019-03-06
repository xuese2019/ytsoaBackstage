package com.yts.ytsoa.business.jygl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gdgl.mapper.GdglMapper;
import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.jygl.mapper.JyglMapper;
import com.yts.ytsoa.business.jygl.model.JyglModel;
import com.yts.ytsoa.business.jygl.model.ResultModel;
import com.yts.ytsoa.business.jygl.service.JyglService;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.utils.GetUuid;
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
    @Autowired
    private XmshMapper xmshMapper;


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

    @Override
    public ResponseResult<PageInfo<JyglModel>> findAllJyjl(int pageNow, int pageSize, JyglModel jyglModel) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<JyglModel> list = jyglMapper.findAllJyjl(jyglModel);
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
    public ResponseResult<JyglModel> updById(JyglModel jyglModel, String accid) throws Exception {
        jyglModel.setGhr(accid);
        jyglModel.setGhrq(new Date());
        int result = jyglMapper.updById(jyglModel);
        if (result > 0) {
            JyglModel byId = jyglMapper.findByDamc(jyglModel.getUuid());
            if (byId != null) {
                GdglModel gdglModel = new GdglModel();
                gdglModel.setUuid(byId.getDamc());
                gdglModel.setJyzt(1);
                gdglMapper.update(gdglModel);
            }
            return new ResponseResult<>(true, "归还成功");
        } else return new ResponseResult<>(false, "归还失败");
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
        model.setJyzt(1);
        model.setShjg(1);
        int result = jyglMapper.add(model);
        if (result != 0) {
            return new ResponseResult<>(true, "借阅成功");
        }
        return new ResponseResult<>(false, "借阅失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<XmshModel> update(XmshModel model) throws Exception {
        String uuid = GetUuid.getUUID();
        model.setUuid(uuid);
        model.setShsj(new Date());
        int result = xmshMapper.add(model);
        if (result != 0) {
            JyglModel jyglModel = new JyglModel();
            jyglModel.setUuid(model.getPrentid());
            jyglModel.setShjg(model.getShjg());
            jyglMapper.update(jyglModel);
            JyglModel one = jyglMapper.findById2(model.getPrentid());
            if (one != null) {
                GdglModel gdglModel = new GdglModel();
                gdglModel.setUuid(one.getDamc());
                gdglModel.setJyzt(2);
                gdglMapper.update(gdglModel);
            }
            return new ResponseResult<>(true, "审核成功");
        } else {
            return new ResponseResult<>(false, "审核失败");
        }
    }

    @Override
    public ResponseResult<List<ResultModel>> findByShjl(String prentid) throws Exception {
        List<ResultModel> list = jyglMapper.findByShjl(prentid);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "查询成功", list);
        } else {
            return new ResponseResult<>(false, "无审核记录");
        }
    }
}

