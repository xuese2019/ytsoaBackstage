package com.yts.ytsoa;


import com.yts.ytsoa.business.bgflbh.service.BgflbhService;
import com.yts.ytsoa.business.bggl.model.BgglModel;
import com.yts.ytsoa.business.bggl.service.BgglService;
import com.yts.ytsoa.business.rgtj.service.RgtjService;
import com.yts.ytsoa.sys.spring.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    private RgtjService rgtjService;
    private BgglService bgglService;
    private BgflbhService bgflbhService;

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

    @Scheduled(cron = "0 0 0 * * ?")
    /*@Scheduled(cron = "0/5 * * * * ?")*/
    public void rgtj() throws Exception {
        try {
            if (rgtjService == null) {
                rgtjService = SpringUtil.getBean(RgtjService.class);
            }
            rgtjService.insertRgtj();
            System.out.println("人工统计中。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    /*@Scheduled(cron = "0/5 * * * * ?")*/
    public void updateGdyxq() throws Exception {
        try {
            if (bgglService == null) {
                bgglService = SpringUtil.getBean(BgglService.class);
            }
            String accid = null;
            bgglService.updateGdyxq(new BgglModel(), accid);
            System.out.println("归档有效期修改中。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    /*@Scheduled(cron = "0/5 * * * * ?")*/
    public void updateFirstBglx() throws Exception {
        try {
            if (bgflbhService == null) {
                bgflbhService = SpringUtil.getBean(BgflbhService.class);
            }
            bgflbhService.updateFirstBglx();
            System.out.println("报告编号1归零中");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    /*@Scheduled(cron = "0/5 * * * * ?")*/
    public void updateSeccondBglx() throws Exception {
        try {
            if (bgflbhService == null) {
                bgflbhService = SpringUtil.getBean(BgflbhService.class);
            }
            bgflbhService.updateSeccondBglx();
            System.out.println("报告编号2归零中");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    /*@Scheduled(cron = "0/5 * * * * ?")*/
    public void updateThirdBglx() throws Exception {
        try {
            if (bgflbhService == null) {
                bgflbhService = SpringUtil.getBean(BgflbhService.class);
            }
            bgflbhService.updateThirdBglx();
            System.out.println("报告编号3归零中");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    /*@Scheduled(cron = "0/5 * * * * ?")*/
    public void updateForthBglx() throws Exception {
        try {
            if (bgflbhService == null) {
                bgflbhService = SpringUtil.getBean(BgflbhService.class);
            }
            bgflbhService.updateForthBglx();
            System.out.println("报告编号4归零中");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    /*@Scheduled(cron = "0/5 * * * * ?")*/
    public void updateFifthBglx() throws Exception {
        try {
            if (bgflbhService == null) {
                bgflbhService = SpringUtil.getBean(BgflbhService.class);
            }
            bgflbhService.updateFifthBglx();
            System.out.println("报告编号5归零中");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}