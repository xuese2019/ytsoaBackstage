package com.yts.ytsoa.business.shjd.controller;

import com.yts.ytsoa.business.shjd.model.ShjdModel;
import com.yts.ytsoa.business.shjd.service.ShjdService;
import com.yts.ytsoa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "审核阶段接口", description = "审核阶段接口")
@RestController
@RequestMapping("/shjd")
public class ShjdController {
    @Autowired
    private ShjdService shjdService;

    @ApiOperation(value = "查询")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseResult<ShjdModel> find(@RequestBody ShjdModel model) {
        return shjdService.find(model);
    }

    @ApiOperation(value = "添加")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseResult<ShjdModel> insert(@RequestBody ShjdModel model) {
        return shjdService.insert(model);
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseResult<ShjdModel> update(@RequestBody ShjdModel model) {
        return shjdService.update(model);
    }
}
