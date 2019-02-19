package com.yts.ytsoa.business.xmyq.mapper;

import com.yts.ytsoa.business.xmyq.mapper.sql.XmyqSql;
import com.yts.ytsoa.business.xmyq.model.XmyqModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface XmyqMapper {

    @SelectProvider(type = XmyqSql.class, method = "findAllSql")
    List<XmyqModel> findAll(@Param("xmyqModel") XmyqModel xmyqModel) throws SQLException;

    /**
     * 添加
     *
     * @param xmyqModel
     * @return
     * @throws SQLException
     */
    @Insert({
            "insert into " + Tables.XMYQ_TABLE + "(uuid,yqly,txsj,txr,xmmc,xmkssj,xmxcjssj,xmcjr,shr,ywzt)" + "values(replace(uuid(), '-', ''),#{xmyqModel.yqly} ,#{xmyqModel.txsj},#{xmyqModel.txr}" +
                    ",#{xmyqModel.xmmc}" +
                    ",#{xmyqModel.xmkssj}" +
                    ",#{xmyqModel.xmxcjssj}" +
                    ",#{xmyqModel.xmcjr}" +
                    ",#{xmyqModel.shr}" +
                    ",#{xmyqModel.ywzt})"
    })
    int add(@Param("xmyqModel") XmyqModel xmyqModel) throws SQLException;
}
