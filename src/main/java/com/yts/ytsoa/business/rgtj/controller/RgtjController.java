package com.yts.ytsoa.business.rgtj.controller;

import com.yts.ytsoa.business.rgtj.model.RgtjModel;
import com.yts.ytsoa.business.rgtj.service.RgtjService;
import com.yts.ytsoa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "人工统计", description = "人工统计")
@RestController
@RequestMapping("/rgtj")
public class RgtjController {
    @Autowired
    private RgtjService rgtjService;

    @ApiOperation(value = "人工统计，添加数据")
    @RequestMapping(value = "/rgtj", method = RequestMethod.POST)
    public ResponseResult<RgtjModel> find() throws Exception {
        return rgtjService.insertRgtj();
    }
}
