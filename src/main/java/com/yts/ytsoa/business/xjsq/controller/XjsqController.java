package com.yts.ytsoa.business.xjsq.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.shjl.service.XmshService;
import com.yts.ytsoa.business.xjsq.XjsqService.XjsqService;
import com.yts.ytsoa.business.xjsq.model.XjsqModel;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Api(value = "休假申请接口", description = "休假申请接口")
@RestController
@RequestMapping("/xjsq")
public class XjsqController {
    @Autowired
    private XjsqService xjsqService;
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private XmshService xmshService;

    @ApiOperation(value = "添加一条申请记录")
    @RequestMapping(value = "/addXjsq", method = RequestMethod.POST)
    public ResponseResult<XjsqModel> addXjsq(@RequestBody XjsqModel model) throws Exception {
        return xjsqService.addXjsq(model);
    }

    @ApiOperation(value = "分页带条件查询")
    @RequestMapping(value = "/find/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XjsqModel>> find(@PathVariable("pageNow") int pageNow, @RequestBody XjsqModel model, HttpServletRequest request) throws Exception {
        return xjsqService.find(pageNow, yamlPageUtils.getPageSize(), model, request);
    }


    @ApiOperation(value = "分页带条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XjsqModel>> kqgl(@PathVariable("pageNow") int pageNow, @RequestBody XjsqModel model, HttpServletRequest request) throws Exception {
        return xjsqService.kqgl(pageNow, yamlPageUtils.getPageSize(), model, request);
    }

    @ApiOperation(value = "根据uuid查出详细信息")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<XjsqModel> findById(@PathVariable("uuid") String uuid) throws SQLException {
        return xjsqService.findById(uuid);
    }


    @ApiOperation(value = "根据uuid查出详细信息")
    @RequestMapping(value = "/findByBm/{bm}", method = RequestMethod.GET)
    public ResponseResult<XjsqModel> findByBm(@PathVariable("bm") String bm) throws SQLException {
        return xjsqService.findByBm(bm);
    }

    @ApiOperation(value = "休假审核")
    @RequestMapping(value = "/xjsh", method = RequestMethod.POST)
    public ResponseResult<XmshModel> xjsh(@RequestBody XmshModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        model.setShr(accid);
        return xjsqService.xjsh(model, request);
    }
}
