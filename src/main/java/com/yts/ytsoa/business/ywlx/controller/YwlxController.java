package com.yts.ytsoa.business.ywlx.controller;

import com.yts.ytsoa.business.ywlx.model.YwlxModel;
import com.yts.ytsoa.business.ywlx.service.YwlxService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author LD
 */
@Api(value = "业务类型接口", description = "业务类型接口")
@Slf4j
@RestController
@RequestMapping("/ywlx")
public class YwlxController {

    @Autowired
    private YamlPageUtils yamlPageUtils;

    @Autowired
    private YwlxService service;

    @ApiOperation(value = "增加类型")
    @RequestMapping(value = "/ywlx", method = RequestMethod.POST)
    public ResponseResult<YwlxModel> add(@Valid @RequestBody YwlxModel model,
                                         BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        return service.add(model);
    }

    @ApiOperation(value = "根据id删除")
    @RequestMapping(value = "/ywlx/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<YwlxModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return service.deleteById(uuid);
    }

    @ApiOperation(value = "根据id修改")
    @RequestMapping(value = "/ywlx", method = RequestMethod.PUT)
    public ResponseResult<YwlxModel> updateById(@Valid @RequestBody YwlxModel model,
                                                BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        return service.updateById(model);
    }

    /*    @ApiOperation(value = "分页条件查询")
        @RequestMapping(value = "/ywlx/{pageNow}", method = RequestMethod.POST)
        public ResponseResult<PageInfo<YwlxModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                           @RequestBody YwlxModel model) throws Exception {
            return service.findAll(pageNow, yamlPageUtils.getPageSize(), model);
        }*/
    @ApiOperation(value = "查询所有")
    @RequestMapping(value = "/ywlx/all", method = RequestMethod.POST)
    public ResponseResult<List<YwlxModel>> findAll2() throws Exception {
        return service.findAll2();
    }

    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/oneId/{uuid}", method = RequestMethod.GET)
    public ResponseResult<YwlxModel> getById(@PathVariable("uuid") String uuid) throws Exception {
        return service.getById(uuid);
    }
}
