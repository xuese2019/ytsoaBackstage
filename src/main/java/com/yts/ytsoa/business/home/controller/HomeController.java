package com.yts.ytsoa.business.home.controller;

import com.yts.ytsoa.business.qxgl.service.ZzQxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LD
 */
@Api(value = "接口说明", description = "接口说明")
@RestController
public class HomeController {

    @Autowired
    private ZzQxService zzQxService;

    @ApiOperation(value = "接口说明", hidden = true)
    @RequestMapping(value = "/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
}
