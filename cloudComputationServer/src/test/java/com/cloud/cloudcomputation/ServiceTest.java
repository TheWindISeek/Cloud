package com.cloud.cloudcomputation;

import com.cloud.cloudcomputation.pojo.User;
import com.cloud.cloudcomputation.service.CloudTaskService;
import com.cloud.cloudcomputation.service.UserService;
import com.cloud.cloudcomputation.service.VmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    CloudTaskService cloudTaskService;
    @Autowired
    VmService vmService;

    @Test
    public void userServiceTest() {
        User user = new User();
        user.setUserName("lucifer");

        System.out.println(userService.login(user));

        user.setUserName("xujiafu");
        user.setUserScore(10);
        user.setUserPassword("lucifer");
        user.setUserPrimary(4);
        System.out.println(userService.register(user));

//        System.out.println(userService.update(user));
    }

    @Test
    public void cloudTaskServiceTest() {

    }

    @Test
    public void vmServiceTest() {

    }
}
