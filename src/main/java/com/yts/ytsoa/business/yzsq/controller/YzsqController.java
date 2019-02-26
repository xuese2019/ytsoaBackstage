package com.yts.ytsoa.business.yzsq.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.yzsq.model.ResultsModel;
import com.yts.ytsoa.business.yzsq.model.YzsqModel;
import com.yts.ytsoa.business.yzsq.result.result;
import com.yts.ytsoa.business.yzsq.service.YzsqService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlFileUtils;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "用章申请接口", description = "用章申请接口")
@RestController
@RequestMapping("/yzsq")
public class YzsqController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private YamlFileUtils yamlFileUtils;
    @Autowired
    private YzsqService yzsqService;

    @ApiOperation(value = "添加一条用章申请")
    @RequestMapping(value = "/addYzsq", method = RequestMethod.POST)
    public ResponseResult<YzsqModel> addYzsq(@RequestBody YzsqModel model, HttpServletRequest request) throws Exception {
        /*String accid = JWTUtils.getAccId(request);
        String xmid = model.getXmid();
        ResponseResult<List<XmcyModel>> list = yzsqService.findXmcyByXmid(xmid);
        if (list.getData() != null) {
            for (int i = 0; i < list.getData().size(); i++) {
                String xmcy = list.getData().get(i).getYgid();
                if (accid.equals(xmcy)) {
                    model.setShzt(1);
                    return yzsqService.addYzsq(model);
                }
            }
        }
        return new ResponseResult<>(false, "只有该项目成员才能申请用章");*/
        if (model != null) {
            return yzsqService.addYzsq(model);
        }
        return new ResponseResult<>(false, "用章申请失败");
    }

    @ApiOperation(value = "条件查询带分页")
    @RequestMapping(value = "/find/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<YzsqModel>> find(@PathVariable("pageNow") int pageNow, @RequestBody YzsqModel model) throws Exception {
        return yzsqService.find(pageNow, yamlPageUtils.getPageSize(), model);
    }

    @ApiOperation(value = "用章申请修改")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseResult<YzsqModel> update(@RequestBody YzsqModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        return yzsqService.update(model);
    }

    @ApiOperation(value = "excel导出")
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public ResponseResult<String> excel(@RequestBody YzsqModel model, HttpServletResponse response) throws Exception {
        return yzsqService.excel(yamlFileUtils.getFileModel() + "yzsqModel.xls", yamlFileUtils.getDowPath(), model);
    }

    @ApiOperation(value = "根据id查出该条信息")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<YzsqModel> findById(@PathVariable("uuid") String uuid) throws Exception {
        return yzsqService.findById(uuid);
    }

    @ApiOperation(value = "项目详情用章管理")
    @RequestMapping(value = "/yzgl/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<result>> yzgl(@PathVariable("pageNow") int pageNow, @RequestBody YzsqModel model) throws Exception {
        if (model != null) {
            return yzsqService.yzgl(pageNow, yamlPageUtils.getPageSize(), model);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @ApiOperation(value = "用章审核")
    @RequestMapping(value = "/yzsh", method = RequestMethod.POST)
    public ResponseResult<XmshModel> yzsh(@RequestBody XmshModel model, HttpServletRequest request, String fsr) throws Exception {
        String accid = JWTUtils.getAccId(request);
        if (model != null) {
            model.setShr(accid);
            return yzsqService.yzsh(model, fsr);
        }
        return new ResponseResult<>(false, "审核失败");
    }

    @ApiOperation(value = "审核记录")
    @RequestMapping(value = "/findByShjl/{prentid}", method = RequestMethod.GET)
    public ResponseResult<List<ResultsModel>> findByShjl(@PathVariable("prentid") String prentid) throws Exception {
        ResponseResult<List<ResultsModel>> result = yzsqService.findByShjl(prentid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result.getData());
        } else {
            return new ResponseResult<>(false, "没有审核记录");
        }
    }

    @ApiOperation(value = "项目管理，详情里的用章管理，且都是已审核通过的申请记录")
    @RequestMapping(value = "findYzglByXmid/{xmid}", method = RequestMethod.GET)
    public ResponseResult<List<YzsqModel>> findYzglByXmid(@PathVariable(value = "xmid") String xmid) throws Exception {
        return yzsqService.findYzglByXmid(xmid);
    }
}
