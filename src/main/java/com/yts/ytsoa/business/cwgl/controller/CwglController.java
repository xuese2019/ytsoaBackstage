package com.yts.ytsoa.business.cwgl.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.cwgl.model.CwglModel;
import com.yts.ytsoa.business.cwgl.service.CwglService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "财务管理", description = "财务管理接口")
@RestController
@RequestMapping("/cwgl")
public class CwglController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private CwglService cwglService;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<CwglModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            CwglModel cwglModel) throws Exception {
        return cwglService.findAll(pageNow, yamlPageUtils.getPageSize(), cwglModel);
    }

    /**
     * 新增财务管理
     */
    @ApiOperation(value = "新增财务管理")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<CwglModel> add(@RequestBody CwglModel cwglModel, HttpServletRequest request) throws Exception {
        return cwglService.add(cwglModel);
    }

    /**
     * 根据id查询
     *
     * @param
     * @param uuid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<CwglModel> findById(@PathVariable("uuid") String uuid) throws Exception {
/*        ZskModel zskModel = new ZskModel();
        zskModel.setUuid(uuid);
        String accId = JWTUtils.getAccId(request);*/
        ResponseResult<List<CwglModel>> result = cwglService.findById(uuid);
        return new ResponseResult<>(result.isSuccess(), result.getMessage(), result.getData() != null ? result.getData().get(0) : null);
    }

    @ApiOperation(value = "根据id修改")
    @RequestMapping(value = "/updById", method = RequestMethod.PUT)
    public ResponseResult<CwglModel> updById(@RequestBody CwglModel cwglModel, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        return cwglService.updById(cwglModel);
    }
}
