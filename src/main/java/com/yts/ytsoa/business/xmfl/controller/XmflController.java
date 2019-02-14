package com.yts.ytsoa.business.xmfl.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.business.xmfl.model.XmflModel;
import com.yts.ytsoa.business.xmfl.service.XmflService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author LD
 */
@Api(value = "项目分类", description = "项目分类接口")
@RestController
@RequestMapping("/xmfl")
public class XmflController {

    @Autowired
    private YamlPageUtils yamlPageUtils;

    @Autowired
    private XmflService service;

    /*  @RequiresRoles(value = {"admin"})*/
    @RequestMapping(value = "/xmfl", method = RequestMethod.POST)
    public ResponseResult<XmflModel> add(@Valid @RequestBody XmflModel model,
                                         BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        return service.add(model);
    }

    /*   @RequiresRoles(value = {"admin"})*/
    @RequestMapping(value = "/xmfl/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<XmflModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return service.deleteById(uuid);
    }

    /*   @RequiresRoles(value = {"admin"})*/
    @RequestMapping(value = "/xmfl", method = RequestMethod.PUT)
    public ResponseResult<XmflModel> updateById(@Valid @RequestBody XmflModel model,
                                                BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        return service.updateById(model);
    }

    /*  @RequiresRoles(value = {"admin"})*/
    @RequestMapping(value = "/xmfl/{pageNow}", method = RequestMethod.GET)
    public ResponseResult<PageInfo<XmflModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                       @RequestBody XmflModel model) throws Exception {
        return service.findAll(pageNow, yamlPageUtils.getPageSize(), model);
    }

    @RequestMapping(value = "/xmfl", method = RequestMethod.GET)
    public ResponseResult<List<XmflModel>> findAll() throws Exception {
        return service.findAll();
    }

    /*    @RequiresRoles(value = {"admin"})*/
    @RequestMapping(value = "/oneId/{uuid}", method = RequestMethod.GET)
    public ResponseResult<XmflModel> getById(@PathVariable("uuid") String uuid) throws Exception {
        return service.getById(uuid);
    }
}
