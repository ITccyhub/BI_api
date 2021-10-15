package com.markerhub.service.impl;

import com.markerhub.entity.WorkorderFields;
import com.markerhub.mapper.WorkorderFieldsMapper;
import com.markerhub.service.WorkorderFieldsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-09-22
 */
@Service
public class WorkorderFieldsServiceImpl extends ServiceImpl<WorkorderFieldsMapper, WorkorderFields> implements WorkorderFieldsService {
   @Autowired
    private WorkorderFieldsMapper workorderFieldsMapper;
    private String $dateStr;

//    private Integer ccc(){
//       Integer s= workorderFieldsMapper.dff(long ss,long sf);
//        return s;
//    }
//private  Integer ccc(){
//    Integer s= workorderFieldsMapper.dff(int ss,long sf);
//        return s;
//}

    @Override
    public Integer getCount(long i, long i1,int site) {

  return   workorderFieldsMapper.counts(i,i1,site);
    }


    @Override
    public long returnDates(LocalDateTime startOfDay) { // zhuan huan ji yuanshijian
        // $dateStr = date('Y-m-d', time());

      //  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
     //   Date date = sdf.parse(GlobalDate);
        Date date=Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date.toString() + "日期格式是");

        long timeInMillisSinceEpoch = date.getTime();
        long timeInMinutesSinceEpoch = timeInMillisSinceEpoch / TimeUnit.MILLISECONDS.toMinutes(timeInMillisSinceEpoch);
        System.out.println(timeInMillisSinceEpoch + "时间为");
        return timeInMillisSinceEpoch;

    }
    public LocalDateTime getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
       // return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    return endOfDay;
    }

    // 获得某天最小时间 2020-02-17 00:00:00

    public LocalDateTime getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        System.out.println(localDateTime);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        System.out.println(startOfDay);
       return  startOfDay;
        // return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }




}
