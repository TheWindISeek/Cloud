package com.cloud.cloudcomputation.service.impl;

import com.cloud.cloudcomputation.dao.LogMapper;
import com.cloud.cloudcomputation.dao.VmMapper;
import com.cloud.cloudcomputation.pojo.Log;
import com.cloud.cloudcomputation.pojo.User;
import com.cloud.cloudcomputation.pojo.Vm;
import com.cloud.cloudcomputation.service.VmService;
import com.cloud.cloudcomputation.util.CloudUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VmServiceImpl implements VmService {

    @Autowired
    VmMapper vmMapper;
    @Autowired
    LogMapper logMapper;


    /**
     * 获取对应用户id的list vm
     * @param userId 用户id
     * @return
     */
    @Transactional
    @Override
    public List<Vm> requestVmList(int userId) {
        User user = new User();
        user.setUserId(userId);
        return vmMapper.selectVmFromUser(user);//查询对应的用户的机器列表
    }


    /**
     * 更新对应的vm
     * @param vm
     * @return
     */
    @Transactional
    @Override
    public boolean updateVm(Vm vm) {
        return vmMapper.updateSelective(vm);//更新对应云数据库
    }

    /**
     * 删除对应的机器
     * @param vmId 机器id
     * @param userId 用户id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteVm(int vmId, int userId) {
        logMapper.insertSelective(new Log(vmId, userId, 2));
        CloudUtil.delete(vmId);//删除对应云服务器
        return false;
    }

    /**
     * 使某台机器失效
     * @param vmId 机器id
     * @param userId 用户id
     * @return
     */
    @Transactional
    @Override
    public boolean faultVm(int vmId, int userId) {
        logMapper.insertSelective(new Log(vmId, userId, 1));//日志数据库记录
        CloudUtil.fault(vmId);//触发故障转移机制
        return true;
    }

    /**
     * 为用户id 申请一台vm
     * @param vm
     * @param userId
     * @return
     */
    @Override
    public Vm requestVm(Vm vm, int userId) {
        vm.setUserId(userId);
        vmMapper.insertSelective(vm);//其实这里我需要获取到vm的vmid
        CloudUtil.create(vm);
        return vm;
    }
}
