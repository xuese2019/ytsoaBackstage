package com.yts.ytsoa.business.bm.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bm.model.BmModel;
import com.yts.ytsoa.business.bm.service.BmService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "部门", description = "部门接口")
@RestController
@RequestMapping("/bm")
public class BmController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private BmService bmService;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<BmModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            BmModel bmModel) throws Exception {
        return bmService.findAll(pageNow, yamlPageUtils.getPageSize(), bmModel);
    }

    @ApiOperation(value = "根据id查出该条信息")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<BmModel> findById(@PathVariable("uuid") String uuid) throws Exception {
        return bmService.findById(uuid);
    }


    @ApiOperation(value = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseResult<BmModel> update(@RequestBody BmModel bmModel) throws Exception {
        /* String accid = JWTUtils.getAccId(request);*/
        return bmService.update(bmModel);
    }

    @ApiOperation(value = "根据id删除")
    @RequestMapping(value = "/del/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<BmModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return bmService.deleteById(uuid);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<BmModel> add(@RequestBody BmModel bmModel) throws Exception {
        return bmService.add(bmModel);
    }
}
