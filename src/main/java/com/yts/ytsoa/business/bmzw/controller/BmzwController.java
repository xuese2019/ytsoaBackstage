package com.yts.ytsoa.business.bmzw.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.bmzw.model.BmzwModel;
import com.yts.ytsoa.business.bmzw.service.BmzwService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "部门职务", description = "部门职务接口")
@RestController
@RequestMapping("/bmzw")
public class BmzwController {
    @Autowired
    private BmzwService bmzwService;
    @Autowired
    private YamlPageUtils yamlPageUtils;


    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<BmzwModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            BmzwModel bmzwModel) throws Exception {
        return bmzwService.findAll(pageNow, yamlPageUtils.getPageSize(), bmzwModel);
    }

    @ApiOperation(value = "根据id查出该条信息")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<BmzwModel> findById(@PathVariable("uuid") String uuid) throws Exception {
        return bmzwService.findById(uuid);
    }


    @ApiOperation(value = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseResult<BmzwModel> update(@RequestBody BmzwModel bmzwModel) throws Exception {
        /* String accid = JWTUtils.getAccId(request);*/
        return bmzwService.update(bmzwModel);
    }

    @ApiOperation(value = "根据id删除")
    @RequestMapping(value = "/del/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<BmzwModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return bmzwService.deleteById(uuid);
    }

    @ApiOperation(value = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<BmzwModel> add(@RequestBody BmzwModel bmzwModel) throws Exception {
        return bmzwService.add(bmzwModel);
    }
}
