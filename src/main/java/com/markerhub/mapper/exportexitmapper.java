package com.markerhub.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface exportexitmapper  {
//    @Select("select count(*) from WorkOrder_Fields Where UDF_CHAR10='TFTM-APL-20210802-04'")
@Select("select count(*) from WorkOrder_Fields Where UDF_CHAR10=#{ss}")
    Integer ss(@Param("ss") String ss);
    @Select("select count(*) from WorkOrder_Fields Where UDF_CHAR20=#{ss}")
    Integer Gs(@Param("ss") String ss);

}
