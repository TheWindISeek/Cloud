package com.cloud.cloudcomputation.pojo;


import java.io.Serializable;

import java.util.Date;

/**
* 
* @TableName Log
*/
public class Log {

    /**
    * 
    */
    private Integer userid;
    /**
    * 
    */
    private Integer vmid;
    /**
    * 
    */
    private Integer optype;
    /**
    * 
    */
    private Date createtime;

    public Log(int vmId, int userId, int i) {
        this.vmid = vmId;
        this.userid = userId;
        this.optype = i;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getVmid() {
        return vmid;
    }

    public void setVmid(Integer vmid) {
        this.vmid = vmid;
    }

    public Integer getOptype() {
        return optype;
    }

    public void setOptype(Integer optype) {
        this.optype = optype;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Log(Integer userid) {
        this.userid = userid;
    }

    public Log(Integer userid, Integer vmid, Integer optype, Date createtime) {
        this.userid = userid;
        this.vmid = vmid;
        this.optype = optype;
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "userid=" + userid +
                ", vmid=" + vmid +
                ", optype=" + optype +
                ", createtime=" + createtime +
                '}';
    }

    public Log() {
    }
}
