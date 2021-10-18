package com.markerhub.service.impl;

import com.markerhub.mapper.exportexitmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  exportexit {
    @Autowired
    private exportexitmapper texitmapperid;

  public Integer ss(String ss){

      return  texitmapperid.ss(ss);
  }

}
