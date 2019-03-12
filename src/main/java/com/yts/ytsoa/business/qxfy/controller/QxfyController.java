package com.yts.ytsoa.business.qxfy.controller;

import com.yts.ytsoa.business.qxfy.model.QxfyModel;
import com.yts.ytsoa.business.qxfy.service.QxfyService;
import com.yts.ytsoa.business.qxgl.model.QxglModel;
import com.yts.ytsoa.sys.shiro.JWTUtils;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qxfy")
public class QxfyController {

    @Autowired
    private QxfyService service;

    @RequestMapping(value = "/qxfy/{accid}", method = RequestMethod.GET)
    public ResponseResult<List<QxglModel>> findByAccId(@PathVariable("accid") String accid) throws Exception {
        return service.findByAccId(accid);
    }

    @RequestMapping(value = "/qxfy", method = RequestMethod.GET)
    public ResponseResult<List<QxglModel>> findByAccId(HttpServletRequest request) throws Exception {
        String accId = JWTUtils.getAccId(request);
        return service.findByAccId(accId);
    }

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/qxfy", method = RequestMethod.POST)
    public ResponseResult<String> setQx(@RequestBody QxfyModel model) throws Exception {
        return service.setQxByAcc(model);
    }
}
