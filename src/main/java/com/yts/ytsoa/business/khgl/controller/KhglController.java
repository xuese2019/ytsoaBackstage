package com.yts.ytsoa.business.khgl.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.khgl.model.KhglModel;
import com.yts.ytsoa.business.khgl.service.KhglService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "客户管理接口", description = "客户管理接口")
@RestController
@RequestMapping("/khgl")
public class KhglController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private KhglService khglService;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<KhglModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            KhglModel khglModel) throws Exception {
        return khglService.findAll(pageNow, yamlPageUtils.getPageSize(), khglModel);
    }

    /**
     * 新增客户
     */
    @ApiOperation(value = "新增客户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<KhglModel> add(@RequestBody KhglModel khglModel) throws Exception {
        return khglService.add(khglModel);
    }

    /**
     * 删除客户
     */
    @ApiOperation(value = "删除客户")
    @RequestMapping(value = "/deleteById/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<KhglModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return khglService.deleteById(uuid);
    }

    /**
     * 根据id查询客户
     */
    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<KhglModel> findById(@PathVariable("uuid") String uuid) throws Exception {
        ResponseResult<List<KhglModel>> result = khglService.findById(uuid);
        return new ResponseResult<>(result.isSuccess(), result.getMessage(), result.getData() != null ? result.getData().get(0) : null);
    }

    /**
     * 根据id修改用户
     */
    @ApiOperation(value = "根据id修改用户")
    @RequestMapping(value = "/updById", method = RequestMethod.PUT)
    public ResponseResult<KhglModel> updById(@RequestBody KhglModel khglModel, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        return khglService.updById(khglModel);
    }
}
