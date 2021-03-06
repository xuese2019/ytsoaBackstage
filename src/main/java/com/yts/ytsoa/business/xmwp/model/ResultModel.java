package com.yts.ytsoa.business.xmwp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yts.ytsoa.business.xmcj.model.XmzmcModel;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultModel extends XmwpModel implements Serializable {
    private String name;
    private String zzjgmc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResultModel(String uuid, @NotBlank(message = "项目名称不能为空") String xmmc, @NotBlank(message = "委托方不能为空") String wtf, @NotBlank(message = "委托方联系人不能为空") String wtflxr, @NotBlank(message = "委托方联系电话不能为空") @Length(min = 11, max = 11, message = "电话最少为11位") String wtflxdh, Date wtsj, @NotBlank(message = "被审计单位不能为空") String bsjdw, @NotBlank(message = "委托人不能为空") String wpr, @NotBlank(message = "承接部门不能为空") String cjbm, @NotBlank(message = "项目负责人不能为空") String xmfzr, @NotBlank(message = "预计费用不能为空，单位：万元") BigDecimal yjsf, int ywzt, Timestamp wpdscsj, String bz, int xmzt, String ywlx, String xmfl, String shr, String fxpg, Date xmkssj, Date xmxcjssj, String xmbzgs, Date bgcjsj, String yglb, int sfqsht, int fpsfkj, int sfsf, int xmshzt, List<XmzmcModel> xmzmcModels, String name, String zzjgmc, int hhrsh, int zkbsh, Date xtsj) {
        super(uuid, xmmc, wtf, wtflxr, wtflxdh, wtsj, bsjdw, wpr, cjbm, xmfzr, yjsf, ywzt, wpdscsj, bz, xmzt, ywlx, xmfl, shr, fxpg, xmkssj, xmxcjssj, xmbzgs, bgcjsj, yglb, sfqsht, fpsfkj, sfsf, xmshzt, xmzmcModels, zkbsh, hhrsh, xtsj);
        this.name = name;
        this.zzjgmc = zzjgmc;
    }

    public ResultModel() {
    }
}
