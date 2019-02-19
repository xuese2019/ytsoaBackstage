package com.yts.ytsoa.business.jygl.controller;


import com.github.pagehelper.PageInfo;
import com.yts.ytsoa.GlobalExceptionHandler;
import com.yts.ytsoa.business.gdgl.service.GdglService;
import com.yts.ytsoa.business.jygl.model.JyglModel;
import com.yts.ytsoa.business.jygl.model.ResultModel;
import com.yts.ytsoa.business.jygl.service.JyglService;
import com.yts.ytsoa.business.shjl.model.XmshModel;
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

@Api(value = "借阅管理", description = "借阅管理接口")
@RestController
@RequestMapping("/jygl")
public class JyglController {
    @Autowired
    private JyglService jyglService;
    @Autowired
    private GdglService gdglService;
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Value("${file.file-model}")
    private String fileModel;
    @Value("${file.dow-path}")
    private String dowPath;


    /**
     * 分页查询
     */
    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<PageInfo<JyglModel>> findAll(@PathVariable("pageNow") int pageNow, @RequestBody
            JyglModel jyglModel) throws Exception {
        return jyglService.findAll(pageNow, yamlPageUtils.getPageSize(), jyglModel);
    }

    @ApiOperation(value = "根据id条件查询")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<JyglModel> findById(@PathVariable("uuid") String uuid, HttpServletRequest request) throws Exception {
        if (uuid != null) {
            JyglModel model = new JyglModel();
            model.setUuid(uuid);
            return jyglService.findById(model);
        }
        return new ResponseResult<>(false, "查无信息");
    }

    @ApiOperation(value = "根据id进行修改")
    @RequestMapping(value = "/updById", method = RequestMethod.PUT)
    public ResponseResult<JyglModel> updById(@RequestBody JyglModel jyglModel, BindingResult result, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        if (result.hasErrors()) {
            return new ResponseResult<>(false, result.getAllErrors().get(0).getDefaultMessage());
        }
        return jyglService.updById(jyglModel, accid);
    }

    @ApiOperation(value = "excel导出")
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public void excel(@RequestBody JyglModel jyglModel,
                      HttpServletResponse response) throws Exception {
        ResponseResult<String> result = jyglService.excel(fileModel + "JyglModel.xls", dowPath, jyglModel);
        if (result.isSuccess()) {
            new ExcelModelExportUtils().download2(dowPath, result.getData(), response);
        } else {
            new GlobalExceptionHandler().res(response, "下载失败");
        }
    }

    /**
     * 新增借阅管理
     */
    @ApiOperation(value = "新增借阅管理")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<JyglModel> add(@RequestBody JyglModel model, HttpServletRequest request) throws Exception {
        if (model != null) {
            String accId = JWTUtils.getAccId(request);
            model.setJyr(accId);
            return jyglService.add(model);
        }
        return new ResponseResult<>(false, "借阅失败");
    }

    @ApiOperation(value = "借阅审核")
    @RequestMapping(value = "/jysh", method = RequestMethod.PUT)
    public ResponseResult<XmshModel> update(@RequestBody XmshModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        if (model != null) {
            model.setShr(accid);
            return jyglService.update(model);
        }
        return new ResponseResult<>(false, "审核失败");
    }

    @ApiOperation(value = "审核记录")
    @RequestMapping(value = "/findByShjl/{prentid}", method = RequestMethod.GET)
    public ResponseResult<List<ResultModel>> findByShjl(@PathVariable("prentid") String prentid) throws Exception {
        ResponseResult<List<ResultModel>> result = jyglService.findByShjl(prentid);
        if (result != null) {
            return new ResponseResult<>(true, "查询成功", result.getData());
        } else {
            return new ResponseResult<>(false, "没有审核记录");
        }
    }
}
