package com.yts.ytsoa.business.xmjz.mapper;

import com.yts.ytsoa.business.xmjz.mapper.sql.XmjzSql;
import com.yts.ytsoa.business.xmjz.modedl.XmjzModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface XmjzMapper {
    /**
     * 查询所有
     *
     * @param xmjzModel
     * @return
     * @throws SQLException
     */
    @SelectProvider(type = XmjzSql.class, method = "findAllSql")
    List<XmjzModel> findAll(@Param("xmjzModel") XmjzModel xmjzModel) throws SQLException;

    /**
     * 添加
     *
     * @param xmjzModel
     * @return
     * @throws SQLException
     */
    @Insert({
            "insert into " + Tables.XMJZ_TABLE + "(uuid,rq,mzjz,xmid)" + "values(replace(uuid(), '-', ''),#{xmjzModel.rq} ,#{xmjzModel.mzjz},#{xmjzModel.xmid})"
    })
    int add(@Param("xmjzModel") XmjzModel xmjzModel) throws SQLException;
/*    @SelectProvider(type = XmjzSql.class, method = "findById")
    XmwpModel findById(@Param("xmwpModel") XmwpModel xmwpModel)throws SQLException;*/
}
