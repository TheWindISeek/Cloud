package com.cloud.cloudcomputation;

import com.cloud.cloudcomputation.dao.LogMapper;
import com.cloud.cloudcomputation.dao.UserMapper;
import com.cloud.cloudcomputation.dao.VmMapper;
import com.cloud.cloudcomputation.pojo.Log;
import com.cloud.cloudcomputation.pojo.User;
import com.cloud.cloudcomputation.pojo.Vm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class DaoTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    LogMapper logMapper;
    @Autowired
    VmMapper vmMapper;

    @Transactional
    @Test
    public void testUser() {
        User user = new User();
        user.setUserPassword("jzh");
        user.setUserName("jzh");
        user.setUserPrimary(4);
        user.setUserScore(0);

        System.out.println(userMapper.insertSelective(user));
        user = userMapper.selectSelective(user);
        System.out.println(user);

        user.setUserScore(100);
        System.out.println(userMapper.updateSelective(user));
    }

    @Transactional
    @Test
    public void testVm() {
        Vm vm = new Vm();

        vm.setUserId(1);
        vm.setIsAutoScaling(1);
        vm.setMips(100);
        vm.setOs("linux");
        vm.setPesNumber(8);
        vm.setSize(12);
        vm.setVmm("xen");
        vm.setRam(2);

        vmMapper.insertSelective(vm);
        System.out.println();
        System.out.println(vm);
        System.out.println();

        vm.setOs("windows");
        System.out.println(vmMapper.updateSelective(vm));
        System.out.println();

        Vm vm1 = vmMapper.selectSelective(vm);
        System.out.println(vm1);
        System.out.println();


        List<Vm> vms = vmMapper.selectVmFromUser(1);
        for(Vm vm2: vms) {
            System.out.println(vm2);
        }

        System.out.println();

        System.out.println(vmMapper.deleteSelective(vm));


    }

    @Test
    public void testLog() {
        Log log = new Log(1, 1, 1);
        System.out.println(logMapper.insertSelective(log));
    }
}
