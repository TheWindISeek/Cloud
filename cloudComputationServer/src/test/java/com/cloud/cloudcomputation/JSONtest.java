package com.cloud.cloudcomputation;

import com.alibaba.fastjson.JSON;
import com.cloud.cloudcomputation.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JSONtest {

    @Test
    public void testJson() {
        User user = new User(1, 4, "12", "132");
        System.out.println(user);
        String s = JSON.toJSONString(user);
        System.out.println(s);
    }
}
