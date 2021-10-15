package com.markerhub.mapper;

import com.markerhub.entity.WorkorderFields;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.Time;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-09-22
 */

public interface WorkorderFieldsMapper extends BaseMapper<WorkorderFields> {

//    @Select("SELECT count(*)\n" +
//            "FROM WorkOrder\n" +
//            "WHERE CREATEDTIME BETWEEN #{s} AND #{sa} AND  SITEID = #{site};")
@Select("SELECT  isnull(MAX(CASE WHEN REQUESTTYPEID=1 THEN sl END),0) sl1,\n" +
        "    isnull(MAX(CASE WHEN REQUESTTYPEID=2 THEN sl END),0) sl2,\n" +
        "    isnull(MAX(CASE WHEN REQUESTTYPEID=3 THEN sl END),0) sl3\n" +
        "    FROM ( select REQUESTTYPEID,COUNT(*) sl  from WorkOrderStates a,WorkOrder b  where a.WORKORDERID =b.WORKORDERID\n" +
        "    and  (b.CREATEDTIME BETWEEN 1632837860083 AND 1634204812952 AND  b.SITEID = 2) and a.REQUESTTYPEID in (1,2,3) group by REQUESTTYPEID ) cc")
List<Map> counts(@Param("s")long s, @Param("sa")long sa, @Param("site")int site);
        // List<StandardSituationVO> standardSituationList();

}
