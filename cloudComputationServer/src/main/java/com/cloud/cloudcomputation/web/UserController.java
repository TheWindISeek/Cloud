package com.cloud.cloudcomputation.web;

import com.alibaba.fastjson.JSON;
import com.cloud.cloudcomputation.pojo.User;
import com.cloud.cloudcomputation.service.UserService;
import com.cloud.cloudcomputation.util.FieldUtils;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody HashMap<String, Object> params) {
        System.out.println("user/ login");
        User user = new User();
        for(String key:params.keySet()) {
            FieldUtils.setAttribute(user, key, params.get(key));
        }
        //User user = new User(1, 4, "lucifer", "lucifer");
        //System.out.println(JSON.toJSONString(user));

        //注意这里有误
        user = userService.login(user);
        System.out.println(user);
        return  JSON.toJSONString(user);
    }

    @RequestMapping("/register")
    @ResponseBody
    public boolean register(@RequestBody HashMap<String, Object> params) {
        System.out.println("user/ register");
        User user = new User();
        for(String key:params.keySet()) {
            FieldUtils.setAttribute(user, key, params.get(key));
        }
        System.out.println(user);
        return userService.register(user);
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody User user) {
        System.out.println("user / update");
//        User user = new User();
//        for(String key:params.keySet()) {
//            FieldUtils.setAttribute(user, key, params.get(key));
//        }
        System.out.println(user);
        return userService.update(user);
    }
}
