package com.cloud.cloudcomputation.service.impl;

import com.cloud.cloudcomputation.pojo.CloudTask;
import com.cloud.cloudcomputation.pojo.User;
import com.cloud.cloudcomputation.service.CloudTaskService;
import com.cloud.cloudcomputation.util.CloudUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CloudTaskServiceImpl implements CloudTaskService {

    @Transactional
    @Override
    public String execCloudlet(CloudTask cloudTask, User user) {
        //调用cloud util中的方法
        //将这个cloudlet提交给对应的服务器
        //调用cloudutil中的模拟方法
        //将模拟的结果返回给前端
        String res = CloudUtil.runCloudlet(cloudTask, user);
        return res;
    }
}
