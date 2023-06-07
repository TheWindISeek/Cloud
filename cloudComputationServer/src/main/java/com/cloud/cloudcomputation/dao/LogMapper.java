package com.cloud.cloudcomputation.dao;

import com.cloud.cloudcomputation.pojo.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {

    /**
     * 插入一条新日志
     * @param log 新日志对象
     * @return 插入新日志是否成功
     */
    boolean insertSelective(Log log);
}
