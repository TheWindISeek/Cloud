package com.cloud.cloudcomputation.service;

import com.cloud.cloudcomputation.pojo.Vm;

import java.util.List;

public interface VmService {
    List<Vm> requestVmList(int userId);

    boolean updateVm(Vm vm);

    boolean deleteVm(int vmId, int userId);

    boolean faultVm(int vmId, int userId);

    Vm requestVm(Vm vm, int userId);
}
