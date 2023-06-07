package com.cloud.cloudcomputation.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CloudTask {
    private int cloudletLength;

    private int cloudletFileSize;

    private int cloudletOutputSize;

    private int perNumber;

    @JsonProperty("isFull")
    private boolean isFull;

    private int vmId;

    @Override
    public String toString() {
        return "CloudTask{" +
                "cloudletLength=" + cloudletLength +
                ", cloudletFileSize=" + cloudletFileSize +
                ", cloudletOutputSize=" + cloudletOutputSize +
                ", perNumber=" + perNumber +
                ", isFull=" + isFull +
                ", vmId=" + vmId +
                '}';
    }

    public CloudTask(int cloudletLength, int cloudletFileSize, int cloudletOutputSize, int perNumber, boolean isFull, int vmId) {
        this.cloudletLength = cloudletLength;
        this.cloudletFileSize = cloudletFileSize;
        this.cloudletOutputSize = cloudletOutputSize;
        this.perNumber = perNumber;
        this.isFull = isFull;
        this.vmId = vmId;
    }

    public CloudTask() {
    }

    public int getCloudletLength() {
        return cloudletLength;
    }

    public void setCloudletLength(int cloudletLength) {
        this.cloudletLength = cloudletLength;
    }

    public int getCloudletFileSize() {
        return cloudletFileSize;
    }

    public void setCloudletFileSize(int cloudletFileSize) {
        this.cloudletFileSize = cloudletFileSize;
    }

    public int getCloudletOutputSize() {
        return cloudletOutputSize;
    }

    public void setCloudletOutputSize(int cloudletOutputSize) {
        this.cloudletOutputSize = cloudletOutputSize;
    }

    public int getPerNumber() {
        return perNumber;
    }

    public void setPerNumber(int perNumber) {
        this.perNumber = perNumber;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public int getVmId() {
        return vmId;
    }

    public void setVmId(int vmId) {
        this.vmId = vmId;
    }
}
