package com.yts.ytsoa.business.xxgl.utils;

import com.yts.ytsoa.business.xxgl.model.XxglModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Slf4j
public class XxglUtils {

    public XxglModel setXx(int zt, String bt, String nr, String fsr, String jsr) {
        XxglModel model1 = new XxglModel();
        model1.setXxlx(zt);
        model1.setXxbt(bt);
        model1.setXxnr(nr == null ? "" : nr);
        model1.setFsr(fsr);
        model1.setJsr(jsr);
        return model1;
    }
}
