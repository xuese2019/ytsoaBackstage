package com.yts.ytsoa;


import com.yts.ytsoa.business.gzrz.model.GzrzModel;
import com.yts.ytsoa.business.gzrz.service.GzrzService;
import com.yts.ytsoa.business.gzrz.service.Impl.GzrzServiceImpl;
import com.yts.ytsoa.business.xmcy.service.XmcyService;
import com.yts.ytsoa.business.xxgl.service.XxglService;
import com.yts.ytsoa.business.xxgl.service.impl.XxglServiceImpl;
import com.yts.ytsoa.business.xxgl.utils.XxglUtils;
import com.yts.ytsoa.sys.spring.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 * @Component 作用于类上
 */
@Component
public class Scheduleds {

    @Autowired(required = false)
    private XxglService xxglService;
    private GzrzService gzrzService;

  /*  @Scheduled(cron = "0 0 11 * * ?") //每天00点0分0秒执行
    public void meals() {
        try {
            String format = new SimpleDateFormat("YYYY-mm-DD HH:MM:ss").format(System.currentTimeMillis());
            if (xxglService == null) {
                xxglService = SpringUtil.getBean(XxglServiceImpl.class);
            }
            xxglService.save(new XxglUtils().setXx(6,
                    "中午饭",
                    "现在是" + format + "该定午饭了",
                    "3",
                    "all"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

 /*   @Scheduled(cron = "0 0 0 * * ?")// 每天凌晨统计
    public void rgtj(resultModel model) throws Exception {
        String format = new SimpleDateFormat("YYYY-mm-DD HH:MM:ss").format(System.currentTimeMillis());
        if (xmcyService != null) {
            xmcyService= SpringUtil.getBean(XmcyServiceImpl.class);
        }
        xmcyService.rgtj(1, 10000, model);
    }*/

    @Scheduled(cron = "0 0 * * * ?")
    public void rgtj() throws Exception {
        if (gzrzService != null) {
            gzrzService = SpringUtil.getBean(GzrzServiceImpl.class);
        }
        GzrzModel gzrzModel = new GzrzModel();
        gzrzService.rgtj(1,100,gzrzModel);
        System.out.println("人工统计中。。。");
    }
}
