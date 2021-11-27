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
//    @Select("SELECT  isnull(MAX(CASE WHEN REQUESTTYPEID=1 THEN sl END),0) sl1,\n" +
//            "    isnull(MAX(CASE WHEN REQUESTTYPEID=2 THEN sl END),0) sl2,\n" +
//            "    isnull(MAX(CASE WHEN REQUESTTYPEID=3 THEN sl END),0) sl3\n" +
//            "    FROM ( select REQUESTTYPEID,COUNT(*) sl  from WorkOrderStates a,WorkOrder b  where a.WORKORDERID =b.WORKORDERID\n" +
//            "    and  (b.CREATEDTIME BETWEEN #{s} AND #{sa} AND  b.SITEID = #{site}) and a.REQUESTTYPEID in (1,2,3) group by REQUESTTYPEID ) cc")
//    List<Map> counts(@Param("s") long s, @Param("sa") long sa, @Param("site") int site);
    // List<StandardSituationVO> standardSituationList();
    @Select("SELECT  isnull(MAX(CASE WHEN REQUESTTYPEID=1 THEN sl END),0) sl1,\n" +
            "    isnull(MAX(CASE WHEN REQUESTTYPEID=2 THEN sl END),0) sl2,\n" +
            "    isnull(MAX(CASE WHEN REQUESTTYPEID=3 THEN sl END),0) sl3\n" +
            "    FROM ( select REQUESTTYPEID,COUNT(*) sl  from WorkOrderStates a,WorkOrder b,WorkOrder_Fields c where a.WORKORDERID =b.WORKORDERID\n" +
            "  and a.WORKORDERID =c.WORKORDERID  and  (c.UDF_DATE2 BETWEEN #{s} AND #{sa} AND  b.SITEID = #{site}) and a.REQUESTTYPEID in (1,2,3) group by REQUESTTYPEID ) cc")
    List<Map> counts(@Param("s") long s, @Param("sa") long sa, @Param("site") int site);


    @Select("SELECT  isnull(MAX(CASE WHEN REQUESTTYPEID=1 THEN sl END),0) sl1,\n" +
            "    isnull(MAX(CASE WHEN REQUESTTYPEID=2 THEN sl END),0) sl2,\n" +
            "    isnull(MAX(CASE WHEN REQUESTTYPEID=3 THEN sl END),0) sl3\n" +
            "    FROM ( select REQUESTTYPEID,COUNT(*) sl  from WorkOrderStates a,WorkOrder b, WorkOrder_Fields c where a.WORKORDERID =b.WORKORDERID\n" +
            "  and a.WORKORDERID =c.WORKORDERID  and  (c.UDF_DATE2 BETWEEN #{s} AND #{sa}) and a.REQUESTTYPEID in (1,2,3) group by REQUESTTYPEID ) cc")
    List<Map> count(@Param("s") long s, @Param("sa") long sa);
    // List<StandardSituationVO> standardSituationList();

    @Select("select top 10 VALUE,wx,gz,ly,wx+gz+ly hz  from (\n" +
            "select  VALUE,max(CASE REQUESTTYPEID WHEN '1' THEN sll ELSE 0 end)wx,max(CASE REQUESTTYPEID WHEN '2' THEN sll ELSE 0 end)gz,max(CASE REQUESTTYPEID WHEN '3' THEN sll ELSE 0 end)ly\n" +
            " from (\n" +
            "select VALUE ,REQUESTTYPEID ,SUM(sl)sll from (select  VALUE from  UDF_PickListValues where  UDF_PickListValues.PickListID>=1503 and UDF_PickListValues.PickListID<=1630) as dd ,\n" +
            "(select REQUESTTYPEID,c.UDF_CHAR57+';' as xtm,COUNT(*) sl  from WorkOrderStates a,WorkOrder b, WorkOrder_Multi_Fields c  where a.WORKORDERID =b.WORKORDERID and a.WORKORDERID=c.WORKORDERID and c.UDF_CHAR57 is not NUll\n" +
            " and a.REQUESTTYPEID in (1,2,3) group by REQUESTTYPEID,c.UDF_CHAR57) as dde where  CHARINDEX (VALUE+';',dde.xtm )>0  and  xtm is not null group by VALUE ,REQUESTTYPEID )eeee group by VALUE )aaaaa order by  wx+gz+ly  desc")
    List<Map> strs();

//     @Select("SELECT COUNT(*) FROM WorkOrder WHERE CREATEDTIME BETWEEN #{s} AND #{sa} ;")
//    Integer zhanbi(@Param("s") long s, @Param("sa") long sa);
    @Select("SELECT COUNT(*) FROM WorkOrder_Fields WHERE UDF_DATE2 BETWEEN #{s} AND #{sa} ;")
    Integer zhanbi(@Param("s") long s, @Param("sa") long sa);

// @Select("select  组,max(CASE 请求类别 WHEN 'Incident' THEN 时间花费 ELSE 0 end)故障,  max(CASE 请求类别 WHEN 'Inquiry' THEN 时间花费 ELSE 0 end)问询, max(CASE 请求类别 WHEN 'Request' THEN 时间花费 ELSE 0 end)依赖 from \n" +
//         "(SELECT qd.QUEUENAME AS 组, \n" +
//         " rtdef.NAME AS 请求类别, sum(ROUND(((ct.TIMESPENT/1000)/3600),1)) AS 时间花费 FROM WorkOrder wo LEFT JOIN WorkOrderToCharge wotoc ON wo.WORKORDERID=wotoc.WORKORDERID LEFT JOIN ChargesTable ct ON wotoc.CHARGEID=ct.CHARGEID LEFT JOIN SDUser rcti ON ct.TECHNICIANID=rcti.USERID \n" +
//         " LEFT JOIN AaaUser rctd ON rcti.USERID=rctd.USER_ID LEFT JOIN WorkOrderStates wos ON wo.WORKORDERID=wos.WORKORDERID \n" +
//         " LEFT JOIN RequestTypeDefinition rtdef ON wos.REQUESTTYPEID=rtdef.REQUESTTYPEID LEFT JOIN WorkOrder_Queue woq ON wo.WORKORDERID=woq.WORKORDERID \n" +
//         " LEFT JOIN QueueDefinition qd ON woq.QUEUEID=qd.QUEUEID WHERE (wo.ISPARENT='1') and ct.CREATEDTIME <>'' and (ct.CREATEDTIME>#{s} and ct.CREATEDTIME<#{sa}) and  qd.QUEUENAME<>''  group by qd.QUEUENAME ,rtdef.NAME )aa group by 组")
//    List<Map> pepotime(@Param("s")long s, @Param("sa") long sa);


    @Select("select  组,max(CASE 请求类别 WHEN 'Incident' THEN 时间花费 ELSE 0 end)故障,  max(CASE 请求类别 WHEN 'Inquiry' THEN 时间花费 ELSE 0 end)问询, max(CASE 请求类别 WHEN 'Request' THEN 时间花费 ELSE 0 end)依赖 from \n" +
        "(SELECT qd.QUEUENAME AS 组, \n" +
        " rtdef.NAME AS 请求类别, sum(ROUND(((ct.TIMESPENT/1000)/3600),1)) AS 时间花费 FROM WorkOrder wo LEFT JOIN WorkOrderToCharge wotoc ON wo.WORKORDERID=wotoc.WORKORDERID LEFT JOIN ChargesTable ct ON wotoc.CHARGEID=ct.CHARGEID LEFT JOIN SDUser rcti ON ct.TECHNICIANID=rcti.USERID \n" +
        " LEFT JOIN AaaUser rctd ON rcti.USERID=rctd.USER_ID LEFT JOIN WorkOrderStates wos ON wo.WORKORDERID=wos.WORKORDERID \n" +
        " LEFT JOIN RequestTypeDefinition rtdef ON wos.REQUESTTYPEID=rtdef.REQUESTTYPEID LEFT JOIN WorkOrder_Queue woq ON wo.WORKORDERID=woq.WORKORDERID \n" +
        " LEFT JOIN QueueDefinition qd ON woq.QUEUEID=qd.QUEUEID LEFT JOIN WorkOrder_Fields WF ON wo.WORKORDERID=WF.WORKORDERID            WHERE (wo.ISPARENT='1') and ct.CREATEDTIME <>'' and (WF.UDF_DATE2>#{s} and WF.UDF_DATE2<#{sa}) and  qd.QUEUENAME<>''  group by qd.QUEUENAME ,rtdef.NAME )aa group by 组")
    List<Map> pepotime(@Param("s")long s, @Param("sa") long sa);
}