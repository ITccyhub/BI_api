package com.markerhub.mapper;

import com.markerhub.entity.WorkorderFields;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Time;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-09-22
 */

public interface WorkorderFieldsMapper extends BaseMapper<WorkorderFields> {

    @Select("SELECT count(*)\n" +
            "FROM WorkOrder\n" +
            "WHERE CREATEDTIME BETWEEN #{s} AND #{sa} AND  SITEID = #{site};")
    Integer counts(@Param("s")long s,@Param("sa")long sa,@Param("site")int site);
        // List<StandardSituationVO> standardSituationList();

}
