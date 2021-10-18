package com.markerhub.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.WorkorderFields;
import com.markerhub.service.WorkorderFieldsService;
import com.markerhub.service.impl.WorkorderFieldsServiceImpl;
import com.markerhub.service.impl.exportexit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.sql.Wrapper;
import java.text.ParseException;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-09-22
 */
@RestController
@RequestMapping("/ir")
public class WorkorderFieldsController {
    @Autowired
    WorkorderFieldsService workorderid;
    @Autowired
    com.markerhub.service.impl.exportexit exportexits;


    @GetMapping("/counts/{s}")
    public Result counts(@PathVariable int s) throws ParseException {


        // Integer work= workorderid.getCount(1631693615541L,1631759949575L,4);

        List<Map> list = workorderid.getCount(workorderid.returnDates(workorderid.getStartOfDay(new Date())), workorderid.returnDates(workorderid.getEndOfDay(new Date())), s);
        ArrayList listarry = new ArrayList();
        Map map = null;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
        }
        Collection cc=map.values();
        return Result.succ(cc);
    }
    @GetMapping("/irex{ss}")
    public Result irex(@PathVariable String ss){
        Integer cc=exportexits.ss(ss);
        return Result.succ(cc);
    }



}