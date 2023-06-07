package com.cloud.cloudcomputation.dao;

import com.cloud.cloudcomputation.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 动态sql插入用户
     * @param user 用户对象
     * @return 是否插入成功
     */
    boolean insertSelective(User user);

    /**
     * 动态sql更新用户
     * @param user 用户对象
     * @return 是否更新成功
     */
    boolean updateSelective(User user);

    /**
     * 动态sql 查询用户
     * @param user 用户对象
     * @return 返回一个用户对象
     */
    User selectSelective(User user);
}
