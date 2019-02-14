package com.yts.ytsoa.business.shjl.mapper;

import com.yts.ytsoa.business.shjl.mapper.Sql.ShjlSql;
import com.yts.ytsoa.business.shjl.model.XmshModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface XmshMapper {

    @Insert({
            "insert into " + Tables.SHJL_TABLE + "(uuid,prentid,shyj,shjg,shsj,shr)" + "values(replace(uuid(), '-', '')," +
                    "#{xmshModel.prentid} ," +
                    "#{xmshModel.shyj}," +
                    "#{xmshModel.shjg}," +
                    "#{xmshModel.shsj}," +
                    "#{xmshModel.shr})"
    })
    int add(@Param("xmshModel") XmshModel xmshModel) throws SQLException;

    @Select({
            "select * from " + Tables.SHJL_TABLE + " where uuid=#{uuid}"
    })
    XmshModel findById(@Param("uuid") String uuid) throws SQLException;

    @SelectProvider(type = ShjlSql.class, method = "findShjl")
    List<XmshModel> findShjl(@Param("prentid") String prentid) throws SQLException;
}
