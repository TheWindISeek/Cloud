package com.cloud.cloudcomputation.util;

import com.cloud.cloudcomputation.dao.VmMapper;
import com.cloud.cloudcomputation.pojo.CloudTask;
import com.cloud.cloudcomputation.pojo.User;
import com.cloud.cloudcomputation.service.impl.VmServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @apiNote 我现在发现好像我理解有偏差 嗯就当不知道
 */
public class CloudUtil {
    private static List<Cloudlet> cloudletList = new ArrayList<>();

    private static Map<Integer, List<com.cloud.cloudcomputation.pojo.Vm> > vmMap = new HashMap<>();

    private static int brokerId = 0;

    private static List<Vm> vmList = new ArrayList<>();
    private static List<Vm> redunList = new ArrayList<>();

    private static DatacenterBroker broker;

    private static double rate = 0.7;

    private static Datacenter datacenter;

    private static int index = 0;
    static {
//        index = 0;
//        datacenter = createDatacenter("data center");
//        broker = createBroker();
//        vmList = new ArrayList<>();
//        CloudSim.init(16, Calendar.getInstance(), true);
    }

    /**
     * Creates the datacenter.
     *
     * @param name the name
     *
     * @return the datacenter
     */
    private static Datacenter createDatacenter(String name) {

        // Here are the steps needed to create a PowerDatacenter:
        // 1. We need to create a list to store
        // our machine
        List<Host> hostList = new ArrayList<Host>();

        // 2. A Machine contains one or more PEs or CPUs/Cores.
        // In this example, it will have only one core.
        List<Pe> peList = new ArrayList<Pe>();

        int mips = 1000;

        // 3. Create PEs and add these into a list.
        peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

        // 4. Create Host with its id and list of PEs and add them to the list
        // of machines
        int hostId = 0;
        int ram = 2048; // host memory (MB)
        long storage = 1000000; // host storage
        int bw = 10000;

        hostList.add(
                new Host(
                        hostId,
                        new RamProvisionerSimple(ram),
                        new BwProvisionerSimple(bw),
                        storage,
                        peList,
                        new VmSchedulerTimeShared(peList)
                )
        ); // This is our machine

        // 5. Create a DatacenterCharacteristics object that stores the
        // properties of a data center: architecture, OS, list of
        // Machines, allocation policy: time- or space-shared, time zone
        // and its price (G$/Pe time unit).
        String arch = "x86"; // system architecture
        String os = "Linux"; // operating system
        String vmm = "Xen";
        double time_zone = 10.0; // time zone this resource located
        double cost = 3.0; // the cost of using processing in this resource
        double costPerMem = 0.05; // the cost of using memory in this resource
        double costPerStorage = 0.001; // the cost of using storage in this
        // resource
        double costPerBw = 0.0; // the cost of using bw in this resource
        LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are not adding SAN
        // devices by now

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem,
                costPerStorage, costPerBw);

        // 6. Finally, we need to create a PowerDatacenter object.
        Datacenter datacenter = null;
        try {
            datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datacenter;
    }

    // We strongly encourage users to develop their own broker policies, to
    // submit vms and cloudlets according
    // to the specific rules of the simulated scenario
    /**
     * Creates the broker.
     *
     * @return the datacenter broker
     */
    private static DatacenterBroker createBroker() {
        DatacenterBroker broker = null;
        try {
            broker = new DatacenterBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }

    /**
     * Prints the Cloudlet objects.
     *
     * @param list list of Cloudlets
     */
    private static void printCloudletList(List<Cloudlet> list) {
        int size = list.size();
        Cloudlet cloudlet;

        String indent = "    ";
        System.out.println();
        System.out.println("========== OUTPUT ==========");
        System.out.println("Cloudlet ID" + indent + "STATUS" + indent
                + "Data center ID" + indent + "VM ID" + indent + "Time" + indent
                + "Start Time" + indent + "Finish Time");

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            cloudlet = list.get(i);
            System.out.print(indent + cloudlet.getCloudletId() + indent + indent);

            if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS) {
                System.out.print("SUCCESS");
                System.out.println(indent + indent + cloudlet.getResourceId()
                        + indent + indent + indent + cloudlet.getVmId()
                        + indent + indent
                        + dft.format(cloudlet.getActualCPUTime()) + indent
                        + indent + dft.format(cloudlet.getExecStartTime())
                        + indent + indent
                        + dft.format(cloudlet.getFinishTime()));
            }
        }
    }


    /**
     * 将给定的cloudtask运行在指定的服务器上
     *
     * @param cloudTask 给定的运行任务
     * @param user
     * @return 输出结果
     */
    public static String runCloudlet(CloudTask cloudTask, User user) {
        CloudSim.init(16, Calendar.getInstance(), true);
        broker = createBroker();
        datacenter = createDatacenter("dataCenter");

        //创建云任务
        System.out.println("进行负载均衡");
        UtilizationModel utilizationModel = cloudTask.isFull() ? new UtilizationModelFull() : new UtilizationModelNull();
        Cloudlet cloudlet1 = new Cloudlet(
                index++,
                (long) (cloudTask.getCloudletLength() * rate),
                (int) (cloudTask.getPerNumber() * rate),
                (long) (cloudTask.getCloudletFileSize() * rate),
                (long) (cloudTask.getCloudletOutputSize() * rate),
                utilizationModel,
                utilizationModel,
                utilizationModel
        );
        Cloudlet cloudlet2 = new Cloudlet(
                index++,
                (long) (cloudTask.getCloudletLength()  * (1-rate)),
                (int) (cloudTask.getPerNumber() * (1-rate)),
                (long) (cloudTask.getCloudletFileSize() * (1-rate)),
                (long) (cloudTask.getCloudletOutputSize() * (1-rate)),
                utilizationModel,
                utilizationModel,
                utilizationModel
        );
        //配置云人物的代理和运行的虚拟机的id
        cloudlet1.setUserId(broker.getId());
        cloudlet1.setVmId(cloudTask.getVmId());
        cloudlet2.setUserId(broker.getId());
        cloudlet2.setVmId(256-cloudTask.getVmId());
        cloudletList.add(cloudlet1);

        //创建对应用户的虚拟机列表
        vmList.clear();
        redunList.clear();
        //这里还应该传入用户的id这样才对
        createVm(broker.getId(), initVmMap(user));

        //将任务和虚拟机都提交给broker
        broker.submitVmList(vmList);
        broker.submitVmList(redunList);
        broker.submitCloudletList(cloudletList);

        //开始仿真
        CloudSim.startSimulation();
        CloudSim.stopSimulation();

        //获取仿真结果
        List<Cloudlet> list = broker.getCloudletReceivedList();

        //使用字节流获取到输出
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        printCloudletList(list);

        System.out.flush();
        System.setOut(old);

        //查看输出的结果
        System.out.println(baos.toString());
        return baos.toString();
    }

    /**
     * 导入vm列表 如果当前的vm还是空的话
     * @param user
     */
    private static List<com.cloud.cloudcomputation.pojo.Vm> initVmMap(User user) {
        SqlSessionFactory sqlSessionFactory = DaoUtil.getSqlSessionFactory();
        SqlSession session =sqlSessionFactory.openSession(true);
        VmMapper vmMapper = session.getMapper(VmMapper.class);
        List<com.cloud.cloudcomputation.pojo.Vm> vms = vmMapper.selectVmFromUser(user);

        session.commit();
        session.close();
        return vms;
    }

    private static void createVm(int brokerId, List<com.cloud.cloudcomputation.pojo.Vm> vms) {
        for(com.cloud.cloudcomputation.pojo.Vm vm: vms) {
            Vm realVm = new Vm(vm.getVmId(),
                    brokerId, vm.getMips(), vm.getPesNumber(),
                    vm.getRam(), 1000, vm.getSize(), vm.getVmm(),
                    new CloudletSchedulerTimeShared());
            Vm reduVm = new Vm(256-vm.getVmId(),
                    brokerId, vm.getMips(), vm.getPesNumber(),
                    vm.getRam(), 1000, vm.getSize(), vm.getVmm(),
                    new CloudletSchedulerTimeShared());

            System.out.println("数据中心当前的服务器" + vm.toString());
            System.out.println("数据中心的另外一台服务器" + reduVm.toString());
            redunList.add(reduVm);
            vmList.add(realVm);
        }
    }

    /**
     * 使某台服务器失效
     * @param vmId
     */
    public static void fault(int vmId) {
        //懒惰失效
    }

    /**
     * 删除某台服务器
     * @param vmId
     */
    public static void delete(int vmId) {
        //懒惰删除
    }

    /**
     * 创建一台服务器
     *
     * 创建的时候就为其新建多建一个服务器 这就是拓展
     * @param vm
     */
    public static void create(com.cloud.cloudcomputation.pojo.Vm vm) {
        List<com.cloud.cloudcomputation.pojo.Vm> vms = vmMap.get(vm.getUserId());
        if(vms == null) {
            vms = new ArrayList<>();
            vms.add(vm);

            vmMap.put(vm.getUserId(), vms);
        } else {
            vms.add(vm);
        }
        System.out.println("添加服务器成功");
    }
}
