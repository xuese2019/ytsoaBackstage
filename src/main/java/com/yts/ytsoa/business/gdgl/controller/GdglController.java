package com.yts.ytsoa.business.gdgl.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.gdgl.model.GdglModel;
import com.yts.ytsoa.business.gdgl.query.GdglQueryModel;
import com.yts.ytsoa.business.gdgl.result.ResultModel;
import com.yts.ytsoa.business.gdgl.service.GdglService;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "归档管理", description = "归档管理接口")
@RestController
@RequestMapping("/gdgl")
public class GdglController {
    @Autowired
    private GdglService gdglService;
    @Autowired
    private YamlPageUtils yamlPageUtils;

    @ApiOperation(value = "添加一条归档申请")
    @RequestMapping(value = "/addGdsq", method = RequestMethod.POST)
    public ResponseResult<GdglModel> addGdgl(@RequestBody GdglModel model) throws Exception {
        return gdglService.addGdgl(model);
    }

    @ApiOperation(value = "条件查询带分页")
    @RequestMapping(value = "/find/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<GdglModel>> find(@PathVariable("pageNow") int pageNow, @RequestBody GdglModel model) throws Exception {
        return gdglService.find(pageNow, yamlPageUtils.getPageSize(), model);
    }

    @ApiOperation(value = "查询所有归档申请记录，条件分页")
    @RequestMapping(value = "/findGdsh/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<ResultModel>> findGdsh(@PathVariable("pageNow") int pageNow, @RequestBody GdglQueryModel model) throws Exception {
        return gdglService.findGdsh(pageNow, yamlPageUtils.getPageSize(), model);
    }

    @ApiOperation(value = "根据id修改")
    @RequestMapping(value = "/updById", method = RequestMethod.PUT)
    public ResponseResult<GdglModel> updById(@RequestBody GdglModel gdglModel, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        return gdglService.updById(gdglModel);
    }

    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<GdglModel> findById(@PathVariable("uuid") String uuid) throws Exception {
        return gdglService.findById(uuid);
    }

    @ApiOperation(value = "归档审核")
    @RequestMapping(value = "/gdsh", method = RequestMethod.POST)
    public ResponseResult<XmshModel> update(@RequestBody XmshModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        model.setShr(accid);
        return gdglService.update(model);
    }

    @ApiOperation(value = "根据档案名称查找")
    @RequestMapping(value = "/findByDamc", method = RequestMethod.GET)
    public ResponseResult<List<GdglModel>> findByDamc(@RequestBody GdglModel model) throws Exception {
        return gdglService.findByDamc(model);
    }
}
