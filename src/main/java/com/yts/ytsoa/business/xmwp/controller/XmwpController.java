package com.yts.ytsoa.business.xmwp.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmwp.model.XmwpModel;
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
        return xmwpService.addXmwp(model, accId);
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

    @ApiOperation(value = "项目管理条件查询，分页")
    @RequestMapping(value = "/findByXmmc/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XmwpModel>> findByXmmc(@PathVariable("pageNow") int pageNow, @RequestBody XmwpModel model, HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        model.setXmfzr(accId);
        return xmwpService.findByXmmc(pageNow, yamlPageUtils.getPageSize(), model, accId);
    }

    @ApiOperation(value = "项目委派管理分页查询带条件")
    @RequestMapping(value = "/xmwpByXmmc/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XmwpModel>> xmwpByXmmc(@PathVariable("pathNow") int pageNow, @RequestBody XmwpModel model, HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        model.setWpr(accId);
        return xmwpService.findByXmmc(pageNow, yamlPageUtils.getPageSize(), model, accId);
    }

    @ApiOperation(value = "excel导出")
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public ResponseResult<String> excel(@RequestBody XmwpModel model, HttpServletResponse response) throws Exception {
        return xmwpService.excel(yamlFileUtils.getFileModel() + "xmwpModel.xls", yamlFileUtils.getDowPath(), model);
    }

    @ApiOperation(value = "项目审核")
    @RequestMapping(value = "/xmsh", method = RequestMethod.POST)
    public ResponseResult<XmwpModel> xmjlsh(@RequestBody XmwpModel model, HttpServletRequest request) throws Exception {
        if (model != null) {



            return xmwpService.xmsh(model, request);

        }
        return new ResponseResult<>(false, "审核失败");
    }
}