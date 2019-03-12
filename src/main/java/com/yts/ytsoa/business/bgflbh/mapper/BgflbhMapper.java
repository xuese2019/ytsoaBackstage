package com.yts.ytsoa.business.bgflbh.mapper;

import com.yts.ytsoa.business.bgflbh.model.BglxModel;
import com.yts.ytsoa.utils.Tables;
import org.apache.ibatis.annotations.*;


@Mapper
public interface BgflbhMapper {

    @UpdateProvider(type = BglxSql.class, method = "updByBglx")
    int update(@Param("model") BglxModel model);

    @Select({
            "SELECT b.bgbhsjs \n" +
                    "FROM bggl_table b \n" +
                    "WHERE b.bglx=#{bglx} \n" +
                    "ORDER BY b.bgbhsjs DESC\n" +
                    "LIMIT 1 "
    })
    int num(@Param("bglx") int bglx);

    @Select({
            "select bgbh_hz from bglx_table where bglx=#{bglx}"
    })
    int findByBglx(@Param("bglx") int bglx);

    @Select({
            "SELECT b.bgbh_hz as bgbhHz,b.lb\n" +
                    "FROM bglx_table b \n" +
                    "where b.bglx=#{model.bglx}"
    })
    BglxModel find(@Param("model") BglxModel model);


    /**
     * 查出第一种类别的报告编号
     *
     * @return
     */
    @Select({
            "select * from " + Tables.BGLX_TABLE + " b where b.uuid=1"
    })
    BglxModel findFirstLb();

    /**
     * 报告编号归零
     *
     * @return
     */
    @Update({
            "update " + Tables.BGLX_TABLE + " set bgbh_hz=1000 where uuid=1"
    })
    int updateFirstBgbh();


    /**
     * 查出第二种类别的报告编号
     *
     * @return
     */
    @Select({
            "select * from " + Tables.BGLX_TABLE + " b where b.uuid=2"
    })
    BglxModel findSeccondLb();

    /**
     * 报告编号归零
     *
     * @return
     */
    @Update({
            "update " + Tables.BGLX_TABLE + " set bgbh_hz=3000 where uuid=2"
    })
    int updateSeccondBgbh();


    /**
     * 查出第三种类别的报告编号
     *
     * @return
     */
    @Select({
            "select * from " + Tables.BGLX_TABLE + " b where b.uuid=3"
    })
    BglxModel findThirdLb();

    /**
     * 报告编号归零
     *
     * @return
     */
    @Update({
            "update " + Tables.BGLX_TABLE + " set bgbh_hz=5000 where uuid=3"
    })
    int updateThirdBgbh();

    /**
     * 查出第四种类别的报告编号
     *
     * @return
     */
    @Select({
            "select * from " + Tables.BGLX_TABLE + " b where b.uuid=4"
    })
    BglxModel findForthLb();

    /**
     * 报告编号归零
     *
     * @return
     */
    @Update({
            "update " + Tables.BGLX_TABLE + " set bgbh_hz=6000 where uuid=4"
    })
    int updateForthBgbh();

    /**
     * 查出第五种类别的报告编号
     *
     * @return
     */
    @Select({
            "select * from " + Tables.BGLX_TABLE + " b where b.uuid=3"
    })
    BglxModel findFifthLb();

    /**
     * 报告编号归零
     *
     * @return
     */
    @Update({
            "update " + Tables.BGLX_TABLE + " set bgbh_hz=5000 where uuid=3"
    })
    int updateFifthBgbh();
}
