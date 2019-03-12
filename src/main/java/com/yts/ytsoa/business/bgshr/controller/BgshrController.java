package com.yts.ytsoa.business.bgshr.controller;

import com.yts.ytsoa.business.bgshr.model.BgshrModel;
import com.yts.ytsoa.business.bgshr.service.BgshrszService;
import com.yts.ytsoa.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "报告审核人设置", description = "报告审核人设置")
@RestController
@RequestMapping("/bgshr")
public class BgshrController {
    @Autowired
    private BgshrszService bgshrszService;

    @ApiOperation(value = "设置报告审核人")
    @RequestMapping(value = "/bgshrsz", method = RequestMethod.PUT)
    public ResponseResult<BgshrModel> bgshrsz(@RequestBody BgshrModel model) throws Exception {
        if (model != null) {
            return bgshrszService.bgshrsz(model);
        }
        return new ResponseResult<>(false, "参数缺失，设置失败");
    }

    @ApiOperation(value = "查询报告审核人")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseResult<BgshrModel> find() throws Exception {
        return bgshrszService.find();
    }

}
