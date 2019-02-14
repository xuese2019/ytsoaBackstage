package com.yts.ytsoa.business.jyjl.controller;

import com.yts.ytsoa.business.jyjl.model.JyjlModel;
import com.yts.ytsoa.business.jyjl.service.JyjlService;
import com.yts.ytsoa.utils.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "借阅记录接口")
@RestController
@RequestMapping("/jyjl")
public class JyjlController {

    @Autowired
    private JyjlService jyjlService;

    public ResponseResult<JyjlModel> insert(@RequestBody JyjlModel model) throws Exception {
        return jyjlService.isnert(model);
    }
}
