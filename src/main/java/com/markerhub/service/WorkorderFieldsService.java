package com.markerhub.service;

import com.markerhub.entity.WorkorderFields;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-09-22
 */
public interface WorkorderFieldsService extends IService<WorkorderFields> {

    List<Map> getCount(long i, long i1, int site);

    LocalDateTime getStartOfDay(Date date);

    long returnDates(LocalDateTime startOfDay);

    LocalDateTime getEndOfDay(Date date);
}
