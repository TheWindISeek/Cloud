package com.cloud.cloudcomputation.service;

import com.cloud.cloudcomputation.pojo.CloudTask;
import com.cloud.cloudcomputation.pojo.User;

public interface CloudTaskService {
    String execCloudlet(CloudTask cloudTask, User user);
}
