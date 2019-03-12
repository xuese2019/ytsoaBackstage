package com.yts.ytsoa.business.swsdt.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.swsdt.model.SwsdtModel;
import com.yts.ytsoa.business.swsdt.service.SwsdtService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "事务所动态", description = "事务所动态")
@Slf4j
@RestController
@RequestMapping("/swsdt")
public class SwsdtController {
    @Autowired
    private SwsdtService swsdtService;

    @Autowired
    private YamlPageUtils yamlPageUtils;

    @ApiOperation(value = "添加事务所动态")
    @RequestMapping(value = "/insertSwsdt", method = RequestMethod.POST)
    public ResponseResult<SwsdtModel> insertSwsdt(@RequestBody SwsdtModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        return swsdtService.insertSwsdt(model, accid);
    }

    @ApiOperation(value = "条件查询带分页")
    @RequestMapping(value = "/find/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<SwsdtModel>> find(@PathVariable(value = "pageNow") int pageNow, @RequestBody SwsdtModel model) throws Exception {
        return swsdtService.find(pageNow, yamlPageUtils.getPageSize(), model);
    }
}
