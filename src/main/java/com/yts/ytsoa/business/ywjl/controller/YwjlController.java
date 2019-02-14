package com.yts.ytsoa.business.ywjl.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.ywjl.model.YwjlModel;
import com.yts.ytsoa.business.ywjl.service.YwjlService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Api(value = "业务交流", description = "业务交流接口")
@RestController
@RequestMapping("/ywjl")
public class YwjlController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private YwjlService ywjlService;

    /**
     * 分页查询
     */
    @ApiOperation(value = "业务分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<YwjlModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            YwjlModel ywjlModel) throws Exception {
        return ywjlService.findAll(pageNow, yamlPageUtils.getPageSize(), ywjlModel);
    }

    /**
     * 新增业务交流
     */
    @ApiOperation(value = "新增业务交流")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<YwjlModel> add(@RequestBody YwjlModel ywjlModel, HttpServletRequest request) throws Exception {
        String dqdlr = JWTUtils.getAccId(request);
        ywjlModel.setFqr(dqdlr);
        ywjlModel.setFqsj(new Date());
        return ywjlService.add(ywjlModel);
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
    public ResponseResult<YwjlModel> delById(@PathVariable("uuid") String uuid) throws Exception {
        /*  String accId = JWTUtils.getAccId(request);*/
        return ywjlService.delById(uuid);
    }
}
