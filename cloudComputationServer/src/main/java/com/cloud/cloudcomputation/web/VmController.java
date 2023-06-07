package com.cloud.cloudcomputation.web;


import com.alibaba.fastjson.JSON;
import com.cloud.cloudcomputation.pojo.Vm;
import com.cloud.cloudcomputation.service.VmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/Vm")
public class VmController {

    @Autowired
    VmService vmService;

    @RequestMapping("/RequestVm")
    @ResponseBody
    public Vm requestVm(@RequestBody HashMap<String, Object> params) {
        System.out.println("vm/requestvm");
        for (String key:params.keySet()) {
            System.out.println(key+" " + params.get(key));
        }
        String vmString = JSON.toJSONString(params.get("vm"));
        String userIdString = JSON.toJSONString(params.get("userId"));
        Vm vm = JSON.parseObject(vmString, Vm.class);
        int userId = JSON.parseObject(userIdString, Integer.class);
        //Vm userId
        return vmService.requestVm(vm, userId);
    }

    @RequestMapping("/RequestVmList")
    @ResponseBody
    public List<Vm> requestVmList(@RequestBody HashMap<String, Object> hashMap) {
        System.out.println("vm/ request vm list");
        System.out.println(hashMap.size());
        int userId = ((Integer) hashMap.get("userId"));
        List<Vm> vms = vmService.requestVmList(userId);
        return vms;
    }

    //其实这个函数根本没用到 看不见我 看不见我
    @RequestMapping("/UpdateVm")
    @ResponseBody
    public boolean updateVm(@RequestBody Vm vm) {
        System.out.println("vm update vm");
        System.out.println(vm);
        return vmService.updateVm(vm);
    }

    @RequestMapping("/DeleteVm")
    @ResponseBody
    public boolean deleteVm(@RequestBody HashMap<String, Object> params) {
        System.out.println("vm/ delete vm");
        for (String key:params.keySet()) {
            System.out.println(key+" " + params.get(key));
        }
        int userId = ((Integer) params.get("userId"));
        int vmId = ((Integer) params.get("vmId"));
        return vmService.deleteVm(vmId, userId);
    }

    @RequestMapping("/FaultVm")
    @ResponseBody
    public boolean faultVm(@RequestBody HashMap<String, Object> params) {
        System.out.println("vm/ fault vm");
        for (String key:params.keySet()) {
            System.out.println(key+" " + params.get(key));
        }
        int userId = ((Integer) params.get("userId"));
        int vmId = ((Integer) params.get("vmId"));
        return vmService.faultVm(vmId, userId);
    }
}
