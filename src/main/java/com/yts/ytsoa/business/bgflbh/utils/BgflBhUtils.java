package com.yts.ytsoa.business.bgflbh.utils;

import com.yts.ytsoa.business.bgflbh.model.BgflbhModel;
import com.yts.ytsoa.business.bggl.mapper.BgglMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 报告分类编号工具类
 */
public class BgflBhUtils {

    @Autowired
    private static BgglMapper bgglMapper;

    /**
     * 如果返回数据 小于1 则说明数据源出现问题，可能是未配置
     *
     * @param lx    编号类型
     * @param model 编号数据源
     * @return
     */
    public static int xqflbh(int lx, BgflbhModel model) {
        if (model == null) {
//            如果返回的是-1，则数据源为null
            return -1;
        }

        switch (lx) {
            case 1:
                return model.getABh() <= 0 ? 0 : model.getABh() + 1;
            case 2:
                return model.getBBh() <= 0 ? 0 : model.getBBh() + 1;
            case 3:
                return model.getCBh() <= 0 ? 0 : model.getCBh() + 1;
            case 4:
                return model.getDBh() <= 0 ? 0 : model.getDBh() + 1;
            case 5:
                return model.getEBh() <= 0 ? 0 : model.getEBh() + 1;
            default:
                return 0;
        }
    }
}