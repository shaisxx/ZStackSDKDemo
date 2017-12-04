package com.shtd.zstack.cloudresource;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import org.zstack.sdk.CloneVmInstanceAction;
import org.zstack.sdk.CloneVmInstanceInventory;
import org.zstack.sdk.CloneVmInstanceResults;
import org.zstack.sdk.CreateVmInstanceAction;
import org.zstack.sdk.DestroyVmInstanceAction;
import org.zstack.sdk.ExpungeVmInstanceAction;
import org.zstack.sdk.GetInterdependentL3NetworksImagesAction;
import org.zstack.sdk.GetVmCapabilitiesAction;
import org.zstack.sdk.GetVmConsolePasswordAction;
import org.zstack.sdk.GetVmMigrationCandidateHostsAction;
import org.zstack.sdk.GetVmQgaAction;
import org.zstack.sdk.HostInventory;
import org.zstack.sdk.MigrateVmAction;
import org.zstack.sdk.PauseVmInstanceAction;
import org.zstack.sdk.QueryVmInstanceAction;
import org.zstack.sdk.RebootVmInstanceAction;
import org.zstack.sdk.RecoverVmInstanceAction;
import org.zstack.sdk.ReimageVmInstanceAction;
import org.zstack.sdk.ResumeVmInstanceAction;
import org.zstack.sdk.SetVmConsolePasswordAction;
import org.zstack.sdk.SetVmHostnameAction;
import org.zstack.sdk.StartVmInstanceAction;
import org.zstack.sdk.StopVmInstanceAction;
import org.zstack.sdk.VmInstanceInventory;

import com.shtd.zstack.utils.ZStackUtils;

/**
 * 3 云资源池-云主机相关接口
 * @author Josh
 * @date 2017-11-28
 */
public class VmInstanceAction {

	/**
	 * 3.1.1 创建云主机
	 * @param sessionId 必须
	 * @param vmname 必须
	 * @param zoneUuid
	 * @param instanceOfferingUuid 必须
	 * @param ImageUUID 必须
	 * @param clusterUuid 必须
	 * @param l3NetworkUuids 必须
	 * @param description
	 * @return
	 * @author Josh
	 */
	public CreateVmInstanceAction.Result createVmInstance(String sessionId, String vmname, String zoneUuid, 
			String instanceOfferingUuid, String ImageUUID, String clusterUuid, 
			String l3NetworkUuids, String description) {
		
		// 创建虚拟机
		CreateVmInstanceAction createVmInstanceAction = new CreateVmInstanceAction();
		createVmInstanceAction.name = vmname;
		createVmInstanceAction.instanceOfferingUuid = instanceOfferingUuid;
		createVmInstanceAction.imageUuid = ImageUUID;
		if (zoneUuid != "") {
			createVmInstanceAction.zoneUuid = zoneUuid; // 可选字段
		}
		createVmInstanceAction.clusterUuid = clusterUuid;

		createVmInstanceAction.l3NetworkUuids = Arrays.asList(l3NetworkUuids);
		createVmInstanceAction.description = description;
		createVmInstanceAction.sessionId = sessionId;
		CreateVmInstanceAction.Result res = createVmInstanceAction.call();

		if (res.error != null) {
			System.out.println(String.format("createVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("createVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 查询云主机
	 * @param hosturl
	 * @param vmname
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVmInstanceAction.Result queryVmInstance(String hosturl, String vmname, String sessionId){
		QueryVmInstanceAction action = new QueryVmInstanceAction();
		action.conditions = Arrays.asList("name=" + vmname);
		action.sessionId = sessionId;
		QueryVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VmInstanceInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("queryVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 云主机列表
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVmInstanceAction.Result listVmInstance(String sessionId) {
		QueryVmInstanceAction action = new QueryVmInstanceAction();
		action.sessionId = sessionId;
		QueryVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("listVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			for (int i = 0; i < res.value.getInventories().size(); i++) {
				VmInstanceInventory inventory = res.value.getInventories().get(i);
				System.out.println(String.format("listVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
			}
		}
		return res;
	}
	
	/**
	 * 删除云主机
	 * @param vmuuid
	 * @param sessionId
	 * @author Josh
	 */
	public DestroyVmInstanceAction.Result destroyVmInstance(String uuid, String sessionId){
		DestroyVmInstanceAction action = new DestroyVmInstanceAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DestroyVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("destroyVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("destroyVmInstance successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 恢复已删除云主机
	 * @param vmuuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RecoverVmInstanceAction.Result recoverVmInstance(String vmuuid, String sessionId){
		RecoverVmInstanceAction action = new RecoverVmInstanceAction();
		action.uuid = vmuuid;
		action.sessionId = sessionId;
		RecoverVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("recoverVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory =	res.value.inventory;
			System.out.println(String.format("recoverVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 彻底删除云主机
	 * @param vmuuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ExpungeVmInstanceAction.Result expungeVmInstance(String uuid, String sessionId){
		ExpungeVmInstanceAction action = new ExpungeVmInstanceAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		ExpungeVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("expungeVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("expungeVmInstance successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.6 启动云主机
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public StartVmInstanceAction.Result startVmInstance(String uuid, String sessionId){
		StartVmInstanceAction action = new StartVmInstanceAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		StartVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("startVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("startVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.7 停止云主机
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public StopVmInstanceAction.Result stopVmInstance(String uuid, String sessionId){
		StopVmInstanceAction action = new StopVmInstanceAction();
		action.uuid = uuid;
		action.type = "grace";
		action.sessionId = sessionId;
		StopVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("stopVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("stopVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.8 重启云主机
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public RebootVmInstanceAction.Result rebootVmInstance(String uuid, String sessionId){
		RebootVmInstanceAction action = new RebootVmInstanceAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		RebootVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("rebootVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("rebootVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.9 暂停云主机
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public PauseVmInstanceAction.Result pauseVmInstance(String uuid, String sessionId){
		PauseVmInstanceAction action = new PauseVmInstanceAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		PauseVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("pauseVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("pauseVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.10 恢复暂停的云主机
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ResumeVmInstanceAction.Result resumeVmInstance(String uuid, String sessionId){
		ResumeVmInstanceAction action = new ResumeVmInstanceAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		ResumeVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("resumeVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("resumeVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.11 重置云主机
	 * @param vmInstanceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ReimageVmInstanceAction.Result reimageVmInstance(String vmInstanceUuid, String sessionId){
		ReimageVmInstanceAction action = new ReimageVmInstanceAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.sessionId = sessionId;
		ReimageVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("reimageVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("reimageVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}

	/**
	 * 3.1.12 热迁移云主机
	 * @param vmInstanceUuid
	 * @param hostUuid
	 * @param sessionUuid
	 * @return
	 * @author Josh
	 */
	public MigrateVmAction.Result migrateVm(String vmInstanceUuid, String hostUuid, String sessionUuid){
		MigrateVmAction action = new MigrateVmAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.hostUuid = hostUuid;
		action.sessionId = sessionUuid;
		MigrateVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("migrateVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("migrateVm successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.13 获取可热迁移的物理机列表
	 * @param vmInstanceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVmMigrationCandidateHostsAction.Result getVmMigrationCandidateHosts(String vmInstanceUuid, String sessionId){
		GetVmMigrationCandidateHostsAction action = new GetVmMigrationCandidateHostsAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.sessionId = sessionId;
		GetVmMigrationCandidateHostsAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVmMigrationCandidateHosts error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			java.util.List<HostInventory> hostInventoryList = res.value.inventories;
			if(hostInventoryList != null && hostInventoryList.size() > 0){
				for(HostInventory hostInventory: hostInventoryList){
					System.out.println(String.format("getVmMigrationCandidateHosts successfully name:%s,uuid:%s", hostInventory.name, hostInventory.uuid));
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.1.26 获取相互依赖的镜像和L3网络
	 * @param zoneUuid
	 * @param imageUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	@SuppressWarnings("rawtypes")
	public GetInterdependentL3NetworksImagesAction.Result getInterdependentL3NetworksImages(String zoneUuid, String imageUuid, String sessionId){
		GetInterdependentL3NetworksImagesAction action = new GetInterdependentL3NetworksImagesAction();
		action.zoneUuid = zoneUuid;
		action.imageUuid = imageUuid;
		action.sessionId = sessionId;
		GetInterdependentL3NetworksImagesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getInterdependentL3NetworksImages error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			java.util.List inventories = res.value.inventories;
			if(inventories != null && inventories.size() > 0){
				for(Object obj: inventories){
					System.out.println(String.format("getInterdependentL3NetworksImages successfully l3Network:%s", obj));
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.1.31 设置云主机控制台密码
	 * @param uuid
	 * @param consolePassword
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public SetVmConsolePasswordAction.Result setVmConsolePassword(String uuid, String consolePassword, String sessionId){
		SetVmConsolePasswordAction action = new SetVmConsolePasswordAction();
		action.uuid = uuid;
		action.consolePassword = consolePassword;
		action.sessionId = sessionId;
		SetVmConsolePasswordAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("setVmConsolePassword error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("setVmConsolePassword successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 获取云主机控制台密码
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVmConsolePasswordAction.Result getVmConsolePassword(String uuid, String sessionId){
		GetVmConsolePasswordAction action = new GetVmConsolePasswordAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetVmConsolePasswordAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVmConsolePassword error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getVmConsolePassword successfully password:%s", res.value.password));
		}
		return res;
	}
	
	/**
	 * 3.1.34 设置云主机Hostname
	 * @param vmuuid
	 * @param hostname
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public SetVmHostnameAction.Result setVmHostname(String uuid, String hostname, String sessionId){
		SetVmHostnameAction action = new SetVmHostnameAction();
		action.uuid = uuid;
		action.hostname = hostname;
		action.sessionId = sessionId;
		SetVmHostnameAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("setVmHostname error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("setVmHostname successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 获取云主机能力
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVmCapabilitiesAction.Result getVmCapabilities(String uuid, String sessionId){
		GetVmCapabilitiesAction action = new GetVmCapabilitiesAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetVmCapabilitiesAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVmCapabilities error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			Map<String, Object> map = res.value.capabilities;
			for (Entry<String, Object> entry : map.entrySet()) {
				System.out.println(String.format("getVmCapabilities successfully key:%s,value:%s", entry.getKey(), entry.getValue()));
			}
		}
		return res;
	}
	
	/**
	 * 3.1.48 克隆云主机到指定物理机上
	 * @param fromvmid
	 * @param sessionId
	 * @author Josh
	 */
	public CloneVmInstanceAction.Result cloneVmInstance(String fromvmid, String sessionId) {
		CloneVmInstanceAction action = new CloneVmInstanceAction();
		action.vmInstanceUuid = fromvmid;
		action.strategy = "InstantStart";
		action.names = Arrays.asList("vm1110", "vm2220", "vm3330", "vm4440");
		action.sessionId = sessionId;
		CloneVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("cloneVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			CloneVmInstanceResults results = res.value.result;
			if(results != null){
				for (int i = 0; i < results.inventories.size(); i++) {
					CloneVmInstanceInventory cloneInventory = results.inventories.get(i);
					if(cloneInventory != null){
						if(cloneInventory.error != null){
							System.out.println(String.format("cloneVmInstance error code:%s,description:%s,details:%s", cloneInventory.error.code, cloneInventory.error.description, cloneInventory.error.details));
						}else{
							VmInstanceInventory inventory = cloneInventory.inventory;
							if(inventory != null){
								System.out.println(String.format("cloneVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
							}
						}
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.1.52 获取云主机Qga
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVmQgaAction.Result getVmQga(String uuid, String sessionId){
		GetVmQgaAction action = new GetVmQgaAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetVmQgaAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVmQga error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getVmQga successfully enable:%s", res.value.enable));
		}
		return res;
	}
	
	public static void main(String[] argv) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		VmInstanceAction vmInstance = new VmInstanceAction();

		// 创建云主机 Begin
//		vmInstance.createVmInstance(sessionId, "20171128-test", "", "b22c052db07647b9a76cd36e36da1b0e", 
//				"c843db4378e747a89de43c74e3ae2430", "c11ae3711dd741d2902c87b5a18ba3b7", "9bd019c111da48a091d8bb84003817ed", "测试创建云主机");
		// 创建云主机 End
		
		// 查询云主机 Begin
		vmInstance.queryVmInstance(ZStackUtils.hosturl, "20171128-test1", sessionId);
		// 查询云主机 End
		
		// 云主机列表 Begin
		vmInstance.listVmInstance(sessionId);
		// 云主机列表 End
		
		// 删除云主机 Begin
		vmInstance.destroyVmInstance("a5061116c07443ff9479e0a8d2a48a03", sessionId);
		// 删除云主机 End
		
		// 恢复已删除云主机 Begin
		vmInstance.recoverVmInstance("21aef9b0685a49de834aa80bef6b8dc4", sessionId);
		// 恢复已删除云主机 End
		
		// 彻底删除云主机 Begin
		vmInstance.expungeVmInstance("a5061116c07443ff9479e0a8d2a48a03", sessionId);
		// 彻底删除云主机 End
		
		// 停止云主机 Begin
//		vmInstance.stopVmInstance("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		// 停止云主机 End
		
		// 启动云主机 Begin
//		vmInstance.startVmInstance("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		// 启动云主机 End
		
		// 重启云主机 Begin
//		vmInstance.rebootVmInstance("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		// 重启云主机 End
		
		// 暂停云主机 Begin
//		vmInstance.pauseVmInstance("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		// 暂停云主机 End
		
		// 恢复暂停的云主机 Begin
//		vmInstance.resumeVmInstance("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		// 恢复暂停的云主机 End
		
		// 重置云主机 Begin
//		vmInstance.reimageVmInstance("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		// 重置云主机 End
		
		//  获取可热迁移的物理机列表 Begin
		vmInstance.getVmMigrationCandidateHosts("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		//  获取可热迁移的物理机列表 End
		
		//   获取相互依赖的镜像和L3网络 Begin
		vmInstance.getInterdependentL3NetworksImages("2429832713f74b339d3abf01e126bf23", "d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		//   获取相互依赖的镜像和L3网络 End
		
		//  设置云主机控制台密码 Begin
//		vmInstance.setVmConsolePassword("d92b0c13bb044ca5817cc11be6b605ef", "123456", sessionId);
		//  设置云主机控制台密码 End
		
		//  获取云主机控制台密码 Begin
		vmInstance.getVmConsolePassword("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		//  获取云主机控制台密码 End
		
		// 设置云主机hostname Begin
//		vmInstance.setVmHostname("21aef9b0685a49de834aa80bef6b8dc4", "vm.host.org", sessionId);
		// 设置云主机hostname End
		
		// 获取云主机能力 Begin
//		vmInstance.getVmCapabilities("21aef9b0685a49de834aa80bef6b8dc4", sessionId);
		// 获取云主机能力 End
		
		// 克隆云主机到指定物理机上 Begin
//		vmInstance.cloneVmInstance("21aef9b0685a49de834aa80bef6b8dc4", sessionId);
		// 克隆云主机到指定物理机上 End
		
		// 获取云主机Qga Begin
		vmInstance.getVmQga("21aef9b0685a49de834aa80bef6b8dc4", sessionId);
		// 获取云主机Qga End 
	}
}