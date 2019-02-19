package com.yts.ytsoa.business.shjl.controller;

import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.business.shjl.service.XmshService;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.yamlutils.YamlPageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Api(value = "项目审核记录", description = "项目审核接口")
@RestController
@RequestMapping("/shjl")
public class XmshController {
    @Autowired
    private YamlPageUtils yamlPageUtils;
    @Autowired
    private XmshService xmshService;

    /**
     * 新增审核记录
     *
     * @param xmshModel
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "新增审核记录")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<XmshModel> add(@RequestBody XmshModel xmshModel) throws Exception {
        return xmshService.add(xmshModel);
    }

    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/findById/{uuid}", method = RequestMethod.GET)
    public ResponseResult<XmshModel> findById(@PathVariable("uuid") String uuid) throws Exception {
        return xmshService.findById(uuid);
    }

    @ApiOperation(value = "根据prentid查出审核记录")
    @RequestMapping(value = "/findShjl/{prentid}", method = RequestMethod.GET)
    public ResponseResult<List<XmshModel>> findShjl(@PathVariable("prentid") String prentid) throws Exception {
        return xmshService.findShjl(prentid);
    }

    @ApiOperation(value = "借阅审核")
    @RequestMapping(value = "/jysh", method = RequestMethod.POST)
    public ResponseResult<XmshModel> jysh(@RequestBody XmshModel model, HttpServletRequest request) throws Exception {
        String accid = JWTUtils.getAccId(request);
        return xmshService.jysh(model, accid);
    }
}
