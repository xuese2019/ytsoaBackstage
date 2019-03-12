package com.yts.ytsoa.business.sz.controller;

import com.yts.ytsoa.business.sz.model.SzModel;
import com.yts.ytsoa.business.sz.service.SzService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "所长", description = "所长接口")
@RestController
@RequestMapping("/sz")
public class SzController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private SzService szService;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseResult<SzModel> findAll() throws Exception {
        ResponseResult<List<SzModel>> all = szService.findAll();
        if (all.isSuccess()) {
            return new ResponseResult<>(true, "成功", all.getData().get(0));
        }
        return new ResponseResult<>(false, "未查询到数据");
    }

//    @ApiOperation(value = "根据id查出该条信息")
//    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
//    public ResponseResult<SzModel> findById(@PathVariable("uuid") String uuid) throws Exception {
//        return szService.findById(uuid);
//    }


/*    @ApiOperation(value = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseResult<SzModel> update(@RequestBody SzModel szModel) throws Exception {
        *//* String accid = JWTUtils.getAccId(request);*//*
        return szService.update(szModel);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<SzModel> add(@RequestBody SzModel szModel) throws Exception {
        return szService.add(szModel);
    }*/

    @ApiOperation(value = "设置所长")
    @RequestMapping(value = "/setsz", method = RequestMethod.POST)
    public ResponseResult<SzModel> setSz(@RequestBody SzModel szModel) throws Exception {
        return szService.setSz(szModel);
    }
}
