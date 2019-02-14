package com.yts.ytsoa.business.xjsp.controller;

import com.yts.ytsoa.business.xjsp.model.XjspModel;
import com.yts.ytsoa.business.xjsp.service.XjspService;
import com.yts.ytsoa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "休假审批接口", description = "休假审批接口")
@RestController
@RequestMapping("/xjsp")
public class XjspController {
    @Autowired
    private XjspService xjspService;

    @ApiOperation(value = "添加一条休假审批的记录")
    @RequestMapping(value = "/addXjsp", method = RequestMethod.POST)
    public ResponseResult<XjspModel> addXjsp(@RequestBody XjspModel model) {
        return xjspService.addXjsp(model);
    }
}
