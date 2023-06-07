# 项目说明

## 环境
前端: element-ui + vue + axios
后端: spring-boot + myBatis
数据库: mysql 
开发工具: IDEA2022.3 专业版 + navicat12 + google98.0.4758.82 + Edge114.0.1823.37
项目构建工具: maven3.8.1
JDK: 1.8
VCS: git2.38.0

前端所有的包放在cloudComputationServer/src/main/resources/static/js
spring-boot, myBatis, mysql版本请参考cloudComputationServer下的 pom.xml文件
数据库使用的是自行搭建的基于阿里云的mysql


# 文件说明

/cloudComputationServer
    该项目下包含了所有的后端逻辑
    /config
        拦截访问配置
    /dao
        数据库访问层
    /pojo
        实体类
    /service
        服务器层
    /util
        数据库 属性设置工具类
    /web
        控制层 接受前端数据
    CloudComputationApplication.java
        启动类

/cloudsim-cloudsim-4.0
    该模块为cloudsim官方包

initCloudComputation.sql
    初始化数据库sql语句

# 运行
找到
cloudComputationServer/src/main/java/com/cloud/cloudcomputation/CloudComputationApplication.java
即可运行

