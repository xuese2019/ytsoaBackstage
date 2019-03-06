package com.yts.ytsoa.business.xmwp.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
import com.yts.ytsoa.business.xmwp.model.XmwpShjlModel;
import com.yts.ytsoa.business.xmwp.service.XmwpService;
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

@Api(value = "项目委派接口", description = "项目委派接口")
@RestController
@RequestMapping("/xmwp")
public class XmwpController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private XmwpService xmwpService;
    @Autowired
    private YamlFileUtils yamlFileUtils;

    @ApiOperation(value = "添加一条项目委派的记录")
    @RequestMapping(value = "/addXmwp", method = RequestMethod.POST)
    public ResponseResult<XmwpModel> addXmwp(@RequestBody XmwpModel model,
                                             HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        model.setWpr(accId);
        return xmwpService.addXmwp(model);
    }

/*    @ApiOperation(value = "带条件查询，默认搜索全部")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseResult<List<XmwpModel>> find(@RequestBody XmwpModel model) throws Exception {
        return xmwpService.find(model);
    }*/

    @ApiOperation(value = "根据uuid删除一条记录")
    @RequestMapping(value = "/delById/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<XmwpModel> delById(@PathVariable("uuid") String uuid) throws Exception {
        return xmwpService.delById(uuid);
    }

    @ApiOperation(value = "项目委派信息修改")
    @RequestMapping(value = "/updById", method = RequestMethod.PUT)
    public ResponseResult<XmwpModel> updById(@RequestBody XmwpModel model) throws Exception {
        return xmwpService.updById(model);
    }

    @ApiOperation(value = "根据uuid查出该条项目委派的信息")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<XmwpModel> findById(@PathVariable("uuid") String uuid) throws Exception {
        return xmwpService.findById(uuid);
    }

    @ApiOperation(value = "项目审核页面条件查询，分页")
    @RequestMapping(value = "/findByXmmc/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<List<XmwpModel>> findByXmmc(@PathVariable(value = "pageNow") int pageNow, @RequestBody XmwpModel model, HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        return xmwpService.findxmsh(pageNow, model, accId);
    }


    @ApiOperation(value = "项目管理条件查询，分页")
    @RequestMapping(value = "/findByXmyq/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XmwpModel>> findByXmyq(@PathVariable("pageNow") int pageNow, @RequestBody XmwpModel model, HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        return xmwpService.findByXmyq(pageNow, yamlPageUtils.getPageSize(), model, accId);
    }

    @ApiOperation(value = "项目委派管理分页查询带条件")
    @RequestMapping(value = "/xmwpByXmmc/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XmwpModel>> xmwpByXmmc(@PathVariable("pathNow") int pageNow, @RequestBody XmwpModel model, HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        return xmwpService.findByXmmc(pageNow, yamlPageUtils.getPageSize(), model, accId);
    }

    @ApiOperation(value = "excel导出")
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public ResponseResult<String> excel(@RequestBody XmwpModel model, HttpServletResponse response) throws Exception {
        return xmwpService.excel(yamlFileUtils.getFileModel() + "xmwpModel.xls", yamlFileUtils.getDowPath(), model);
    }

    @ApiOperation(value = "项目审核")
    @RequestMapping(value = "/xmsh", method = RequestMethod.POST)
    public ResponseResult<XmshModel> xmsh(@RequestBody XmshModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        if (model != null) {
            return xmwpService.xmsh(model, accid);
        }
        return new ResponseResult<>(false, "缺失参数，审核失败");
    }

    @ApiOperation(value = "委派管理页面，分页条件查询")
    @RequestMapping(value = "/find/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XmwpModel>> find(@PathVariable("pageNow") int pageNow, @RequestBody XmwpModel model) throws Exception {
        return xmwpService.find(pageNow, yamlPageUtils.getPageSize(), model);
    }

    /**
     * 报告申请页面
     * 项目名称搜索只能查询自己承接的项目
     * 并且必须得是审核通过的项目才能申请报告
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "每个项目负责人只能查询自己的项目，并且是已经审核通过的")
    @RequestMapping(value = "/findByXmfzr", method = RequestMethod.POST)
    public ResponseResult<List<XmwpModel>> findByXmfzr(@RequestBody XmwpModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        return xmwpService.findByXmfzr(model, accid);
    }

    @ApiOperation(value = "项目管理页面，分页条件查询")
    @RequestMapping(value = "/xmgl/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XmwpModel>> xmgl(@PathVariable("pageNow") int pageNow, @RequestBody XmwpModel model) throws Exception {
        return xmwpService.find(pageNow, yamlPageUtils.getPageSize(), model);
    }


    @ApiOperation(value = "审核记录")
    @RequestMapping(value = "/findByShjl/{prentid}", method = RequestMethod.GET)
    public ResponseResult<List<XmwpShjlModel>> findByShjl(@PathVariable("prentid") String prentid) throws Exception {
        ResponseResult<List<XmwpShjlModel>> result = xmwpService.findByShjl(prentid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result.getData());
        } else {
            return new ResponseResult<>(false, "没有审核记录");
        }
    }
}
