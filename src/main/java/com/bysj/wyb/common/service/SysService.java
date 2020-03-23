package com.bysj.wyb.common.service;

import com.bysj.wyb.common.entity.Counter;
import com.bysj.wyb.common.mapper.SysMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SysService {

    @Resource
    SysMapper sysMapper;

    public void logCount(String uid){
        Counter counter=new Counter();
        counter.setUid(uid);
        String logTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        counter.setLogTime(logTime);
        sysMapper.logCount(counter);
    }
}
