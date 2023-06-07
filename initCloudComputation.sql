create database if not EXISTS CloudComputation;

use CloudComputation;

SET FOREIGN_KEY_CHECKS=0;
/* 云用户表 原谅我管理员和用户不分开*/
drop table if exists User;

create table User (
                      userId int(11) NOT NULL AUTO_INCREMENT,
                      userPrimary int(11),
                      userPassword varchar(16),
                      userName varchar(16) UNIQUE,
                      userScore int(11),
                      primary key (userId)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert
into User(userId, userPrimary, userScore,userPassword, userName)
values (1, 4, 'lucifer', 0,'lucifer'),
       (3, 1, 'lxf', 0, 'lxf');

/*虚拟服务器表*/
drop table if exists Vm;

create table Vm (
    /*虚拟服务器id*/
                    vmid int(11) NOT NULL AUTO_INCREMENT,
    /*每秒多少百万次指令*/
                    mips int(11),
    /*存储大小*/
                    size int(11),
    /*内存大小*/
                    ram int(11),
    /*操作系统*/
                    os varchar(16),
    /*CPU核数*/
                    pesNumber int(11),
    /*vmm的厂商 默认xen吧*/
                    vmm varchar(16),
    /*哪个用户创建的*/
                    userId int(11),
    /*是否开启弹性伸缩*/
                    isAutoScaling int(11),
                    PRIMARY KEY (vmid)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert
into Vm(vmid, mips, size, ram, os, pesNumber, vmm, userId, isAutoScaling)
values (0, 1000, 10000, 512, 'Linux', 1, 'Xen', 1, 1);

/*日志表 1-提交任务 2-服务器故障 3-服务器被干掉了 */
drop table if exists Log;

create table Log (
                     userId int(11) NOT NULL,
                     opType int(11),
                     vmid int(11) NOT NULL,
                     createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--                      primary key (userId, vmid),
                     foreign key (userId) references User(userId)
--      foreign key (vmid) references Vm(vmid)
)DEFAULT CHARSET=utf8;

insert
into Log(userId, opType, vmid)
VALUES (1, 1, 0);

SET FOREIGN_KEY_CHECKS=1;