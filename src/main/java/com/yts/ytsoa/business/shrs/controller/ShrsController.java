package com.yts.ytsoa.business.shrs.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.shrs.model.ShrsModel;
import com.yts.ytsoa.business.shrs.service.ShrsService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "审核人", description = "审核人接口")
@RestController
@RequestMapping("/shrs")
public class ShrsController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private ShrsService shrsService;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<ShrsModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            ShrsModel shrsModel) throws Exception {
        return shrsService.findAll(pageNow, yamlPageUtils.getPageSize(), shrsModel);
    }

    @ApiOperation(value = "根据id查出该条信息")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<ShrsModel> findById(@PathVariable("uuid") String uuid) throws Exception {
        return shrsService.findById(uuid);
    }

    @ApiOperation(value = "新增审核人")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<ShrsModel> add(@RequestBody ShrsModel shrsModel, HttpServletRequest request) throws Exception {
        String dqdlr = JWTUtils.getAccId(request);
    /*    zskModel.setTjr(dqdlr);
        zskModel.setJrsj(new Date());*/
        return shrsService.add(shrsModel);
    }

    @ApiOperation(value = "根据id删除")
    @RequestMapping(value = "/delById/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<ShrsModel> delById(@PathVariable("uuid") String uuid) throws Exception {
        /*  String accId = JWTUtils.getAccId(request);*/
        return shrsService.delById(uuid);
    }

    @ApiOperation(value = "根据id修改")
    @RequestMapping(value = "/updById", method = RequestMethod.PUT)
    public ResponseResult<ShrsModel> updById(@RequestBody ShrsModel shrsModel, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        return shrsService.updById(shrsModel);
    }
}
