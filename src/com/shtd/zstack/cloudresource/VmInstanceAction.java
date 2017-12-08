package com.shtd.zstack.cloudresource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.zstack.sdk.AttachIsoToVmInstanceAction;
import org.zstack.sdk.AttachL3NetworkToVmAction;
import org.zstack.sdk.ChangeVmPasswordAction;
import org.zstack.sdk.CloneVmInstanceAction;
import org.zstack.sdk.CloneVmInstanceInventory;
import org.zstack.sdk.CloneVmInstanceResults;
import org.zstack.sdk.ClusterInventory;
import org.zstack.sdk.CreateVmInstanceAction;
import org.zstack.sdk.DeleteVmConsolePasswordAction;
import org.zstack.sdk.DeleteVmHostnameAction;
import org.zstack.sdk.DeleteVmInstanceHaLevelAction;
import org.zstack.sdk.DeleteVmSshKeyAction;
import org.zstack.sdk.DeleteVmStaticIpAction;
import org.zstack.sdk.DestroyVmInstanceAction;
import org.zstack.sdk.DetachIsoFromVmInstanceAction;
import org.zstack.sdk.DetachL3NetworkFromVmAction;
import org.zstack.sdk.ExpungeVmInstanceAction;
import org.zstack.sdk.GetCandidateIsoForAttachingVmAction;
import org.zstack.sdk.GetCandidateVmForAttachingIsoAction;
import org.zstack.sdk.GetCandidateZonesClustersHostsForCreatingVmAction;
import org.zstack.sdk.GetInterdependentL3NetworksImagesAction;
import org.zstack.sdk.GetVmAttachableDataVolumeAction;
import org.zstack.sdk.GetVmAttachableL3NetworkAction;
import org.zstack.sdk.GetVmBootOrderAction;
import org.zstack.sdk.GetVmCapabilitiesAction;
import org.zstack.sdk.GetVmConsolePasswordAction;
import org.zstack.sdk.GetVmHostnameAction;
import org.zstack.sdk.GetVmInstanceHaLevelAction;
import org.zstack.sdk.GetVmMigrationCandidateHostsAction;
import org.zstack.sdk.GetVmQgaAction;
import org.zstack.sdk.GetVmSshKeyAction;
import org.zstack.sdk.GetVmStartingCandidateClustersHostsAction;
import org.zstack.sdk.HostInventory;
import org.zstack.sdk.ImageInventory;
import org.zstack.sdk.L3NetworkInventory;
import org.zstack.sdk.MigrateVmAction;
import org.zstack.sdk.PauseVmInstanceAction;
import org.zstack.sdk.QueryVmInstanceAction;
import org.zstack.sdk.QueryVmNicAction;
import org.zstack.sdk.RebootVmInstanceAction;
import org.zstack.sdk.RecoverVmInstanceAction;
import org.zstack.sdk.ReimageVmInstanceAction;
import org.zstack.sdk.ResumeVmInstanceAction;
import org.zstack.sdk.SetVmBootOrderAction;
import org.zstack.sdk.SetVmConsolePasswordAction;
import org.zstack.sdk.SetVmHostnameAction;
import org.zstack.sdk.SetVmInstanceHaLevelAction;
import org.zstack.sdk.SetVmQgaAction;
import org.zstack.sdk.SetVmSshKeyAction;
import org.zstack.sdk.SetVmStaticIpAction;
import org.zstack.sdk.StartVmInstanceAction;
import org.zstack.sdk.StopVmInstanceAction;
import org.zstack.sdk.UpdateVmInstanceAction;
import org.zstack.sdk.VmInstanceInventory;
import org.zstack.sdk.VmNicInventory;
import org.zstack.sdk.VolumeInventory;
import org.zstack.sdk.ZoneInventory;

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
	 * 3.1.2 删除云主机
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
	 * 3.1.3 恢复已删除云主机
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
	 * 3.1.4 彻底删除云主机
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
	 * 3.1.5 查询云主机
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
			List<HostInventory> inventoryList = res.value.inventories;
			if(inventoryList != null && inventoryList.size() > 0){
				for(HostInventory inventory: inventoryList){
					System.out.println(String.format("getVmMigrationCandidateHosts successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.1.14 获取云主机可加载ISO列表
	 * @param vmInstanceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetCandidateIsoForAttachingVmAction.Result getCandidateIsoForAttachingVm(String vmInstanceUuid, String sessionId){
		GetCandidateIsoForAttachingVmAction action = new GetCandidateIsoForAttachingVmAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.sessionId = sessionId;
		GetCandidateIsoForAttachingVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getCandidateIsoForAttachingVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<ImageInventory> inventoryList = res.value.inventories;
			if(inventoryList != null && inventoryList.size() > 0){
				for(ImageInventory inventory: inventoryList){
					System.out.println(String.format("getCandidateIsoForAttachingVm successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.1.15 获取ISO可加载云主机列表
	 * @param isoUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetCandidateVmForAttachingIsoAction.Result getCandidateVmForAttachingIso(String isoUuid, String sessionId){
		GetCandidateVmForAttachingIsoAction action = new GetCandidateVmForAttachingIsoAction();
		action.isoUuid = isoUuid;
		action.sessionId = sessionId;
		GetCandidateVmForAttachingIsoAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getCandidateVmForAttachingIso error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<VmInstanceInventory> inventoryList = res.value.inventories;
			if(inventoryList != null && inventoryList.size() > 0){
				for(VmInstanceInventory inventory: inventoryList){
					System.out.println(String.format("getCandidateVmForAttachingIso successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.1.16 加载ISO到云主机
	 * @param vmInstanceUuid
	 * @param isoUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachIsoToVmInstanceAction.Result attachIsoToVmInstance(String vmInstanceUuid, String isoUuid, String sessionId){
		AttachIsoToVmInstanceAction action = new AttachIsoToVmInstanceAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.isoUuid = isoUuid;
		action.sessionId = sessionId;
		AttachIsoToVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachIsoToVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("attachIsoToVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.17 卸载云主机上的ISO
	 * @param vmInstanceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachIsoFromVmInstanceAction.Result detachIsoFromVmInstance(String vmInstanceUuid, String sessionId){
		DetachIsoFromVmInstanceAction action = new DetachIsoFromVmInstanceAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.sessionId = sessionId;
		DetachIsoFromVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachIsoFromVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("detachIsoFromVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.18 获取云主机可加载云盘列表
	 * @param vmInstanceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVmAttachableDataVolumeAction.Result getVmAttachableDataVolume(String vmInstanceUuid, String sessionId){
		GetVmAttachableDataVolumeAction action = new GetVmAttachableDataVolumeAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.sessionId = sessionId;
		GetVmAttachableDataVolumeAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVmAttachableDataVolume error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<VolumeInventory> inventoryList = res.value.inventories;
			if(inventoryList != null && inventoryList.size() > 0){
				for(VolumeInventory inventory: inventoryList){
					System.out.println(String.format("getVmAttachableDataVolume successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.1.19 获取云主机可加载L3网络列表
	 * @param vmInstanceUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVmAttachableL3NetworkAction.Result getVmAttachableL3Network(String vmInstanceUuid, String sessionId){
		GetVmAttachableL3NetworkAction action = new GetVmAttachableL3NetworkAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.sessionId = sessionId;
		GetVmAttachableL3NetworkAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVmAttachableL3Network error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<L3NetworkInventory> inventoryList = res.value.inventories;
			if(inventoryList != null && inventoryList.size() > 0){
				for(L3NetworkInventory inventory: inventoryList){
					System.out.println(String.format("getVmAttachableL3Network successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.1.20 加载L3网络到云主机
	 * @param vmInstanceUuid
	 * @param l3NetworkUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public AttachL3NetworkToVmAction.Result attachL3NetworkToVm(String vmInstanceUuid, String l3NetworkUuid, String sessionId){
		AttachL3NetworkToVmAction action = new AttachL3NetworkToVmAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.l3NetworkUuid = l3NetworkUuid;
		action.sessionId = sessionId;
		AttachL3NetworkToVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("attachL3NetworkToVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("attachL3NetworkToVm successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.21 从云主机卸载网络
	 * @param vmNicUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DetachL3NetworkFromVmAction.Result detachL3NetworkFromVm(String vmNicUuid, String sessionId){
		DetachL3NetworkFromVmAction action = new DetachL3NetworkFromVmAction();
		action.vmNicUuid = vmNicUuid;
		action.sessionId = sessionId;
		DetachL3NetworkFromVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("detachL3NetworkFromVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("detachL3NetworkFromVm successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.22 查询云主机网卡
	 * @param ip
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public QueryVmNicAction.Result queryVmNic(String ip, String sessionId){
		QueryVmNicAction action = new QueryVmNicAction();
		action.conditions = Arrays.asList("ip=" + ip);
		action.sessionId = sessionId;
		QueryVmNicAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("queryVmNic error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<VmNicInventory> inventoryList = res.value.inventories;
			if(inventoryList != null && inventoryList.size() > 0){
				for(VmNicInventory inventory: inventoryList){
					System.out.println(String.format("queryVmNic successfully deviceId:%s,uuid:%s,gateway:%s,mac:%s", 
							inventory.deviceId, inventory.uuid, inventory.gateway, inventory.mac));
				}
			}
		}
		return res;
	}
	
	// 3.1.23 设置云主机网卡限速
	/*public void setNicQoS(){
		SetNicQoSAction action = new SetNicQoSAction();
		action.uuid = "00cc6445774649b488f6a03b023d6701";
		action.outboundBandwidth = 819200.0;
		action.inboundBandwidth = 819200.0;
		action.sessionUuid = "d5a1b6a5424e4b1c8cc23f77e8ba6d58";
		SetNicQoSAction.Result res = action.call();
	}*/
	
	// 3.1.24 获取云主机网卡限速
	/*public void getNicQoS(){
		GetNicQoSAction action = new GetNicQoSAction();
		action.uuid = "9d6e28b144a34b01807a5a9d83ba005c";
		action.sessionUuid = "883ff8e0d0cd43ab91005df23ad062ee";
		GetNicQoSAction.Result res = action.call();
	}*/
	
	// 3.1.25 取消云主机网卡限速
	/*public void deleteNicQoS(){
		DeleteNicQoSAction action = new DeleteNicQoSAction();
		action.uuid = "27b5b55e69284a8ea07871661de562a4";
		action.direction = "example";
		action.sessionUuid = "d83ac78201fc4ad38b17261bba4dc17b";
		DeleteNicQoSAction.Result res = action.call();
	}*/
	
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
	 * 3.1.27 设置云主机SSH Key
	 * @param uuid
	 * @param SshKey
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public SetVmSshKeyAction.Result setVmSshKey(String uuid, String SshKey, String sessionId){
		SetVmSshKeyAction action = new SetVmSshKeyAction();
		action.uuid = uuid;
		action.SshKey = SshKey;
		action.sessionId = sessionId;
		SetVmSshKeyAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("setVmSshKey error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("setVmSshKey successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.28 获取云主机SSH Key
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVmSshKeyAction.Result getVmSshKey(String uuid, String sessionId){
		GetVmSshKeyAction action = new GetVmSshKeyAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetVmSshKeyAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVmSshKey error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getVmSshKey successfully sshKey:%s", res.value.sshKey));
		}
		return res;
	}
	
	/**
	 * 3.1.29 删除云主机SSH Key
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteVmSshKeyAction.Result deleteVmSshKey(String uuid, String sessionId){
		DeleteVmSshKeyAction action = new DeleteVmSshKeyAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		DeleteVmSshKeyAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVmSshKey error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteVmSshKey successfully uuid:%s", uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.30 变更云主机密码
	 * @param uuid
	 * @param password
	 * @param account
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public ChangeVmPasswordAction.Result changeVmPassword(String uuid, String password, String account, String sessionId){
		ChangeVmPasswordAction action = new ChangeVmPasswordAction();
		action.uuid = uuid;
		action.password = password;
		action.account = account;
		action.sessionId = sessionId;
		ChangeVmPasswordAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("changeVmPassword error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("changeVmPassword successfully uuid:%s", uuid));
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
	 * 3.1.32 获取云主机控制台密码
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
	 * 3.1.33 删除云主机控制台密码
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteVmConsolePasswordAction.Result deleteVmConsolePassword(String uuid, String sessionId){
		DeleteVmConsolePasswordAction action = new DeleteVmConsolePasswordAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		DeleteVmConsolePasswordAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVmConsolePassword error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteVmConsolePassword successfully uuid:%s", uuid));
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
	 * 3.1.35 获取云主机Hostname
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVmHostnameAction.Result getVmHostname(String uuid, String sessionId){
		GetVmHostnameAction action = new GetVmHostnameAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetVmHostnameAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVmHostname error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getVmHostname successfully hostname:%s", res.value.hostname));
		}
		return res;
	}
	
	/**
	 * 3.1.36 删除云主机Hostname
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteVmHostnameAction.Result deleteVmHostname(String uuid, String sessionId){
		DeleteVmHostnameAction action = new DeleteVmHostnameAction();
		action.uuid = uuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteVmHostnameAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVmHostname error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteVmHostname successfully uuid:%s", uuid));
		}
		return res;
	}
	
	// 3.1.37 创建启动云主机的定时任务
	/*public void createStartVmInstanceScheduler(){
		CreateStartVmInstanceSchedulerAction action = new CreateStartVmInstanceSchedulerAction();
		action.vmUuid = "7d86597d42eb4e31b2497a06361f710b";
		action.schedulerName = "create-vm-scheduler";
		action.schedulerDescription = "for test create vm scheduler";
		action.type = "simple";
		action.interval = 5.0;
		action.repeatCount = 10.0;
		action.startTime = 0.0;
		action.sessionUuid = "a5e890d5bd78487f979861dee02aaafe";
		CreateStartVmInstanceSchedulerAction.Result res = action.call();
	}*/
	
	// 3.1.38 创建停止云主机的定时任务
	/*public void createStopVmInstanceScheduler(){
		CreateStopVmInstanceSchedulerAction action = new CreateStopVmInstanceSchedulerAction();
		action.vmUuid = "12cd4882e5784a81967a314be5f10a55";
		action.schedulerName = "vm-scheduler";
		action.schedulerDescription = "for test stop vm scheduler";
		action.type = "simple";
		action.interval = 5.0;
		action.repeatCount = 10.0;
		action.startTime = 0.0;
		action.sessionUuid = "256f557f46ff499aad22d7b1776a7567";
		CreateStopVmInstanceSchedulerAction.Result res = action.call();
	}*/
	
	// 3.1.39 创建重启云主机的定时任务
	/*public void createRebootVmInstanceScheduler(){
		CreateRebootVmInstanceSchedulerAction action = new CreateRebootVmInstanceSchedulerAction();
		action.vmUuid = "65f8b54efe254b8e8e5b427bfdadd1e6";
		action.schedulerName = "vm-scheduler";
		action.schedulerDescription = "for test restart vm scheduler";
		action.type = "simple";
		action.interval = 5.0;
		action.repeatCount = 10.0;
		action.startTime = 0.0;
		action.sessionUuid = "3cedf9bd8bbf424f860057259adfcdcf";
		CreateRebootVmInstanceSchedulerAction.Result res = action.call();
	}*/
	
	/**
	 * 3.1.40 获得云主机启动设备列表
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVmBootOrderAction.Result getVmBootOrder(String uuid, String sessionId){
		GetVmBootOrderAction action = new GetVmBootOrderAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetVmBootOrderAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVmBootOrder error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<String> orders = res.value.orders;
			if(orders != null && orders.size() > 0){
				for(String order: orders){
					System.out.println(String.format("getVmBootOrder successfully order:%s", order));		
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.1.41 指定云主机启动设备
	 * @param uuid
	 * @param bootOrder
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public SetVmBootOrderAction.Result setVmBootOrder(String uuid, String bootOrder, String sessionId){
		SetVmBootOrderAction action = new SetVmBootOrderAction();
		action.uuid = uuid;
		action.bootOrder = Arrays.asList(bootOrder);
		action.sessionId = sessionId;
		SetVmBootOrderAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("setVmBootOrder error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("setVmBootOrder successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.42 获取目的地列表
	 * @param instanceOfferingUuid
	 * @param imageUuid
	 * @param l3NetworkUuids
	 * @param dataDiskOfferingUuids
	 * @param clusterUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetCandidateZonesClustersHostsForCreatingVmAction.Result getCandidateZonesClustersHostsForCreatingVm(String instanceOfferingUuid, String imageUuid, String l3NetworkUuids, 
			String dataDiskOfferingUuids, String clusterUuid, String sessionId){
		
		GetCandidateZonesClustersHostsForCreatingVmAction action = new GetCandidateZonesClustersHostsForCreatingVmAction();
		action.instanceOfferingUuid = instanceOfferingUuid;
		action.imageUuid = imageUuid;
		action.l3NetworkUuids = Arrays.asList(l3NetworkUuids);
		action.dataDiskOfferingUuids = Arrays.asList(dataDiskOfferingUuids);
		action.clusterUuid = clusterUuid;
		action.sessionId = sessionId;
		GetCandidateZonesClustersHostsForCreatingVmAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getCandidateZonesClustersHostsForCreatingVm error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<ZoneInventory> zones = res.value.zones;
			if(zones != null && zones.size() > 0){
				for(ZoneInventory inventory: zones){
					System.out.println(String.format("getCandidateZonesClustersHostsForCreatingVm successfully ZoneInventory name:%s,uuid:%s", inventory.name, inventory.uuid));		
				}
			}
			List<ClusterInventory> clusters = res.value.clusters;
			if(clusters != null && clusters.size() > 0){
				for(ClusterInventory inventory: clusters){
					System.out.println(String.format("getCandidateZonesClustersHostsForCreatingVm successfully ClusterInventory name:%s,uuid:%s", inventory.name, inventory.uuid));		
				}
			}
			List<HostInventory> hosts = res.value.hosts;
			if(hosts != null && hosts.size() > 0){
				for(HostInventory inventory: hosts){
					System.out.println(String.format("getCandidateZonesClustersHostsForCreatingVm successfully HostInventory name:%s,uuid:%s", inventory.name, inventory.uuid));		
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.1.43 获取云主机可启动目的地列表
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVmStartingCandidateClustersHostsAction.Result getVmStartingCandidateClustersHosts(String uuid, String sessionId){
		GetVmStartingCandidateClustersHostsAction action = new GetVmStartingCandidateClustersHostsAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetVmStartingCandidateClustersHostsAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVmStartingCandidateClustersHosts error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			List<HostInventory> hosts = res.value.hosts;
			if(hosts != null && hosts.size() > 0){
				for(HostInventory inventory: hosts){
					System.out.println(String.format("getVmStartingCandidateClustersHosts successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
				}
			}
			List<ClusterInventory> clusters = res.value.clusters;
			if(clusters != null && clusters.size() > 0){
				for(ClusterInventory inventory: clusters){
					System.out.println(String.format("getVmStartingCandidateClustersHosts successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
				}
			}
		}
		return res;
	}
	
	/**
	 * 3.1.44 指定云主机IP
	 * @param vmInstanceUuid
	 * @param l3NetworkUuid
	 * @param ip
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public SetVmStaticIpAction.Result setVmStaticIp(String vmInstanceUuid, String l3NetworkUuid, String ip, String sessionId){
		SetVmStaticIpAction action = new SetVmStaticIpAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.l3NetworkUuid = l3NetworkUuid;
		action.ip = ip;
		action.sessionId = sessionId;
		SetVmStaticIpAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("setVmStaticIp error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("setVmStaticIp successfully vmInstanceUuid:%s,l3NetworkUuid:%s,ip:%s", vmInstanceUuid, l3NetworkUuid, ip));
		}
		return res;
	}
	
	/**
	 * 3.1.45 删除云主机指定IP
	 * @param vmInstanceUuid
	 * @param l3NetworkUuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteVmStaticIpAction.Result deleteVmStaticIp(String vmInstanceUuid, String l3NetworkUuid, String sessionId){
		DeleteVmStaticIpAction action = new DeleteVmStaticIpAction();
		action.vmInstanceUuid = vmInstanceUuid;
		action.l3NetworkUuid = l3NetworkUuid;
		action.deleteMode = "Permissive";
		action.sessionId = sessionId;
		DeleteVmStaticIpAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVmStaticIp error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteVmStaticIp successfully vmInstanceUuid:%s,l3NetworkUuid:%s", vmInstanceUuid, l3NetworkUuid));
		}
		return res;
	}
	
	/**
	 * 3.1.46 获取云主机能力
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
	 * 3.1.47 更新云主机信息
	 * @param uuid
	 * @param name
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public UpdateVmInstanceAction.Result updateVmInstance(String uuid, String name, String sessionId){
		UpdateVmInstanceAction action = new UpdateVmInstanceAction();
		action.uuid = uuid;
		action.name = name;
		action.sessionId = sessionId;
		UpdateVmInstanceAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("updateVmInstance error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			VmInstanceInventory inventory = res.value.inventory;
			System.out.println(String.format("updateVmInstance successfully name:%s,uuid:%s", inventory.name, inventory.uuid));
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
	 * 3.1.49 设置云主机高可用级别
	 * @param uuid
	 * @param level
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public SetVmInstanceHaLevelAction.Result setVmInstanceHaLevel(String uuid, String level, String sessionId){
		SetVmInstanceHaLevelAction action = new SetVmInstanceHaLevelAction();
		action.uuid = uuid;
		action.level = level;
		action.sessionId = sessionId;
		SetVmInstanceHaLevelAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("setVmInstanceHaLevel error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("setVmInstanceHaLevel successfully level:%s,uuid:%s", level, uuid));
		}
		return res;
	}
	
	/**
	 * 3.1.50 获取云主机高可用级别
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public GetVmInstanceHaLevelAction.Result getVmInstanceHaLevel(String uuid, String sessionId){
		GetVmInstanceHaLevelAction action = new GetVmInstanceHaLevelAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		GetVmInstanceHaLevelAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("getVmInstanceHaLevel error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("getVmInstanceHaLevel successfully level:%s", res.value.level));
		}
		return res;
	}
	
	/**
	 * 3.1.51 删除云主机高可用是否成功
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public DeleteVmInstanceHaLevelAction.Result deleteVmInstanceHaLevel(String uuid, String sessionId){
		DeleteVmInstanceHaLevelAction action = new DeleteVmInstanceHaLevelAction();
		action.uuid = uuid;
		action.sessionId = sessionId;
		DeleteVmInstanceHaLevelAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("deleteVmInstanceHaLevel error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("deleteVmInstanceHaLevel successfully uuid:%s", uuid));
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
	
	/**
	 * 3.1.53 设置云主机Qga
	 * @param uuid
	 * @param sessionId
	 * @return
	 * @author Josh
	 */
	public SetVmQgaAction.Result setVmQga(String uuid, String sessionId){
		SetVmQgaAction action = new SetVmQgaAction();
		action.uuid = uuid;
		action.enable = false;
		action.sessionId = sessionId;
		SetVmQgaAction.Result res = action.call();
		if (res.error != null) {
			System.out.println(String.format("setVmQga error code:%s,description:%s,details:%s", res.error.code, res.error.description, res.error.details));
		} else {
			System.out.println(String.format("setVmQga successfully uuid:%s", uuid));
		}
		return res;
	}
	
	public static void main(String[] argv) {

		String sessionId = ZStackUtils.ZStackLogin();
		
		VmInstanceAction action = new VmInstanceAction();

		// 创建云主机 Begin
//		action.createVmInstance(sessionId, "20171128-test", "", "b22c052db07647b9a76cd36e36da1b0e", 
//				"c843db4378e747a89de43c74e3ae2430", "c11ae3711dd741d2902c87b5a18ba3b7", "9bd019c111da48a091d8bb84003817ed", "测试创建云主机");
		// 创建云主机 End
		
		// 查询云主机 Begin
		action.queryVmInstance(ZStackUtils.hosturl, "20171128-test1", sessionId);
		// 查询云主机 End
		
		// 删除云主机 Begin
		action.destroyVmInstance("a5061116c07443ff9479e0a8d2a48a03", sessionId);
		// 删除云主机 End
		
		// 恢复已删除云主机 Begin
		action.recoverVmInstance("21aef9b0685a49de834aa80bef6b8dc4", sessionId);
		// 恢复已删除云主机 End
		
		// 彻底删除云主机 Begin
		action.expungeVmInstance("a5061116c07443ff9479e0a8d2a48a03", sessionId);
		// 彻底删除云主机 End
		
		// 停止云主机 Begin
//		action.stopVmInstance("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		// 停止云主机 End
		
		// 启动云主机 Begin
//		action.startVmInstance("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		// 启动云主机 End
		
		// 重启云主机 Begin
//		action.rebootVmInstance("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		// 重启云主机 End
		
		// 暂停云主机 Begin
//		action.pauseVmInstance("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		// 暂停云主机 End
		
		// 恢复暂停的云主机 Begin
//		action.resumeVmInstance("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		// 恢复暂停的云主机 End
		
		// 重置云主机 Begin
//		action.reimageVmInstance("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		// 重置云主机 End
		
		//  获取可热迁移的物理机列表 Begin
		action.getVmMigrationCandidateHosts("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		//  获取可热迁移的物理机列表 End
		
		//   获取相互依赖的镜像和L3网络 Begin
		action.getInterdependentL3NetworksImages("2429832713f74b339d3abf01e126bf23", "d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		//   获取相互依赖的镜像和L3网络 End
		
		//  设置云主机控制台密码 Begin
//		action.setVmConsolePassword("d92b0c13bb044ca5817cc11be6b605ef", "123456", sessionId);
		//  设置云主机控制台密码 End
		
		//  获取云主机控制台密码 Begin
		action.getVmConsolePassword("d92b0c13bb044ca5817cc11be6b605ef", sessionId);
		//  获取云主机控制台密码 End
		
		// 设置云主机hostname Begin
//		action.setVmHostname("21aef9b0685a49de834aa80bef6b8dc4", "vm.host.org", sessionId);
		// 设置云主机hostname End
		
		// 获取云主机能力 Begin
//		action.getVmCapabilities("21aef9b0685a49de834aa80bef6b8dc4", sessionId);
		// 获取云主机能力 End
		
		// 克隆云主机到指定物理机上 Begin
//		action.cloneVmInstance("21aef9b0685a49de834aa80bef6b8dc4", sessionId);
		// 克隆云主机到指定物理机上 End
		
		// 获取云主机Qga Begin
		action.getVmQga("21aef9b0685a49de834aa80bef6b8dc4", sessionId);
		// 获取云主机Qga End 
	}
}