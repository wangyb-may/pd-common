package com.bysj.wyb.common.mapper;

import com.bysj.wyb.common.entity.Counter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysMapper {
    /**
     * 记录登录信息
     * @param counter
     * @return
     */
    int logCount(Counter counter);
}
