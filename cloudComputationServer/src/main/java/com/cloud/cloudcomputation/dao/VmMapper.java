package com.cloud.cloudcomputation.dao;

import com.cloud.cloudcomputation.pojo.User;
import com.cloud.cloudcomputation.pojo.Vm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface VmMapper {

    /**
     * 动态sql插入vm
     * @param vm vm对象
     * @return 是否插入成功
     */
   // @Options(useGeneratedKeys = true, keyProperty = "vmid")
    boolean insertSelective(Vm vm);

    /**
     * 动态sql更新用户
     * @param vm 用户对象
     * @return 是否更新成功
     */
    boolean updateSelective(Vm vm);

    /**
     * 动态sql 查询用户
     * @param vm 用户对象
     * @return 返回一个用户对象
     */
    Vm selectSelective(Vm vm);

    /**
     * 根据用户对象查询其对应的vm列表
     * @param user 用户的属性
     * @return vm列表
     */
    List<Vm> selectVmFromUser(User user);

    List<Vm> selectVmFromUser(int userId);

    /**
     * 删除对应的vm
     * @param vm
     * @return 是否删除成功
     */
    boolean deleteSelective(Vm vm);
}
