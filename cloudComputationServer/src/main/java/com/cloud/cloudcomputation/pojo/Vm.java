package com.cloud.cloudcomputation.pojo;



/**
* 
* @TableName Vm
*/
public class Vm {

    /**
    * 
    */
    private Integer vmid;
    /**
    * 
    */
    private Integer mips;
    /**
    * 
    */
    private Integer size;
    /**
    * 
    */
    private Integer ram;
    /**
    * 
    */
    private Integer pesnumber;
    /**
    * 
    */
    private String vmm;

    private String os;
    /**
    * 
    */
    private Integer userid;
    /**
    * 
    */
    private Integer autoscaling;

    @Override
    public String toString() {
        return "Vm{" +
                "vmid=" + vmid +
                ", mips=" + mips +
                ", size=" + size +
                ", ram=" + ram +
                ", pesnumber=" + pesnumber +
                ", vmm='" + vmm + '\'' +
                ", os='" + os + '\'' +
                ", userid=" + userid +
                ", isautoscaling=" + autoscaling +
                '}';
    }

    public Vm(Integer vmid, Integer mips, Integer size, Integer ram, Integer pesnumber, String vmm, String os, Integer userid, Integer isautoscaling) {
        this.vmid = vmid;
        this.mips = mips;
        this.size = size;
        this.ram = ram;
        this.pesnumber = pesnumber;
        this.vmm = vmm;
        this.os = os;
        this.userid = userid;
        this.autoscaling = isautoscaling;
    }

    public Integer getVmId() {
        return vmid;
    }

    public void setVmId(Integer vmid) {
        this.vmid = vmid;
    }

    public Integer getMips() {
        return mips;
    }

    public void setMips(Integer mips) {
        this.mips = mips;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getPesNumber() {
        return pesnumber;
    }

    public void setPesNumber(Integer pesnumber) {
        this.pesnumber = pesnumber;
    }

    public String getVmm() {
        return vmm;
    }

    public void setVmm(String vmm) {
        this.vmm = vmm;
    }

    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userid) {
        this.userid = userid;
    }

    public Integer getIsAutoScaling() {
        return autoscaling;
    }

    public void setIsAutoScaling(Integer autoscaling) {
        this.autoscaling = autoscaling;
    }

    public Vm(Integer vmid, Integer mips, Integer size, Integer ram, Integer pesnumber, String vmm, Integer userid, Integer isautoscaling) {
        this.vmid = vmid;
        this.mips = mips;
        this.size = size;
        this.ram = ram;
        this.pesnumber = pesnumber;
        this.vmm = vmm;
        this.userid = userid;
        this.autoscaling = isautoscaling;
    }

    public Vm() {
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
