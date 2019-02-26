package com.yts.ytsoa.business.gzrz.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gzrz.model.GzrzModel;
import com.yts.ytsoa.business.gzrz.service.GzrzService;
import com.yts.ytsoa.business.xmcy.service.XmcyService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "工作日志接口", description = "工作日志接口")
@RestController
@RequestMapping("/gzrz")
public class GzrzController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private GzrzService gzrzService;
    @Autowired
    private XmcyService xmcyService;

    @ApiOperation(value = "条件查询带分页，默认搜索全部")
    @RequestMapping(value = "/find/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<GzrzModel>> find(@PathVariable("pageNow") int pageNow, @RequestBody GzrzModel model) {
        return gzrzService.find(pageNow, yamlPageUtils.getPageSize(), model);
    }

    @ApiOperation(value = "添加一条日志（非/项目日志）")
    @RequestMapping(value = "/addGzrz", method = RequestMethod.POST)
    public ResponseResult<GzrzModel> addGzrz(@RequestBody GzrzModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        return gzrzService.addGzrz(model, accid);
    }

    @ApiOperation(value = "根据项目id，查出该项目的所有日志")
    @RequestMapping(value = "/findByXmid/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<GzrzModel>> findByXmid(@PathVariable("pageNow") int pageNow, @RequestBody GzrzModel model) throws Exception {
        return gzrzService.findByXmid(pageNow, yamlPageUtils.getPageSize(), model);
    }

    @ApiOperation(value = "人工统计")
    @RequestMapping(value = "/rgtj/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<GzrzModel>> rgtj(@PathVariable("pageNow")int pageNow,@RequestBody GzrzModel model) throws Exception {
        return gzrzService.rgtj(pageNow, yamlPageUtils.getPageSize(), model);
    }
}
