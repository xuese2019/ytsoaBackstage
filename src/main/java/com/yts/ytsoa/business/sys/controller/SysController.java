package com.yts.ytsoa.business.sys.controller;

import com.yts.ytsoa.business.sys.service.SysService;
import com.yts.ytsoa.utils.ResponseResult;
import com.yts.ytsoa.utils.annot.AnnotC;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LD
 * @date:
 * @description:
 */
@AnnotC
@Slf4j
@RestController
@RequestMapping("/sys")
public class SysController {

    @Autowired
    private SysService sysService;

    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/eliminate", method = RequestMethod.GET)
    public ResponseResult<String> eliminate() {
        return sysService.deleteAll();
    }
}
