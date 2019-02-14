package com.yts.ytsoa.business.zzjg.controller;

import com.yts.ytsoa.business.zzjg.model.ZzjgModel;
import com.yts.ytsoa.business.zzjg.service.ZzjgService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author LD
 */
@Api(value = "组织机构接口", description = "组织机构接口")
@Slf4j
@RestController
@RequestMapping("/zzjg")
public class ZzjgController {

    @Autowired
    private YamlPageUtils yamlPageUtils;

    @Autowired
    private ZzjgService service;

    @ApiOperation(value = "新增组织机构，只有管理员能操作")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/zzjg", method = RequestMethod.POST)
    public ResponseResult<ZzjgModel> add(@Valid @RequestBody ZzjgModel model,
                                         BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        if (model.getUuid() != null && !model.getUuid().isEmpty()) {
            return service.updateById(model);
        } else {
            return service.add(model);
        }
    }

    @ApiOperation(value = "根据id删除组织机构，只有管理员能操作")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/zzjg/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<ZzjgModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return service.deleteById(uuid);
    }

    @ApiOperation(value = "根据id修改组织机构名称")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/zzjg", method = RequestMethod.PUT)
    public ResponseResult<ZzjgModel> updateById(@Valid @RequestBody ZzjgModel model,
                                                BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        return service.updateById(model);
    }

    @ApiOperation(value = "根据id获取组织机构信息")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/zzjg/{uuid}", method = RequestMethod.GET)
    public ResponseResult<ZzjgModel> updateById(@PathVariable("uuid") String uuid) throws Exception {
        return service.getById(uuid);
    }

    @ApiOperation(value = "递归获取组织机构用于创建树")
    @RequestMapping(value = "/recursive", method = RequestMethod.GET)
    public ResponseResult<List<ZzjgModel>> recursive() throws Exception {
        return service.findAll();
    }
}
