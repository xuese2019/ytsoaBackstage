package com.yts.ytsoa.business.pjsq.controller;

import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.GlobalExceptionHandler;
import com.yts.ytsoa.business.pjsq.model.PjsqModel;
import com.yts.ytsoa.business.pjsq.service.PjsqService;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.poi.ExcelModelExportUtils;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "票据申请", description = "票据申请接口")
@RestController
@RequestMapping("/pjsq")
public class PjsqController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private PjsqService pjsqService;

    @Value("${file.file-model}")
    private String fileModel;
    @Value("${file.dow-path}")
    private String dowPath;


    /**
     * 添加票据申请
     */
    @ApiOperation(value = "添加票据申请")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<PjsqModel> add(@RequestBody PjsqModel pjsqModel, HttpServletRequest request) throws Exception {
        return pjsqService.add(pjsqModel);
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询,票据管理")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<PjsqModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            PjsqModel pjsqModel) throws Exception {
        return pjsqService.findAll(pageNow, yamlPageUtils.getPageSize(), pjsqModel);
    }

    @ApiOperation(value = "条件查询带分页，票据审核")
    @RequestMapping(value = "/find/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<PjsqModel>> find(@PathVariable("pageNow") int pageNow, @RequestBody PjsqModel model) throws Exception {
        return pjsqService.find(pageNow, yamlPageUtils.getPageSize(), model);
    }

    @ApiOperation(value = "excel导出")
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public void excel(@ModelAttribute("form") PjsqModel pjsqModel,
                      HttpServletResponse response) throws Exception {
        ResponseResult<String> result = pjsqService.excel(fileModel + "pjsqModel.xls", dowPath, pjsqModel);
        if (result.isSuccess()) {
//            调用下载
            new ExcelModelExportUtils().download2(dowPath, result.getData(), response);
        } else {
            new GlobalExceptionHandler().res(response, "下载失败");
        }
    }

    @ApiOperation(value = "票据审核")
    @RequestMapping(value = "/pjsh", method = RequestMethod.POST)
    public ResponseResult<XmshModel> pjsh(@RequestBody XmshModel model, HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        if (model != null) {
            model.setShr(accId);
            return pjsqService.shjl(model);
        }
        return new ResponseResult<>(false, "审核失败");
    }

    @ApiOperation(value = "根据id查询详细信息")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<PjsqModel> findById(@PathVariable(value = "uuid") String uuid) throws Exception {
        return pjsqService.findById(uuid);
    }
}
