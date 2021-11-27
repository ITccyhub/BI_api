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
import java.text.SimpleDateFormat;
import java.time.*;
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
   @GetMapping("/yearsban")
    public Result yearsban() //ir半年 站比
    {

        Calendar cal = Calendar.getInstance();

        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        ArrayList listarry = new ArrayList();
        if(month<7)
        {

            Collections.addAll(listarry,1,2,3,4,5,6);



        }
        else
        {
            Collections.addAll(listarry,7,8,9,10,11,12);

        }
        ArrayList listarrys = new ArrayList();
        for (int i = 0; i < listarry.size(); i++) {
           int s= (int) listarry.get(i);
       //    List<Map> list=workorderid.getyears(workorderid.returnDates(getBeginTime(year,s)),workorderid.returnDates(getEndTime(year,s)) );
         Integer ss = workorderid.getzhanbi(workorderid.returnDates(getBeginTime(year,s)),workorderid.returnDates(getEndTime(year,s)));

            Map<String, Object> map = new HashMap<String, Object>();
         map.put("value",ss);
         map.put("name",s+"月");
listarrys.add(map);

            //    return Result.succ(map);

        }
      return Result.succ(listarrys);

    }




    @GetMapping("/countyear{year}")
     public  Result countyear(@PathVariable int year){ //ir 各年度站比
  //  getBeginTime(year,1);
    List<Map> list=workorderid.getyears(workorderid.returnDates(getBeginTime(year,1)),workorderid.returnDates(getEndTime(year,12)) );
        ArrayList listarry = new ArrayList();
        Map map = null;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
        }
        Collection cc=map.values();
        return Result.succ(cc);

    }
    @GetMapping("/countyears") //全年站比
    public  Result countyear(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
      //  getBeginTime(year,1);
        List<Map> list=workorderid.getyears(workorderid.returnDates(getBeginTime(year,1)),workorderid.returnDates(getEndTime(year,12)) );
        ArrayList listarry = new ArrayList();
        Map map = null;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
        }
        Collection cc=map.values();
        return Result.succ(cc);

    }

    @GetMapping("/counts/{s}")
    public Result counts(@PathVariable int s) throws ParseException { //当天Ir所在公司统计


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
    @GetMapping("/irex{ss}") //ir 导入 isu重复检验
    public Result irex(@PathVariable String ss){
        Integer cc=exportexits.ss(ss);
        return Result.succ(cc);
    }
    @GetMapping("/irexs{ss}") //ir日本 导入
    public Result irexs(@PathVariable String ss){
        Integer cc=exportexits.Gs(ss);
        return Result.succ(cc);
    }
    @GetMapping("/week{week}")
    public Result week(@PathVariable int week) //当前新其排行
    {




        Calendar cal = Calendar.getInstance();
        int weeks = cal.get(Calendar.DAY_OF_WEEK);
//
//        SimpleDateFormat dateFm = new SimpleDateFormat("EE");
//        String currSun = dateFm.format(date);

        List<Map> list = workorderid.getCounts(workorderid.returnDates(getWeekdayStart(week,weeks)),workorderid.returnDates(getWeekdayEnd(week,weeks)));
//        System.out.println("cc"+workorderid.returnDates(getWeekdayStart(week, weeks)));
//        System.out.println("ss"+workorderid.returnDates(getWeekdayEnd(week, weeks)));
        ArrayList listarry = new ArrayList();
        Map map = null;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
        }
        Collection cc=map.values();
        return Result.succ(cc);


    }
    @GetMapping("/top")   //分类
    public Result top()
    {
        List<Map>list = workorderid.gets();


        return Result.succ(list);
    }

    @GetMapping("/pepotime")//保守公时
    public Result pepotime()
    {
        Calendar cal = Calendar.getInstance();

        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
List<Map> List =workorderid.pepotime(workorderid.returnDates(getBeginTime(year,month)), workorderid.returnDates(getEndTime(year,month)));
        return Result.succ(List);
    }




    public LocalDateTime getWeekdayStart(int week,int weeks)
    {

        if(weeks==1)
        {

                switch (week)
                {case 1:return workorderid.getStartOfDay(new Date());
                    case 2: return workorderid.getStartOfDay(getDateByDay(6));  //shang xin qi 2
                    case 3: return workorderid.getStartOfDay(getDateByDay(5));
                    case 4: return workorderid.getStartOfDay(getDateByDay(4));
                    case 5: return workorderid.getStartOfDay(getDateByDay(3));
                    case 6: return workorderid.getStartOfDay(getDateByDay(2));
                    case 7: return workorderid.getStartOfDay(getDateByDay(1));


                }



        }
        else if (weeks==2)
        {

            switch (week)
            {case 2:return workorderid.getStartOfDay(new Date());
                case 3: return workorderid.getStartOfDay(getDateByDay(6));  //shang xin qi 2
                case 4: return workorderid.getStartOfDay(getDateByDay(5));
                case 5: return workorderid.getStartOfDay(getDateByDay(4));
                case 6: return workorderid.getStartOfDay(getDateByDay(3));
                case 7: return workorderid.getStartOfDay(getDateByDay(2));
                case 1: return workorderid.getStartOfDay(getDateByDay(1));


            }


        }
        else if (weeks==3)
        {

            switch (week)
            {case 3:return workorderid.getStartOfDay(new Date());
                case 4: return workorderid.getStartOfDay(getDateByDay(6));  //shang xin qi 2
                case 5: return workorderid.getStartOfDay(getDateByDay(5));
                case 6: return workorderid.getStartOfDay(getDateByDay(4));
                case 7: return workorderid.getStartOfDay(getDateByDay(3));
                case 1: return workorderid.getStartOfDay(getDateByDay(2));
                case 2: return workorderid.getStartOfDay(getDateByDay(1));


            }


        }
        else if (weeks==4)
        {

            switch (week)
            {case 4:return workorderid.getStartOfDay(new Date());
                case 5: return workorderid.getStartOfDay(getDateByDay(6));  //shang xin qi 2
                case 6: return workorderid.getStartOfDay(getDateByDay(5));
                case 7: return workorderid.getStartOfDay(getDateByDay(4));
                case 1: return workorderid.getStartOfDay(getDateByDay(3));
                case 2: return workorderid.getStartOfDay(getDateByDay(2));
                case 3: return workorderid.getStartOfDay(getDateByDay(1));


            }


        }
        else if (weeks==5)
        {

            switch (week)
            {case 5:return workorderid.getStartOfDay(new Date());
                case 6: return workorderid.getStartOfDay(getDateByDay(6));  //shang xin qi 2
                case 7: return workorderid.getStartOfDay(getDateByDay(5));
                case 1: return workorderid.getStartOfDay(getDateByDay(4));
                case 2: return workorderid.getStartOfDay(getDateByDay(3));
                case 3: return workorderid.getStartOfDay(getDateByDay(2));
                case 4: return workorderid.getStartOfDay(getDateByDay(1));


            }


        }
        else if (weeks==6)
        {

            switch (week)
            {case 6:return workorderid.getStartOfDay(new Date());
                case 7: return workorderid.getStartOfDay(getDateByDay(6));  //shang xin qi 2
                case 1: return workorderid.getStartOfDay(getDateByDay(5));
                case 2: return workorderid.getStartOfDay(getDateByDay(4));
                case 3: return workorderid.getStartOfDay(getDateByDay(3));
                case 4: return workorderid.getStartOfDay(getDateByDay(2));
                case 5: return workorderid.getStartOfDay(getDateByDay(1));


            }


        }
        else if (weeks==7)
        {

            switch (week)
            {case 7:return workorderid.getStartOfDay(new Date());
                case 1: return workorderid.getStartOfDay(getDateByDay(6));  //shang xin qi 2
                case 2: return workorderid.getStartOfDay(getDateByDay(5));
                case 3: return workorderid.getStartOfDay(getDateByDay(4));
                case 4: return workorderid.getStartOfDay(getDateByDay(3));
                case 5: return workorderid.getStartOfDay(getDateByDay(2));
                case 6: return workorderid.getStartOfDay(getDateByDay(1));


            }


        }
        return null;

    }
    public LocalDateTime getWeekdayEnd(int week,int weeks)
    {
        if(weeks==1)
        {

            switch (week)
            {case 1:return workorderid.getEndOfDay(new Date());
                case 2: return workorderid.getEndOfDay(getDateByDay(6));  //shang xin qi 2
                case 3: return workorderid.getEndOfDay(getDateByDay(5));
                case 4: return workorderid.getEndOfDay(getDateByDay(4));
                case 5: return workorderid.getEndOfDay(getDateByDay(3));
                case 6: return workorderid.getEndOfDay(getDateByDay(2));
                case 7: return workorderid.getEndOfDay(getDateByDay(1));


            }



        }
        else if (weeks==2)
        {

            switch (week)
            {case 2:return workorderid.getEndOfDay(new Date());
                case 3: return workorderid.getEndOfDay(getDateByDay(6));//shang xin qi 2
                case 4: return workorderid.getEndOfDay(getDateByDay(5));
                case 5: return workorderid.getEndOfDay(getDateByDay(4));
                case 6: return workorderid.getEndOfDay(getDateByDay(3));
                case 7: return workorderid.getEndOfDay(getDateByDay(2));
                case 1: return workorderid.getEndOfDay(getDateByDay(1));


            }


        }
        else if (weeks==3)
        {

            switch (week)
            {case 3:return workorderid.getEndOfDay(new Date());
                case 4: return workorderid.getEndOfDay(getDateByDay(6));//shang xin qi 2
                case 5: return workorderid.getEndOfDay(getDateByDay(5));
                case 6: return workorderid.getEndOfDay(getDateByDay(4));
                case 7: return workorderid.getEndOfDay(getDateByDay(3));
                case 1: return workorderid.getEndOfDay(getDateByDay(2));
                case 2: return workorderid.getEndOfDay(getDateByDay(1));


            }


        }
        else if (weeks==4)
        {

            switch (week)
            {case 4:return workorderid.getEndOfDay(new Date());
                case 5: return workorderid.getEndOfDay(getDateByDay(6));//shang xin qi 2
                case 6: return workorderid.getEndOfDay(getDateByDay(5));
                case 7: return workorderid.getEndOfDay(getDateByDay(4));
                case 1: return workorderid.getEndOfDay(getDateByDay(3));
                case 2: return workorderid.getEndOfDay(getDateByDay(2));
                case 3: return workorderid.getEndOfDay(getDateByDay(1));


            }


        }
        else if (weeks==5)
        {

            switch (week)
            {case 5:return workorderid.getEndOfDay(new Date());
                case 6: return workorderid.getEndOfDay(getDateByDay(6));//shang xin qi 2
                case 7: return workorderid.getEndOfDay(getDateByDay(5));
                case 1: return workorderid.getEndOfDay(getDateByDay(4));
                case 2: return workorderid.getEndOfDay(getDateByDay(3));
                case 3: return workorderid.getEndOfDay(getDateByDay(2));
                case 4: return workorderid.getEndOfDay(getDateByDay(1));


            }


        }
        else if (weeks==6)
        {

            switch (week)
            {case 6:return workorderid.getEndOfDay(new Date());
                case 7: return workorderid.getEndOfDay(getDateByDay(6));//shang xin qi 2
                case 1: return workorderid.getEndOfDay(getDateByDay(5));
                case 2: return workorderid.getEndOfDay(getDateByDay(4));
                case 3: return workorderid.getEndOfDay(getDateByDay(3));
                case 4: return workorderid.getEndOfDay(getDateByDay(2));
                case 5: return workorderid.getEndOfDay(getDateByDay(1));


            }


        }
        else if (weeks==7)
        {

            switch (week)
            {case 7:return workorderid.getEndOfDay(new Date());
                case 1: return workorderid.getEndOfDay(getDateByDay(6));//shang xin qi 2
                case 2: return workorderid.getEndOfDay(getDateByDay(5));
                case 3: return workorderid.getEndOfDay(getDateByDay(4));
                case 4: return workorderid.getEndOfDay(getDateByDay(3));
                case 5: return workorderid.getEndOfDay(getDateByDay(2));
                case 6: return workorderid.getEndOfDay(getDateByDay(1));


            }


        }
        return null;

    }

    public static Date getDateByDay(int day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -day);
      //  System.out.println(DateUtils.date2String("yyyy-MM-dd", cal.getTime()));
   return cal.getTime();
    }

    public static LocalDateTime getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        System.out.println(startOfDay);
     //   ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));
return  startOfDay;
       // return Date.from(zonedDateTime.toInstant());
    }

    public static LocalDateTime getEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
      //  ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        //return Date.from(zonedDateTime.toInstant());
   return localDateTime;
    }



}