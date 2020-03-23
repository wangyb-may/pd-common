package com.bysj.wyb.common.mapper;

import com.bysj.wyb.common.entity.Counter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysMapper {
    int logCount(Counter counter);
}
