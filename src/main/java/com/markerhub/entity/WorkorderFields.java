package com.markerhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("WorkOrder_Fields")
public class WorkorderFields implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("WORKORDERID")
    private Long workorderid;

    @TableField("UDF_CHAR1")
    private String udfChar1;

    @TableField("UDF_DATE1")
    private Long udfDate1;

    @TableField("UDF_DATE2")
    private Long udfDate2;

    @TableField("UDF_DATE3")
    private Long udfDate3;

    @TableField("UDF_DATE4")
    private Long udfDate4;

    @TableField("UDF_CHAR4")
    private String udfChar4;

    @TableField("UDF_CHAR5")
    private String udfChar5;

    @TableField("UDF_LONG1")
    private Long udfLong1;

    @TableField("UDF_CHAR3")
    private String udfChar3;

    @TableField("UDF_CHAR6")
    private String udfChar6;

    @TableField("UDF_DATE5")
    private Long udfDate5;

    @TableField("UDF_DATE6")
    private Long udfDate6;

    @TableField("UDF_DATE7")
    private Long udfDate7;

    @TableField("UDF_CHAR8")
    private String udfChar8;

    @TableField("UDF_CHAR12")
    private String udfChar12;

    @TableField("UDF_CHAR15")
    private String udfChar15;

    @TableField("UDF_CHAR17")
    private String udfChar17;

    @TableField("UDF_CHAR10")
    private String udfChar10;

//    @TableField("UDF_CHAR19")
//    private String udfChar19;

    @TableField("UDF_CHAR20")
    private String udfChar20;

    @TableField("UDF_CHAR21")
    private String udfChar21;

    @TableField("UDF_CHAR22")
    private String udfChar22;

    @TableField("UDF_CHAR23")
    private String udfChar23;

    @TableField("UDF_CHAR24")
    private String udfChar24;

    @TableField("UDF_CHAR25")
    private String udfChar25;

    @TableField("UDF_CHAR26")
    private String udfChar26;

    @TableField("UDF_CHAR27")
    private String udfChar27;

    @TableField("UDF_CHAR28")
    private String udfChar28;

    @TableField("UDF_CHAR29")
    private String udfChar29;

    @TableField("UDF_CHAR30")
    private String udfChar30;

    @TableField("UDF_CHAR31")
    private String udfChar31;

    @TableField("UDF_CHAR18")
    private String udfChar18;

    @TableField("UDF_CHAR32")
    private String udfChar32;

    @TableField("UDF_CHAR33")
    private String udfChar33;

    @TableField("UDF_CHAR13")
    private String udfChar13;

    @TableField("UDF_CHAR16")
    private String udfChar16;

    @TableField("UDF_CHAR34")
    private String udfChar34;

    @TableField("UDF_CHAR36")
    private String udfChar36;

    @TableField("UDF_CHAR37")
    private String udfChar37;

    @TableField("UDF_CHAR38")
    private String udfChar38;

    @TableField("UDF_CHAR39")
    private String udfChar39;

    @TableField("UDF_CHAR2")
    private String udfChar2;

    @TableField("UDF_CHAR7")
    private String udfChar7;

    @TableField("UDF_CHAR9")
    private String udfChar9;

    @TableField("UDF_CHAR11")
    private String udfChar11;

    @TableField("UDF_CHAR14")
    private String udfChar14;

    @TableField("UDF_CHAR35")
    private String udfChar35;

    @TableField("UDF_CHAR40")
    private String udfChar40;

    @TableField("UDF_CHAR44")
    private String udfChar44;

    @TableField("UDF_CHAR45")
    private String udfChar45;

    @TableField("UDF_CHAR49")
    private String udfChar49;

    @TableField("UDF_CHAR41")
    private String udfChar41;

    @TableField("UDF_CHAR47")
    private String udfChar47;

    @TableField("UDF_CHAR48")
    private String udfChar48;

    @TableField("UDF_CHAR46")
    private String udfChar46;

    @TableField("UDF_CHAR42")
    private String udfChar42;

    @TableField("UDF_CHAR43")
    private String udfChar43;

    @TableField("UDF_CHAR51")
    private String udfChar51;

    @TableField("UDF_CHAR52")
    private String udfChar52;

    @TableField("UDF_CHAR53")
    private String udfChar53;

    @TableField("UDF_CHAR54")
    private String udfChar54;

    @TableField("UDF_DOUBLE1")
    private Float udfDouble1;


}
