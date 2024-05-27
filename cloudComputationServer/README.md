# 数据库设计
数据库总共三张表
当然也可以再多一张表

分别是用户表
虚拟服务器表 反正是1-n 直接合并了算了
日志表
还可以考虑的就是任务表，但是考虑到任务表其实没啥必要 任务是用户想做的任务

```mysql
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
					 primary key (userId, vmid),
					 foreign key (userId) references User(userId)
--      foreign key (vmid) references Vm(vmid)
)DEFAULT CHARSET=utf8;

insert
into Log(userId, opType, vmid)
VALUES (1, 1, 0);

SET FOREIGN_KEY_CHECKS=1;

```


# 前端设计
访问路径
http://localhost:8080/CloudComputation/static/login.html
## 登录页面

其实只有一个用户和密码 但是用户名最好还是唯一的
这样方便我查人了
所以我返回的其实是权限等级 或者直接返回一个用户的对象
根据这个对象中的用户的权限等级进行跳转
然后将这个用户的对象都设置到local storage上面

## 注册页面
注册页面直接返回一个是否成功的标志就行
既然是通过注册页面走的
那么创建的用户的权限默认为1
ID自动生成

## 主页面
主页面肯定是要根据不同的用户权限来做条件渲染的

当权限等级大于4的时候
就会出现一个使服务器故障的页面

否则就不会出现

那么我直接阐述最多的页面

最多的页面左边分为4栏

### 1 服务器
#### 1.1 申请服务器
mips 这里只能选下拉框
size 这里可以随意 单位为G
ram 这里只能选下拉框
pesNumber 这里只能选下拉框
isAutoScaling 这里只能选是或者否
os 这里只能选择下拉框的东西
费用 (mips + ram*0.8 + pesNumber + size*0.05) * isAutoScaling ? 1.5 : 1
确定 =>
	获取信息后
	将这个提交到后端
	具体的对象
	vm : {
		vmId:
		mips:
		size:
		ram: 
		pesNumber:
		isAutoScaling:
		os:
		userId: //当前用户的id
	}

Vm requestVm(Vm)


#### 1.2 现有服务器资源查看
右边以表格的形式展现 
现有的服务器是
vmid mips size ram pesNumber isAutoScaling

添加一个更新按钮 本来是可以做异步的发送 但是我懒得做了
点击更新按钮
向后端发送数据请求

然后会返回一个vmList

List<Vm> requestVmList(int userId)

List<Vm> 

vmList [
	
]

然后会出现2个按钮
分别是
修改
点击之后会弹出一个框 具体的内容和之前申请的时候一样

首先我用之前的内容把表格内容填上
然后把用户的积分给加上
接着用户修改完再进行修改吧
let preUserScore = userScore;

user.userScore = calc(vm);
boolean updateVm(Vm) 依据新的对象更新当前vm

删除
删除就直接将这个服务器干掉了
deleteVm(int userId, int vmId);//在这里我们不考虑当前服务器上是否还运行着任务 巴拉巴拉的这种情况
直接从数据中删掉这个服务器就是了 或者懒惰删除 因为我不想出发外键异常 去修改log表
这个时候我就到log表再插入一下
userId : 当前用户的id
opType : 3 没记错的话
vmId : 当前的服务器


### 2 创建任务并分配 查看结果
#### 2.1 创建新任务
反正就是一个列表
有
cloudletLength //不知道
cloudletFileSize //输入文件的大小
cloudletOutputSize //输出文件的大小
perNumber 需要的核数 //cpu的核数
isFull 是否独占 //这里我们只考虑所有资源全部被占用 和 所有资源全部可以不被占用的情况
vmId 云服务器id //只能从当前的vmListId中得到

确定

点击确定后
string execCloudlet(Cloudlet cloudlet);

返回的string 其实就是他给输出的东西
我只是将输出重定向到了字符串里面

之后这边将结果显示到右边就是了


弹性伸缩即是
有两个任务都选择的是独占
并且申请的是同一台服务器
还是能够返回结果

至于故障转移
就是当我在下面使一个服务器故障后
使用这条服务器跑任务还是正常的

至于负载均衡这个前端就看不到了
纯粹看的是后端
这个在每次申请服务器的时候新建一个服务器的冗余备份就行

### 3 个人信息相关
#### 3.1 个人信息修改
一个简单的页面
就放一下用户的姓名
密码
积分 积分是用来买东西的 这个我就不实现购买了

然后下面有更新按钮
点击提交

user : {
	userId:
	userPrimary:
	userScore:
	userName:
	userPassword:
}

boolean updateUser(User user)
成功就返回修改成功吧
弹出一个框说明修改成功

### 4 管理员相关
#### 4.1 选择服务器故障
其实就是把之前的那个服务器列表的页面搬过来
不过右边还多了一个按钮
就是故障按钮

点击之后就会发送到
boolean faultVm(int vmId);
返回一个是否成功使其故障


# 后端设计

后端设计的关键是如何将cloud和服务器启动结合

web层 把前面的稍微总结一下
	vmController
		Vm requestVm(Vm)
		List<Vm> requestVmList(int userId)
		boolean updateVm(Vm)
		boolean deleteVm(int userId, int vmId)
		boolean faultVm(int userId, int vmId)
	
	CloudtaskController
		string execCloudlet(Cloudlet cloudlet)

	UserController
		boolean updateUser(User user)
		boolean selectUser(User user)
		User registerUser(User user)

dao层需要有的方法是
	user
		insert()
		update()
		select()
	log
		insert()
	vm
		insert()
		update()
		select()
		delete()

pojo
	user
	log
	vm
	cloudtask 似乎已经有了 但是需要一个自定义的

config
	虽然我现在只配置了一个资源的访问 但是很明显我现在是需要自行配置一个基于权限的拦截系统 不想做了


service
	这层才是最复杂的一层
	UserService 直接转发 调用dao 
		updateUser
		selectUser
		registerUser 
	vmService
		insertVm
			insertvm
			调用cloudutil中的insert方法
			为其创建备份
		requestVmList
			查询是userid的所有vm呗
		updateVm
			直接更改
		deleteVm(int userId, int vmId)
			这个就需要在日志数据库中记录一下了
		faultVm(int userId, int vmId)
			同理
			但是这个还需要在cloudutil中调用faultVm方法
			触发故障转移机制
	CloudTaskService
		execCloudlet(Cloudlet cloudlet)
		调用cloudutil中的方法
		将这个cloudlet提交给对应的服务器
		然后调用cloudutil中的模拟方法
		并将模拟的结果返回给前端

Util
	CloudUtil
		首先写几个静态语句块
		把datacenter  broker都创建了
		falutVm
			使当前vm失效 但是同时创建一个新的。当然也可以。。。对的就是那种
		copyToVm
			创建vm的备份 并将着两个服务器当一个使用
		runCloudlet
			如果当前vm被占用
				如果当前配置了弹性伸缩
					创建一个新服务器完成这个任务
				否则
					返回当前服务器被占用
			否则
				将任务7 3 分到服务器上
				开始仿真
				将仿真的结果返回

# 碎碎念

默认任务可拆分
Cloudsim 初始化的时候就可以多写一点东西
默认只有一个数据中心
所有的虚拟服务器都是在他上面建立的可


数据库的设计

首先需要一个用户表

这个用户表应该有的属性是用户id，权限，昵称，密码

接着是日志列表
需要有的信息是
操作者id 操作类型 操作服务器的id
接着是虚拟服务器的列表

需要有的是
vmid mips size ram bw pesNumber vmm dataid userid
分别代表
他属于谁
mips是多少
存储空间
RAM大小
bw不知道
pesNumber不知道
vmm不知道
dataid 表示是哪一个数据中心
userid 表示是哪一个用户使用它


然后默认所有服务器跑在一个云服务器上面
并且这个云服务器上面只有一台具体的数据中心
然后在这个数据中心上我们创建一系列的虚拟服务器

每一个任务应该有的属性是
cloudletId 自动生成
cloudletLength 云计算长度
cloudletFileSize 
cloudletOutputSize 
pesNumber 需要的




然后就是前端页面的设计

登录和注册页面就先不管

管理员反正就一个


核心功能是
	申请实例
	或者说虚拟服务器
	vmid 虚拟机ID
	mips 每秒百万指令数
	size 存储器容量
	ram 内存
	bw  不知道
	pesNumber  //几核CPU
	vmm “Xen”
	dataid 默认1
	userid 当前用户的id

	策略的配置
		弹性伸缩
			当服务器已经被独占任务占用的时候
			还想要使用服务器
			那么就需要使用弹性伸缩策略
			这个时候就为其再创建一个新的服务器
			跑完这个任务就回收
			弹性伸缩的配置应该在服务器的申请里面就提到 我反正是不想再写新的代码了


		故障转移
			使用主动被动式 也就是一台服务器坏掉了 另外一台被动启用
			当指定某台服务器故障时
			（将这台服务器从现有的列表中或者代理中移除）
			虚拟机启用之前负载均衡的另外一台服务器
			并再度开启一个线程，再创建一个服务器。
			然后再度为这条服务器分配任务的时候
			这台服务器还是能正常运行
			这个只能让管理员去配置 正好显示一下不一样


		负载均衡 
			直接默认配置负载均衡
			也就是我直接创建两个虚拟服务器
			同时为他服务
			当任务到达的时候
			如果不为独占
			那么就可以将任务分开
			就当是73开
			然后给服务器进行分配
		
	

分配任务
	创建任务 需要选择是否独占 只能选择全部独占 或者全部不独占
	并为这个任务选择 实例进行运行
	最后会返回一个结果
	如果
	
个人信息相关
	修改自己的密码
	积分增加
管理员相关
	选择服务器故障
	










# Bottom