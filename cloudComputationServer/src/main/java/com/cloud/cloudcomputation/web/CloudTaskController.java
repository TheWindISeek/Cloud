package com.cloud.cloudcomputation.web;

import com.alibaba.fastjson.JSON;
import com.cloud.cloudcomputation.pojo.CloudTask;
import com.cloud.cloudcomputation.pojo.User;
import com.cloud.cloudcomputation.service.CloudTaskService;
import org.cloudbus.cloudsim.Cloudlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/Cloudlet")
public class CloudTaskController {

    @Autowired
    CloudTaskService cloudTaskService;

    @RequestMapping("/execCloudlet")
    @ResponseBody
    public String execCloudlet(@RequestBody HashMap<String, Object> hashMap) {
        System.out.println("cloud task");

        String cloudletString = JSON.toJSONString(hashMap.get("cloudlet"));
        String userString = JSON.toJSONString(hashMap.get("user"));
        CloudTask cloudTask = JSON.parseObject(cloudletString, CloudTask.class);
        User user = JSON.parseObject(userString, User.class);
        String result = cloudTaskService.execCloudlet(cloudTask, user);
        return result;
    }
//        public String execCloudlet(@RequestBody HashMap<String, Object> params) {
//        System.out.println("cloud task");
//        CloudTask cloudTask = new CloudTask();
//        for(String key:params.keySet()) {
//            System.out.println(key+" " + params.get(key));
//        }
//        return "cloudtask";
//    }
}
