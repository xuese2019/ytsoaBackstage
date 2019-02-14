package com.yts.ytsoa.business.zsk.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.zsk.model.ZskModel;
import com.yts.ytsoa.business.zsk.service.ZskService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Api(value = "知识库", description = "知识库接口")
@RestController
@RequestMapping("/zsk")
public class ZskController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private ZskService zskService;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<ZskModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            ZskModel zskModel) throws Exception {
        return zskService.findAll(pageNow, yamlPageUtils.getPageSize(), zskModel);
    }

    /**
     * 根据id查询
     *
     * @param request
     * @param uuid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<ZskModel> findById(@PathVariable("uuid") HttpServletRequest request, String uuid) throws Exception {
        ZskModel zskModel = new ZskModel();
        zskModel.setUuid(uuid);
        String accId = JWTUtils.getAccId(request);
        ResponseResult<List<ZskModel>> result = zskService.findById(zskModel);
        return new ResponseResult<>(result.isSuccess(), result.getMessage(), result.getData() != null ? result.getData().get(0) : null);
    }

    /**
     * 新增知识库
     */
    @ApiOperation(value = "新增知识库")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<ZskModel> add(@RequestBody ZskModel zskModel, HttpServletRequest request) throws Exception {
        String dqdlr = JWTUtils.getAccId(request);
        zskModel.setTjr(dqdlr);
        zskModel.setJrsj(new Date());
        return zskService.add(zskModel);
    }

    /**
     * 根据id删除
     *
     * @param uuid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据id删除")
    @RequestMapping(value = "/delById/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<ZskModel> delById(@PathVariable("uuid") String uuid) throws Exception {
        /*  String accId = JWTUtils.getAccId(request);*/
        return zskService.delById(uuid);
    }

    /**
     * 根据id修改
     *
     * @param zskModel
     * @param result
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据id修改")
    @RequestMapping(value = "/updById", method = RequestMethod.PUT)
    public ResponseResult<ZskModel> updById(@RequestBody ZskModel zskModel, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        return zskService.updById(zskModel);
    }

    /**
     * 批量添加
     *
     * @param zskModel
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "批量添加")
    @RequestMapping(value = "/addZsk", method = RequestMethod.POST)
    public ResponseResult<ZskModel> addZsk(@RequestBody List<ZskModel> zskModel) throws Exception {
        return zskService.addZsk(zskModel);
    }

}
