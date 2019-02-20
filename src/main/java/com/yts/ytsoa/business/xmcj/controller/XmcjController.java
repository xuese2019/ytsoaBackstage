package com.yts.ytsoa.business.xmcj.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.GlobalExceptionHandler;
import com.yts.ytsoa.business.xmcj.model.XmcjModel;
import com.yts.ytsoa.business.xmcj.model.XmzmcModel;
import com.yts.ytsoa.business.xmcj.service.XmcjService;
import com.yts.ytsoa.business.xmcy.service.XmcyService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.poi.ExcelModelExportUtils;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "项目承接", description = "项目承接接口")
@RestController
@RequestMapping("/xmcj")
public class XmcjController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private XmcjService xmcjService;
    @Autowired
    private XmcyService xmcyService;
    @Value("${file.file-model}")
    private String fileModel;
    @Value("${file.dow-path}")
    private String dowPath;

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<XmcjModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                       @RequestBody XmcjModel xmcjModel, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        return xmcjService.findAll(pageNow, yamlPageUtils.getPageSize(), xmcjModel, accid);
    }

    @ApiOperation(value = "根据id删除")
    @RequestMapping(value = "/del/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<XmcjModel> deleteById(@PathVariable("uuid") String uuid) throws Exception {
        return xmcjService.deleteById(uuid);
    }

    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<List<XmcjModel>> findById(@PathVariable("uuid") String uuid) throws Exception {
        return xmcjService.findById(uuid);
    }

    @ApiOperation(value = "excel导出")
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public void excel(@ModelAttribute("form") XmcjModel xmcjModel,
                      HttpServletResponse response) throws Exception {
        ResponseResult<String> result = xmcjService.excel(fileModel + "xmcjModel.xls", dowPath, xmcjModel);
        if (result.isSuccess()) {
//            调用下载
            new ExcelModelExportUtils().download2(dowPath, result.getData(), response);
        } else {
            new GlobalExceptionHandler().res(response, "下载失败");
        }
    }

    @ApiOperation(value = "项目承接接口")
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public ResponseResult<XmcjModel> updateById(@RequestBody XmcjModel xmcjModel,
                                                BindingResult result,
                                                HttpServletRequest request) throws Exception {
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        xmcjModel.setYwzt(1);
        String accId = JWTUtils.getAccId(request);
        xmcjModel.setXmfzr(accId);
        return xmcjService.updateById(xmcjModel, accId);
    }

    @ApiOperation(value = "根据项目委派的uuid查询项目子名称")
    @RequestMapping(value = "/findXmzmc", method = RequestMethod.GET)
    public ResponseResult<List<XmzmcModel>> findXmzmc(@RequestBody XmzmcModel model) throws Exception {
        if (model != null) {
            return xmcjService.findXmzmc(model);
        }
        return new ResponseResult<>(false, "缺少查询参数，查询失败");
    }
}
