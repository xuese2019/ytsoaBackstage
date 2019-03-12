package com.yts.ytsoa.business.shrsz.controller;

import com.yts.ytsoa.business.shrsz.model.ShrszModel;
import com.yts.ytsoa.business.shrsz.service.ShrszService;
import com.yts.ytsoa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "设置审核人", description = "设置审核人")
@RestController
@RequestMapping(value = "/shrsz")
public class ShrszController {
    @Autowired
    private ShrszService shrszService;

    @ApiOperation(value = "审核人设置接口")
    @RequestMapping(value = "/shrsz", method = RequestMethod.PUT)
    ResponseResult<ShrszModel> shrsz(@RequestBody ShrszModel model) throws Exception {
        if (model != null) {
            return shrszService.shrsz(model);
        }
        return new ResponseResult<>(false, "数据为空，无法设置");
    }

    @ApiOperation(value = "审核人查询")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    ResponseResult<ShrszModel> findAll() throws Exception {
        return shrszService.findAll();
    }
}
