package com.yts.ytsoa.business.yzsq.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.mapper.XmshMapper;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.xmcy.model.XmcyModel;
import com.yts.ytsoa.business.xmwp.mapper.XmwpMapper;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.business.xxgl.service.XxglService;
import com.yts.ytsoa.business.xxgl.utils.XxglUtils;
import com.yts.ytsoa.business.yzsq.mapper.YzsqMapper;
import com.yts.ytsoa.business.yzsq.model.ResultsModel;
import com.yts.ytsoa.business.yzsq.model.YzsqModel;
import com.yts.ytsoa.business.yzsq.result.result;
import com.yts.ytsoa.business.yzsq.service.YzsqService;
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
public class YzsqServiceImpl implements YzsqService {
    @Autowired
    private YzsqMapper yzsqMapper;
    @Autowired
    private XmwpMapper xmwpMapper;
    @Autowired
    private XmshMapper xmshMapper;

    @Autowired
    private XxglService xxglService;

    /**
     * 添加
     *
     * @param model
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<YzsqModel> addYzsq(YzsqModel model) throws Exception {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String s1 = s.format(System.currentTimeMillis());
        model.setSsnf(s1);
        model.setShjg(1);
        int result = yzsqMapper.addYzsq(model);
        XmwpModel model1 = xmwpMapper.findById(model.getXmid());
        int ywzt = model1.getYwzt();
        if (ywzt < 2) {
            return new ResponseResult<>(false, "项目未开始，不能申请用章");
        }
        if (result > 0) {
            return new ResponseResult<>(true, "申请用章成功");
        }
        return new ResponseResult<>(false, "申请用章失败");
    }

    /**
     * 查询所有
     *
     * @param pageNow
     * @param pageSize
     * @param model
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<YzsqModel>> find(int pageNow, int pageSize, YzsqModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<YzsqModel> list = yzsqMapper.find(model);
        PageInfo<YzsqModel> pageInfo = new PageInfo<>(list);
        if (pageInfo.getSize() > 0) {
            return new ResponseResult<>(true, "查询成功", pageInfo);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    /**
     * 修改
     *
     * @param model
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<YzsqModel> update(YzsqModel model) throws Exception {
        int result = yzsqMapper.update(model);
        if (result > 0) {
            return new ResponseResult<>(true, "修改成功");
        }
        return new ResponseResult<>(false, "修改失败");
    }

    /**
     * excel导出
     *
     * @param basePath
     * @param outPath
     * @param model
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<String> excel(String basePath, String outPath, YzsqModel model) throws Exception {
        List<YzsqModel> list = yzsqMapper.find(model);
        List<String[]> datas = new ArrayList<>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String str[] = new String[7];
                str[0] = String.valueOf(i + 1);
                if (list.get(i).getSqr() != null) {
                    str[1] = list.get(i).getSqr();
                }
                if (list.get(i).getWjmc() != null) {
                    str[2] = list.get(i).getWjmc();
                }
                if (list.get(i).getYzdw() != null) {
                    str[3] = list.get(i).getYzdw();
                }
                if (list.get(i).getBz() != null) {
                    str[4] = list.get(i).getBz();
                }
                if (String.valueOf(list.get(i).getSqrq()) != null) {
                    str[5] = String.valueOf(list.get(i).getSqrq());
                }
                if (list.get(i).getSsnf() != null) {
                    str[6] = list.get(i).getSsnf();
                }
                datas.add(str);
            }
            return new ExcelModelExportUtils().exportCurrency(basePath, outPath, datas);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    /**
     * 根据id查询
     *
     * @param uuid
     * @return
     */

    @Override
    public ResponseResult<YzsqModel> findById(String uuid) throws Exception {
        YzsqModel result = yzsqMapper.findById(uuid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PageInfo<result>> yzgl(int pageNow, int pageSize, YzsqModel model) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<result> result = yzsqMapper.yzgl(model);
        PageInfo<result> page = new PageInfo<>(result);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", page);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<List<XmcyModel>> findXmcyByXmid(String xmid) throws Exception {
        if (xmid != null) {
            List<XmcyModel> list = yzsqMapper.findXmcyByXmid(xmid);
            if (list.size() > 0) {
                return new ResponseResult<>(true, "查询成功", list);
            }
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @Override
    public ResponseResult<XmshModel> yzsh(XmshModel model, String fsr) throws Exception {
        model.setShsj(new Date());
        int result = xmshMapper.add(model);
        if (result != 0) {
            YzsqModel yzsqModel = new YzsqModel();
            yzsqModel.setUuid(model.getPrentid());
            yzsqModel.setShjg(model.getShjg());
            yzsqMapper.updateByShjg(yzsqModel);
            xxglService.save(new XxglUtils().setXx(2, "用章审核成功：" + model.getShsj(), model.getShyj(), fsr, yzsqModel.getSqr()));
            return new ResponseResult<>(true, "审核成功");
        }
        return new ResponseResult<>(false, "审核失败");
    }

    @Override
    public ResponseResult<List<ResultsModel>> findByShjl(String prentid) throws Exception {
        List<ResultsModel> list = yzsqMapper.findByShjl(prentid);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "查询成功", list);
        } else {
            return new ResponseResult<>(false, "无审核记录");
        }
    }
}
